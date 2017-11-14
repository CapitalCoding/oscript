package com.br.capitalcoding.task.types.solution.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.Solution;
import com.br.capitalcoding.task.types.SolutionTask;
import com.br.capitalcoding.task.types.solution.SolutionSet;

public class LoginSolution extends Solution {

	@Override
	public boolean meetCondition() {
		return context.getClient().isLoggedIn();
	}
			
	@Override
	public List<ArrayList<SolutionSet>> taskOptions() {
		SolutionSet set = new SolutionSet(); //set
		ArrayList<SolutionSet> sets = new ArrayList<SolutionSet>() {{
			add(set);
		}};
		return Arrays.asList(sets);
	}


}
class LoginTask extends SolutionTask {

	public LoginTask(MethodProvider provider) {
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
				return "Login in";
			}
			
			@Override
			public boolean instant() {
				return false;
			}
			
			@Override
			public PreCondition condition() {
				return new PreCondition().none();
			}
			
			@Override
			public boolean meetCondition() {
				return provider.getClient().isLoggedIn();
			}
		}, provider);
	}

	@Override
	public void run() {
		//getProvider().getBot().log
		BotScope.message("Era pra logar");
		
	}
	
}
