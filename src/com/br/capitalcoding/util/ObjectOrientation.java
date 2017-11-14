package com.br.capitalcoding.util;

import java.util.Arrays;

import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.RS2Object;

import com.br.capitalcoding.BotScope;

public class ObjectOrientation {

	public static boolean pastThrough(RS2Object obj, Position dir) {
		Position current = BotScope.getContext().myPosition();
		Face objectFace = getObjectFace(obj);
		boolean dirEastern = dir.getX() > obj.getX();
		boolean dirWestern = dir.getX() < obj.getX();
		BotScope.message("Position dir:"+dir.toString());
		switch (objectFace) {
		case EAST:
			if(dirEastern)
				return current.getX() > obj.getX();
			if(dirWestern)
				return current.getX() <= obj.getX();
			break;
		case NORTH:
			if(dirEastern)
				return current.getY() < obj.getY();
			if(dirWestern)
				return current.getY() >= obj.getY();
			break;
		case SOUTH:
			if(dirEastern)
				return current.getY() <= obj.getY();
			if(dirWestern)
				return current.getY() > obj.getY();
			break;
		case WEST:
			if(dirEastern) //gate west
				return current.getX() < obj.getX();
			if(dirWestern) //gate west
				return current.getX() >= obj.getX();
			
			break;
		default:
			break;

		}
						
		return true;
	}
	private static Face getObjectFace(RS2Object obj) {
		return Arrays.asList(Face.values()).stream().filter(i-> i.turnedRule(obj)).findFirst().get();
	}
}

enum Face {
	NORTH {
		@Override
		boolean turnedRule(RS2Object obj) {
			return obj.getOrientation() == 1;
		}
	},
	SOUTH {
		@Override
		boolean turnedRule(RS2Object obj) {
			return obj.getOrientation() == 3;
		}
	},
	WEST{
		@Override
		boolean turnedRule(RS2Object obj) {
			return obj.getOrientation() == 0;
		}
	},
	
	EAST {
		@Override
		boolean turnedRule(RS2Object obj) {
			return obj.getOrientation() == 2;
		}
	};
	
	abstract boolean turnedRule(RS2Object obj);
}
