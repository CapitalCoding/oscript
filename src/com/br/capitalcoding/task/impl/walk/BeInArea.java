package com.br.capitalcoding.task.impl.walk;

import org.osbot.rs07.api.filter.AreaFilter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.game.area.CityArea;
import com.br.capitalcoding.game.area.CityAreas;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;

public class BeInArea extends PreConditionTask{

	private Area area;
	
	public BeInArea(Area area) {
		super(1, "Being in an area", false, 0);
		this.area = area;
	}
	@Override
	public boolean isMeetCondition() {
		return area.contains(BotScope.getContext().myPosition());
	}

	@Override
	public void run() {
		BotScope.message("Walking to:");
		if(!BotScope.getContext().myPlayer().isMoving())
		getProvider().getWalking().webWalk(area);		
		
	}
	
}
