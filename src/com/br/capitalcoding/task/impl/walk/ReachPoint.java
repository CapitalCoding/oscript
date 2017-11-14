package com.br.capitalcoding.task.impl.walk;

import org.osbot.rs07.api.map.Position;

public class ReachPoint {


	public ReachPoint(Position pos) {
		this.pos = pos;
		this.reached = false;
	}
	final Position pos;
	boolean reached;
	
	public boolean isReached() {
		return reached;
	}
	public void setReached(boolean reached) {
		this.reached = reached;
	}
	public Position getPos() {
		return pos;
	}
}
