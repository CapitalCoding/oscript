package com.br.capitalcoding.task.impl.combat;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.ui.Tab;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.impl.item.ItemOnInventory;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.task.types.PreConditionTask;

public class EatFood extends GameTask{

	private int hpPercentage;
	public EatFood(int hpPercentage) {
		super(2, "Eating food", false, 0);
		this.hpPercentage = hpPercentage;
	}

	
	@Override
	public void run() {	
		if(getProvider().myPlayer().getHealthPercent() <= hpPercentage) {
		getProvider().getTabs().open(Tab.INVENTORY);
		boolean item = getProvider().getInventory().interact("Eat", new Filter<Item>() {
			@Override
			public boolean match(final Item item) {
				if(item.hasAction("Eat"))
					return true;
				return false;
			}
			
		});
	}
	}

}
