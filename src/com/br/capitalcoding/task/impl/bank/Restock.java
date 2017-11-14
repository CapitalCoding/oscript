package com.br.capitalcoding.task.impl.bank;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Item;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.types.PreConditionTask;

public class Restock extends PreConditionTask{

	
	private Area area;
	public Restock(Area area) {
		super(2, "Restocking", false, 0);
		this.area = area;
	}
	PreCondition condition = new PreCondition().add(new StayNearBank(area)).add(new OpenBank());
	@Override
	protected PreCondition conditionFor(TaskState state) {
		return condition;
	}
	
	@Override
	public boolean isMeetCondition() {
		return getProvider().getInventory().isFull() && getProvider().getInventory().contains(getInvItem().getName());
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		if(getProvider().getBank().contains(allItems)) {
			getProvider().getBank().depositAll();
			BotScope.message("Deposited");
		}
		getProvider().getBank().withdraw(new Filter<Item>() {			
			@Override
			public boolean match(Item item) {
				if(item.hasAction("Eat"))
					return true;
				return false;
			}
		}, getProvider().getInventory().getEmptySlots());
		
	}

	@SuppressWarnings("unchecked")
	private Item getInvItem() {
		return getProvider().getInventory().getItem(food);
	}
	Filter<Item> food = new Filter<Item>() {			
		@Override
		public boolean match(Item item) {
			if(item.hasAction("Eat"))
				return true;
			return false;
		
		}
	};
	Filter<Item> allItems = new Filter<Item>() {

		@Override
		public boolean match(Item item) {
			if(!item.hasAction("Eat"))
				return true;
			return false;
		}
		
	};
	
}
