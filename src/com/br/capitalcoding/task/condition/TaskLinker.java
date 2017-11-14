package com.br.capitalcoding.task.condition;

import com.br.capitalcoding.model.Task;

public class TaskLinker {

	private Task owner;
	private Task dependent;
	private TaskState stateOnComplete;

	public TaskLinker(Task owner, Task dependent, TaskState stateOnComplete) {
		this.owner = owner;
		this.dependent = dependent;
		this.stateOnComplete = stateOnComplete;
	}

	public Task getOwner() {
		return owner;
	}

	public Task getDependent() {
		return dependent;
	}

	public TaskState getStateOnComplete() {
		return stateOnComplete;
	}

}
