package com.br.capitalcoding.task.drop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.GroundItem;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.game.area.stronghold.StrongholdNPC;
import com.br.capitalcoding.task.types.PreConditionTask;

public class DropWatcher extends PreConditionTask{

	public DropWatcher() {
		super(2, "Drop Watcher", false, 0);
	}
	int[] excludedIds = {526,592,440,9011};
	@SuppressWarnings("unchecked")
	@Override
	public boolean isMeetCondition() {
		GroundItem item = getGroundItem();
		return getProvider().getInventory().isFull() || item == null || (item != null) && !getGroundItem().exists();
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		GroundItem item = getGroundItem();
		BotScope.message("Tentando pegar1");
		if(item != null && item.interact("Take")) {
			BotScope.message("Tentando pegar");
		}
		
	}
	//2121,5267, 2134, 5275
	@SuppressWarnings("unchecked")
	private GroundItem getGroundItem() {
		return BotScope.getContext().getGroundItems().closest(new Filter<GroundItem>() {
			boolean flag = false;
			@Override
			public boolean match(GroundItem i2) {
				if(!i2.getArea(10).contains(BotScope.getContext().myPosition()))
					return false;
				for(int excluded : excludedIds) { //bones,ashes
					if(i2.getId() == excluded)
					return false;
				}

				return true;
			}
			
		});
	}
}
