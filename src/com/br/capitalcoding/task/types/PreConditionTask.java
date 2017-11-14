package com.br.capitalcoding.task.types;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.impl.LoggedIn;
import com.br.capitalcoding.task.types.solution.SolutionManager;
import com.br.capitalcoding.task.types.solution.impl.LoginSolution;

public abstract class PreConditionTask extends GlobalAdapter{

	public PreConditionTask(int delay, String key, boolean immediate, int retriesUntilStop) {
		super(delay, key, immediate, retriesUntilStop);
	}
	public SolutionManager solutions() {
		return new SolutionManager().add(new LoginSolution());
	}
	public boolean deleteAfterMeet() {
		return false;
	}
	/**
	 * if(Precondition.add(new PreConditionTask()	{
	 * 
	 * 			@Override
			public int retryTimer() {
				return 30;
			}
			
			@Override
			public int retriesUntilStop() {
				return 2;
			}
			
			@Override
			public Object keyValue() {
				return "BuyAndSell";
			}
			
			@Override
			public boolean instant() {
				return false;
			}

			@Override
			public PreConditions condition() {
				return new PreConditions().none();
			}
			
			@Override
			public GameCondition taskCondition(){
			
			new GameCondition() {
			 
			 @Override
			 public Task meetTask(){
			 
			 return new MissingItem();
			}
				return null;
			}).
	 * 
	 */
}
