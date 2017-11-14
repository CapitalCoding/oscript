package com.br.capitalcoding.task.impl.bank;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Item;

import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;

public class WithdrawItem extends PreConditionTask{

	private Filter<Item> item;

	public WithdrawItem(Area bank, Filter<Item> item) {
		super(5, "Withdraw", false, 0);
		condition = new PreCondition().add(new StayNearBank(bank)).add(new OpenBank());
		this.item = item;
	}
	final PreCondition condition;
	
	@Override
	public void run() {	
		if(getProvider().getBank().isOpen()) {
			getProvider().getBank().withdraw(this.item, 1);
		}
	}

}
