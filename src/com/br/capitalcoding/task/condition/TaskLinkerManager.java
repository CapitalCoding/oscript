package com.br.capitalcoding.task.condition;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.manager.TaskManager;

public class TaskLinkerManager {

	/**
	 * Notifies the task owner on completition
	 * @param task
	 * @param task2
	 * @param stateAfter
	 */
	public static void attach(Task owner, Task dependent, TaskState stateAfter) {
		dependent.setTaskLinker(new TaskLinker(owner, dependent, stateAfter));
		//TaskManager.submit(dependent);
	}
}
