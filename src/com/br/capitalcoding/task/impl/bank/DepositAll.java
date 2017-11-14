package com.br.capitalcoding.task.impl.bank;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.impl.walk.BeInArea;
import com.br.capitalcoding.task.types.PreConditionTask;

public class DepositAll extends PreConditionTask{

	private String keep;
	public DepositAll(Area bank) {
		super(1, "Banking all", false, 0);
		BotScope.message("Bank area:"+bank.toString());
		conditions = new PreCondition().add(new StayNearBank(bank)).add(new OpenBank());
	}
	public DepositAll(Area bank, String keep) {
		super(1, "Banking all", false, 0);
		BotScope.message("Bank area:"+bank.toString());
		conditions = new PreCondition().add(new StayNearBank(bank)).add(new OpenBank());
		this.keep = keep;
	}
	final PreCondition conditions;
	
	Filter<Item> toBank = new Filter<Item>() {
		@Override
		public boolean match(Item i) {
			if(keep != null && i.nameContains(keep))
				return false;
			return true;
		}
		
	};
	@Override
	public boolean isMeetCondition() {
		if(keep != null) {
			return !getProvider().getInventory().contains(toBank);
		}
		return getProvider().getInventory().isEmpty();
	}
	@Override
	public PreCondition getPreCondition() {
		return conditions;
	}

	@Override
	public void run() {
		BotScope.message("Inicou deposit bankOpen:"+getProvider().getBank().isOpen());
		if(getProvider().getBank().isOpen()) {
			getProvider().getBank().depositAll(toBank);
		}
		
	}

	
}
