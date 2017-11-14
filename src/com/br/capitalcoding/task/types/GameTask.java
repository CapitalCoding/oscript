package com.br.capitalcoding.task.types;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.GameAdapter;
import com.br.capitalcoding.task.condition.GameCondition;


public abstract class GameTask extends GlobalAdapter{

	public GameTask(int delay, String key, boolean immediate, int retriesUntilStop) {
		super(delay, key, immediate, retriesUntilStop);
	}
}
