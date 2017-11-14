package com.br.capitalcoding.model;

import org.osbot.rs07.api.DoorHandler;
import org.osbot.rs07.api.def.ObjectDefinition;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.util.LocalPathFinder;
import org.osbot.rs07.canvas.paint.Painter;
import org.osbot.rs07.input.mouse.MiniMapTileDestination;
import org.osbot.rs07.input.mouse.RectangleDestination;
import org.osbot.rs07.script.Script;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.util.ObjectOrientation;
import com.br.capitalcoding.util.Utils;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Originally created by Maxi
 */

/**
 * Updated by Swizzbeat
 */

public class Walker {
    
    private Script scriptInstance;
    
    public Walker(Script scriptInstance) {
        this.scriptInstance = scriptInstance;
    }
    
    public boolean noObstacleBlocking(Position p){
        RS2Object obstacle = getNextObstacle(p);//getNextObstacle(p);
        if (obstacle != null) {
            obstacle.interact("Open");
            return false;
        }
        return true;
    }
    public boolean walkPathMM(Position[] path) {
        return walkPathMM(path, 3);
    }
    
    public boolean walkPathMM(Position[] path, int distance) {
        Position next = nextTile(path, 15);
        if (next != null && noObstacleBlocking(next)) {
            return clickMiniMapPosition(next);
        }
        Position lastNode = path[path.length - 1];
        return lastNode != null && scriptInstance.map.distance(lastNode) < distance;
    }
    
    public Position nextTile(Position path[], int skipDist) {
        int dist = -1, closest = -1;
        for (int i = path.length - 1; i >= 0; i--) {
            Position tile = path[i];
            int d = scriptInstance.map.distance(tile);
            if (d < dist || dist == -1) {
                dist = d;
                closest = i;
            }
        }
        
        int feasibleTileIndex = -1;
        
        for (int i = closest; i < path.length; i++) {
            if (scriptInstance.map.distance(path[i]) <= skipDist) {
                feasibleTileIndex = i;
            } else {
                break;
            }
        }

		return (feasibleTileIndex == -1) ? null : path[feasibleTileIndex];
    }

    public RS2Object getNextObstacle(Entity e) {
        List<RS2Object> obstacles = getObstacles();
        List<Position> path = generatePath(e);
        if (path == null) {
            return null;
        }
        for (RS2Object obj : obstacles) {
            for (Position pos : path) {
                if (obj.getPosition().equals(pos)) {
                    return obj;
                }
            }
        }
        return null;
    }
    
    public RS2Object getNextObstacle(Position p) {
        List<RS2Object> obstacles = getObstacles();
        List<Position> path = generatePath(p);
        Position my = BotScope.getContext().myPosition();
        if (path == null) {
            return null;
        }
        //Collections.reverse(obstacles);
       obstacles.sort(new Comparator<RS2Object>() {

			@Override
			public int compare(RS2Object o1, RS2Object o2) {
				
				return Integer.compare(o1.getPosition().distance(my),o2.getPosition().distance(my));
			}
		});
      // BotScope.message("Path:"+path.stream().map(Position::toString).collect(Collectors.joining(".")));
       
        for (RS2Object obj : obstacles) {
            for (Position pos : path) {       
            	if(obj.getArea(1).contains(my) && !ObjectOrientation.pastThrough(obj,p))
            		return obj;
                if (obj.getPosition().equals(pos)) {               	
                		return obj;
                }
            }
        }
        return null;
    }
    
    public List<RS2Object> getObstacles() {
        List<RS2Object> list = new LinkedList<>();
        //for (RS2Object obj : scriptInstance.objects.getAll()) {
        for (RS2Object obj : BotScope.getContext().getDoorHandler().getObstacles()) {
            if (obj.getType() == 0 && obj.getDefinition() != null && 
            		obj.getDefinition().getActions() != null && 
            		obj.getDefinition().getModelIds() != null && 
            		obj.getDefinition().getModelIds().length < 3) {
                search:
                {
                    for (String action : obj.getDefinition().getActions()) {
                        if (action != null && action.equalsIgnoreCase("open")) {
                            list.add(obj);
                            break search;
                        }
                    }
                }
            }
        }
        return list;
    }
    
    public List<Position> generatePath(Position p) {
		LocalPathFinder pf = new LocalPathFinder(scriptInstance.bot);
        int[][] flags = generateModifiedClippingData();
        List<Position> path = pf.findPath(p, flags);
        if (path == null) {
            return null;
        }
        return path;
    }
    
    private List<Position> generatePath(Entity e) {
		LocalPathFinder pf = new LocalPathFinder(scriptInstance.bot);
        int[][] flags = generateModifiedClippingData();
        List<Position> path = pf.findPath(e, flags);
        if (path == null) {
            return null;
        }
        return path;
    }
    
    private int[][] generateModifiedClippingData() {
        int[][] origFlags = scriptInstance.map.getRegion().getClippingPlanes()[scriptInstance.map.getPlane()].getTileFlags();
        int[][] flags = new int[origFlags.length][origFlags.length];
        for (int x = 0; x < flags.length; x++) {
            for (int y = 0; y < flags.length; y++) {
                flags[x][y] = origFlags[x][y];
            }
        }
        
        for (RS2Object obj : getObstacles()) {
            int lx = obj.getLocalX();
            int ly = obj.getLocalY();
            ObjectDefinition def = obj.getDefinition();
            if (def.isClipping1()) {
                switch (obj.getOrientation()) {
                    case 0:
                    case 2:
                        flags[lx][ly] &= ~585;
                        break;
                    case 1:
                    case 3:
                        flags[lx][ly] &= ~1170;
                        break;
                }
            }
            
            if (def.getClipping2() != 0) {
                if (0 == obj.getOrientation()) {
                    flags[lx][ly] &= ~128;
                    flags[lx - 1][ly] &= ~8;
                }
                
                if (1 == obj.getOrientation()) {
                    flags[lx][ly] &= ~2;
                    flags[lx][ly + 1] &= ~32;
                }
                
                if (2 == obj.getOrientation()) {
                    flags[lx][ly] &= ~8;
                    flags[lx + 1][ly] &= ~128;
                }
                
                if (3 == obj.getOrientation()) {
                    flags[lx][ly] &= ~32;
                    flags[lx][ly - 1] &= ~2;
                }
                
                if (def.isClipping3()) {
                    if (0 == obj.getOrientation()) {
                        flags[lx][ly] &= ~65536;
                        flags[lx - 1][ly] &= ~4096;
                    }
                    
                    if (obj.getOrientation() == 1) {
                        flags[lx][ly] &= ~1024;
                        flags[lx][ly + 1] &= ~16384;
                    }
                    
                    if (2 == obj.getOrientation()) {
                        flags[lx][ly] &= ~4096;
                        flags[lx + 1][ly] &= ~65536;
                    }
                    
                    if (3 == obj.getOrientation()) {
                        flags[lx][ly] &= ~16384;
                        flags[lx][ly - 1] &= ~1024;
                    }
                }
            }
        }
        return flags;
    }

    private boolean clickMiniMapPosition(Position position) {
	return scriptInstance.mouse.click(new RectangleDestination(scriptInstance.bot, new MiniMapTileDestination(scriptInstance.bot, position).getBoundingBox()));
    }
    
    public Position[] reversePath(Position[] path) {
        Position[] t = new Position[path.length];
        for (int i = 0; i < t.length; i++) {
            t[i] = path[path.length - i - 1];
        }
        return t;
    }
} 