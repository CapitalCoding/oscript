package com.br.capitalcoding.scripts;

import org.osbot.rs07.api.def.ItemDefinition;
import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.Item;
import org.osbot.rs07.api.model.NPC;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.antiban.AntibanVariations;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.impl.bank.DepositAll;
import com.br.capitalcoding.task.impl.bank.DepositInBank;
import com.br.capitalcoding.task.impl.bank.WithdrawItem;
import com.br.capitalcoding.task.impl.item.EquipmentMeet;
import com.br.capitalcoding.task.impl.item.TakeGroundItem;
import com.br.capitalcoding.task.impl.walk.BeInArea;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.util.Utils;

public class Shearer extends GameTask{

	private Area area;

	public Shearer() {
		super(2, "Cortador", false, 0);
		this.area = new Area(3193,3257,3210,3275);//new EquipmentMeet(Utils.itemFilter(1735)
		conditions = new PreCondition().add(new TakeGroundItem(1735)).add(new BeInArea(area));
		banking = new PreCondition().add(new DepositAll(Banks.LUMBRIDGE_UPPER, "Shears"));
	}
	final PreCondition conditions;
	final PreCondition banking;
	
	@Override
	protected PreCondition conditionFor(TaskState state) {
		switch (state) {
		case NORMAL:		
			return conditions;
		case BANKING:
			return banking;
		default:
			break;
		}
		return conditions;
	}
	public static int TimesInBank = 0;
	@Override
	protected void onStateBack() {
		Shearer.TimesInBank ++;
	}
	@Override
	public void run() {	
		
		if(getProvider().getInventory().isFull())
			setTaskState(TaskState.BANKING, TaskState.NORMAL);
		
		switch (this.getTaskState()) {
		case NORMAL:
			NPC sheep = getSheep();
			if(sheep != null && sheep.interact("Shear")) {
				if(Utils.random(3) == 0) {
					Utils.sleep(300, 1000);
					AntibanVariations.getAnyOfAny().run();
				}
			}
			break;

		default:
			break;
		}
	}
	@SuppressWarnings({ "unused", "unchecked" })
	private NPC getSheep() {
		return BotScope.getContext().getNpcs().closest(new Filter<NPC>() {

			@Override
			public boolean match(NPC n) {
				if(n.hasAction("Shear") && !n.hasAction("Talk-to") && area.contains(n))
					return true;
				return false;
			}
		});

	}
}
