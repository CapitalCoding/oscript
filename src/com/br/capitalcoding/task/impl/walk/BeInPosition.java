package com.br.capitalcoding.task.impl.walk;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;

public class BeInPosition extends PreConditionTask{

	private Position pos;
	
	public BeInPosition(Position pos) {
		super(1, "Being in an position", false, 0);
		this.pos = pos;
	}
	@Override
	public boolean isMeetCondition() {
		return pos.equals(BotScope.getContext().myPosition());
	}

	@Override
	public void run() {
		BotScope.message("Walking to:");
		if(!BotScope.getContext().myPlayer().isMoving())
		getProvider().getWalking().webWalk(pos);		
		
	}
	
}
