package com.br.capitalcoding.task.types;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.condition.GameCondition;

public abstract class SolutionTask extends GlobalAdapter {

	public SolutionTask(GameCondition adapter, MethodProvider provider) {
		super(adapter, provider);
	}
	

}
