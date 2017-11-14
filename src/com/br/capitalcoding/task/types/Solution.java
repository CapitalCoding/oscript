package com.br.capitalcoding.task.types;

import java.util.ArrayList;
import java.util.List;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.condition.Condition;
import com.br.capitalcoding.task.types.solution.SolutionSet;

public abstract class Solution implements Condition {

		protected MethodProvider context = BotScope.getContext();
		/**
		 * Each dimension has a sequence of tasks
		 * @return
		 */
		public abstract List<ArrayList<SolutionSet>> taskOptions();
		boolean solved;
		
		@Override
		public boolean meetCondition() {
		return taskOptions().isEmpty();
		}
}
