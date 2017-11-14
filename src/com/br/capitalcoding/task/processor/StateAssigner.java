package com.br.capitalcoding.task.processor;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.task.types.GlobalAdapter;
import com.br.capitalcoding.task.types.PreConditionTask;

public class StateAssigner {

	public static ProcessingState assignState(Task t) {
		GlobalAdapter task = (GlobalAdapter) t;
		if(t instanceof PreConditionTask) 
		return forPreCondition(task);
		if(t instanceof GameTask) 
			return forGameTask(task);
		return null;

	}
	
	
	
	private static ProcessingState forGameTask(GlobalAdapter t){
			boolean paused = t.isPaused();
			boolean meetPreCondition = t.getPreCondition().hasMeetPreCondition();
			if(!meetPreCondition && !paused)
				return ProcessingState.NEED_MEET_SUB_TASKS;
			else if(!meetPreCondition && paused)
				return ProcessingState.NEED_MEET_SUB_TASKS;
			else if(meetPreCondition && paused)
				return ProcessingState.COMPLETED_SUB_TASKS;
			else
				return ProcessingState.NOT_HANDLED;
	}
	
	private static ProcessingState forPreCondition(GlobalAdapter t){
			boolean paused = t.isPaused();
			boolean meetPreCondition = t.getPreCondition().hasMeetPreCondition();
			boolean failed = t.isFailed(); //when task executions is over the retries
			boolean completed = ((PreConditionTask) t).isMeetCondition();
			
			if(!paused && completed)
				return ProcessingState.COMPLETED_SUB_AND_MEET;
			else if(paused && completed)
				return ProcessingState.COMPLETED_SUB_AND_MEET;
			else if(!meetPreCondition && !paused)
				return ProcessingState.NEED_MEET_SUB_TASKS;
			else if(!meetPreCondition && paused)
				return ProcessingState.NEED_MEET_SUB_TASKS;
			else if(meetPreCondition && paused)
				return ProcessingState.COMPLETED_SUB_TASKS;	
			else 
				return ProcessingState.NOT_HANDLED;
			/*if(!meetPreCondition && !paused) 
				return State.NEED_MEET_CONDITION;
			else if(!meetPreCondition && paused) 
				return State.PAUSED_MEETING_CONDITION;
			else if(meetPreCondition && paused && !failed) //meet paused !faled
				return State.IDLE;
			else if(!paused && !meetPreCondition && failed && !meetSolution)
				return State.NEED_MEET_SOLUTION;
			else if(paused && !meetPreCondition && failed && meetSolution) //paused !meet failed meetSol
				return State.COMPLETED_SOLUTION;
			else if(!paused && meetSolution && meetPreCondition) 
				return State.DONE_NEED_STOP;*/
	}
}
