package com.br.capitalcoding.task.impl.item;

import org.osbot.rs07.api.def.ItemDefinition;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.GroundItem;
import org.osbot.rs07.api.model.Item;

import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.impl.walk.BeInArea;
import com.br.capitalcoding.task.types.PreConditionTask;

public class TakeGroundItem extends PreConditionTask{

	
	final private PreCondition conditions;

	public TakeGroundItem(int item) {
		super(2, "Ground Item", false, 0);
		this.conditions = new PreCondition().add((new BeInArea(new Area(3188,3270,3192,3275))));
		this.item = ItemDefinition.forId(item);
	}
	final ItemDefinition item;
	@Override
	public PreCondition getPreCondition() {
		return conditions;
	}
	@Override
	public boolean isMeetCondition() {
		return item != null && getProvider().getInventory().contains(item.getId());
	}
	@Override
	public void run() {
	GroundItem item = getProvider().getGroundItems().closest(groundItem);
		if(item != null && item.interact("Take")) {
			
		}
	}

	
	Filter<GroundItem> groundItem = 
		new Filter<GroundItem>() {

			@Override
			public boolean match(GroundItem i) {
				if(item != null && item.getId() == i.getId())
					return true;
				return false;
			}
			
		};

	
}
