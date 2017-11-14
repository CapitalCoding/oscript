package com.br.capitalcoding.task.processor;

public enum ProcessingState {
		IDLE, 
		NEED_MEET_SOLUTIONS, 
		NEED_MEET_SUB_TASKS, 
		WAITING_SUB_TASKS,
		COMPLETED_SUB_TASKS,
		COMPLETED_SUB_AND_MEET,
		NOT_HANDLED;
		
}
