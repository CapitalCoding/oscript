package com.br.capitalcoding.task.condition.impl;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;
import com.br.capitalcoding.task.types.solution.SolutionManager;

public class LoggedIn extends PreConditionTask {

	public LoggedIn() {
		super(5, "logged-in-pre-condition", false, 0);
	}

	@Override
	public void run() {

	}

}
