package com.br.capitalcoding.task.impl.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.impl.bank.OpenBank;
import com.br.capitalcoding.task.impl.bank.StayNearBank;
import com.br.capitalcoding.task.impl.bank.WithdrawItem;
import com.br.capitalcoding.task.types.PreConditionTask;
import com.br.capitalcoding.task.types.Solution;
import com.br.capitalcoding.task.types.solution.SolutionManager;
import com.br.capitalcoding.task.types.solution.SolutionSet;

public class EquipmentMeet extends PreConditionTask{

	final private Filter<Item> item;
	final private PreCondition conditions;

	// Script ->  

	public EquipmentMeet(Filter<Item> item) {
		super(5, "Equipment meet", true, 2);
		this.conditions = new PreCondition().add(new WithdrawItem(Banks.LUMBRIDGE_UPPER, item));
		this.item = item;
	}
	
	@Override
	public boolean isMeetCondition() {
		return (getProvider().getInventory().contains(this.item) || getProvider().getEquipment().contains(this.item));
	}
	@Override
	public void run() {
		BotScope.message("Running Equip");
		/*if(getProvider().getInventory().contains(item.getId())) {
			getProvider().getInventory().interact("Wield", item.getId());
		}*/
	}

	@Override
	public SolutionManager solutions() {
		return new SolutionManager().add(new Solution() {
			SolutionSet set = new SolutionSet(new StayNearBank(Banks.EDGEVILLE));//, new OpenBank(), new WithdrawFromBank(item)); //set		
			@Override
			public List<ArrayList<SolutionSet>> taskOptions() {				
				ArrayList<SolutionSet> sets = new ArrayList<SolutionSet>() {{
					add(set);
				}};
				return Arrays.asList(sets);
			}
		}); 
	}
}
