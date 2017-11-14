package com.br.capitalcoding.task.impl.bank;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.game.area.CityAreas;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.impl.walk.BeInArea;
import com.br.capitalcoding.task.types.PreConditionTask;

public class StayNearBank extends PreConditionTask {

	private Area bank;
	public StayNearBank(Area bank) { //TODO: specify which bank
		super(1, "Stay near bank", false, 0);
		this.bank = bank;
	}
	PreCondition condition = new PreCondition().add(new BeInArea(bank));
	@Override
	public boolean isMeetCondition() {
		return getProvider().getNpcs().closest("Banker") != null;
	}
	@Override
	public PreCondition getPreCondition() {
		return condition;
	}
	@Override
	public void run() {
			
	}

}
