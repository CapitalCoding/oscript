package com.br.capitalcoding.task.impl.walk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;

import com.br.capitalcoding.BotScope;

public class ReachManager {

	List<ReachPoint> reachPoints = new ArrayList<>();
	
	public ReachManager(ReachPoint... point) {
		this.reachPoints.addAll(Arrays.asList(point));
	}
	public ReachManager(ReachPoint point) {
		this.reachPoints.add(point);
	}
	List<Position> path;
	List<RS2Object> obstacles;
	private void define() {
		path = BotScope.getWalker().generatePath(reachPoints.get(reachPoints.size()).getPos());
	}
	private void next() {
	       obstacles.sort(new Comparator<RS2Object>() {

				@Override
				public int compare(RS2Object o1, RS2Object o2) {
					Position my = BotScope.getContext().myPosition();
					return Integer.compare(o1.getPosition().distance(my),o2.getPosition().distance(my));
				}
			});
	       
	       BotScope.message("Path:"+path.stream().map(Position::toString).collect(Collectors.joining(".")));
	       
	        for (RS2Object obj : obstacles) {
	            for (Position pos : path) {            	
	                if (obj.getPosition().equals(pos) ) {
	                   // return obj;
	                }
	            }
	        }

	}
	private Optional<Position> determinePathVisible(List<Position> path) {
		Collections.reverse(path);
		return path.stream().filter(i->i.isOnMiniMap(BotScope.getContext().getBot())).findFirst();
		

	}
	public void walk() {
		
		
	}
}
