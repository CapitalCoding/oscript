package com.br.capitalcoding.task.types.solution;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.model.Item;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.SolutionTask;

public class MissingEquipmentSolution extends SolutionTask {

	public MissingEquipmentSolution(Item item , MethodProvider provider) {
		super(new GameCondition() {
			
			@Override
			public int retryTimer() {
				return 0;
			}
			
			@Override
			public int retriesUntilStop() {
				return 0;
			}
			
			@Override
			public Object keyValue() {
				return null;
			}
			
			@Override
			public boolean instant() {
				return false;
			}
			
			@Override
			public PreCondition condition() {
				return null;
			}
			
			@Override
			public boolean meetCondition() {
				return false;
			}
		}, provider);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
