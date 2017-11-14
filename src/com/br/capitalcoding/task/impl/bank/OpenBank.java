package com.br.capitalcoding.task.impl.bank;

import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;

public class OpenBank extends PreConditionTask{

	public OpenBank() {
		super(1, "Open Bank", false, 0);
	}
	
	@Override
	public boolean isMeetCondition() {
		return BotScope.getContext().getBank().isOpen();
	}
	@Override
	public void run() {
		try {
			
			RS2Object bank = getProvider().getObjects().closest("Bank booth");
			BotScope.message("Abrindo bank"+bank.exists());
			if(bank != null) {
				if(getProvider().getCamera().toEntity(bank))
				getProvider().getBank().open();
			}
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
