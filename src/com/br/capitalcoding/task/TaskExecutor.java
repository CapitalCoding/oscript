package com.br.capitalcoding.task;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.processor.Processor;
import com.br.capitalcoding.task.types.SolutionTask;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.task.types.PreConditionTask;

public class TaskExecutor {

	private final Task task;
	private boolean gameTask,preCondition,criteriaOption;
	
	public TaskExecutor(Task task) {
		this.task = task;
	}

	public boolean execute() {	
	if((this.task instanceof GameTask)) {
		this.gameTask = true;
		return Processor.processGameTask(this.task);	
	}else if( (this.task instanceof PreConditionTask)) {	
		this.preCondition = true;
		return Processor.processPreConditionTask(this.task);
	}else if( (this.task instanceof SolutionTask)) {
		this.criteriaOption = true;
		return Processor.processSolutionTask(this.task);
	
	}
	BotScope.message("Retorna falso wtf");
	return false;
	
	}

	public boolean isGameTask() {
		return gameTask;
	}

	public boolean isPreCondition() {
		return preCondition;
	}

	public boolean isCriteriaOption() {
		return criteriaOption;
	}	
	
}
