package com.br.capitalcoding.task.impl.bank;

import org.osbot.rs07.api.Bank;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.game.area.CityAreas;
import com.br.capitalcoding.model.Item;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.types.PreConditionTask;

public class DepositInBank extends PreConditionTask{

	private Item item;
	
	private Area bank;

	public DepositInBank(Item item, Area bank) {
		super(1, "Depositing in Bank", false, 0);
		this.item = item;
		this.bank = bank;
	}
	PreCondition conditions = new PreCondition().add(new StayNearBank(bank)).add(new OpenBank());
	@Override
	public boolean isMeetCondition() {
		return !getProvider().getInventory().contains(item.getId());
	}
	@Override
	public PreCondition getPreCondition() {
		return conditions;
	}

	@Override
	public void run() {
		BotScope.message("Inicou deposit bankOpen:"+getProvider().getBank().isOpen());
		if(getProvider().getBank() != null && getProvider().getBank().isOpen()) {
			if(!getProvider().getInventory().contains(item.getId()))
				this.stop();
			long amount = getProvider().getInventory().getAmount(item.getId());
			getProvider().getBank().deposit(item.getId(), (int) amount);
		}
		
	}
	@Override
	public boolean deleteAfterMeet() {
		return false;
	}

}
