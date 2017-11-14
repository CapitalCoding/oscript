package com.br.capitalcoding.task.manager;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.types.SolutionTask;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.task.types.PreConditionTask;

public class Determinator {

	private Task task;

	public Determinator(Task task) {
		this.task = task;
	}

	public <T> Task  determineTaskType() {
    if( (this.task instanceof GameTask)) {	
		//return convertInstanceOfObject(this.task, GameTaskAdapter.class);
	}else if( (this.task instanceof PreConditionTask)) {	

	}else if( (this.task instanceof SolutionTask)) {	

	}
		return null;
	}

}
