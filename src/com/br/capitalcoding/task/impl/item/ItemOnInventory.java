package com.br.capitalcoding.task.impl.item;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.model.Item;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;

public class ItemOnInventory extends PreConditionTask {

	private Item item;

	public ItemOnInventory(Item item) {
		super(5, "Grabbing Item", false, 0);
	}
	
	@Override
	public boolean isMeetCondition() {
		return (getProvider().getInventory().getAmount(item.getId()) >= item.getAmount());
	}
	@Override
	public void run() {
		
	}

}
