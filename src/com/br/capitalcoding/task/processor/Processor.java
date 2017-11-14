package com.br.capitalcoding.task.processor;

import java.util.ArrayList;
import java.util.Objects;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.listeners.Observer;
import com.br.capitalcoding.task.manager.TaskManager;
import com.br.capitalcoding.task.types.SolutionTask;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.task.types.GlobalAdapter;
import com.br.capitalcoding.task.types.PreConditionTask;

public class Processor {

	
	public static boolean processPreConditionTask(Task task) {
		try {
			ProcessingState state = StateAssigner.assignState(task);
    		BotScope.message("PRE STATE:"+state.name()+" "+task.getKeyName()+" P:"+task.isPaused()+
    				" Meet:"+((PreConditionTask) task).isMeetCondition()+
    				" MeetSub:"+((GlobalAdapter)task).getPreCondition().hasMeetPreCondition()+
    				" Running:"+task.isRunning()); 
    		PreConditionTask pre = (PreConditionTask) task;
			switch (state) {
			case COMPLETED_SUB_AND_MEET:		
				if(task.getTaskLinker() != null) {
					GlobalAdapter gl = (GlobalAdapter) task.getTaskLinker().getOwner();
					gl.setTaskState(task.getTaskLinker().getStateOnComplete());
				}
				task.stop();
				return false;
				//task.stop();
				// STHEFANNY
			case NEED_MEET_SUB_TASKS:
				/*ArrayList<Task> subTasks = Processor.unmeetTasks(task);
				for(Task subTask : subTasks) {     					
					TaskManager.submit(subTask);     
					BotScope.message("Colocou pre task:"+subTask.getKey());
					}*/
				PreConditionTask sub = pre.getPreCondition().next();
				if(sub != null) {
					TaskManager.submit(sub);     
					BotScope.message("Colocou task:"+sub.getKey());
				}
				task.setPaused(true);
				break;
			case WAITING_SUB_TASKS:
				break;
			case COMPLETED_SUB_TASKS:
				task.setPaused(false);
				break;
				
			default:
				break;
			}
    		BotScope.message("POS STATE:"+state.name()+" "+task.getKeyName()+" P:"+task.isPaused()+
    				" Meet:"+((PreConditionTask) task).isMeetCondition()+
    				" MeetSub:"+((GlobalAdapter)task).getPreCondition().hasMeetPreCondition()+
    				" Running:"+task.isRunning()); 
					
			/*BotScope.message("Iniciou process pre condition:"+t.getKey());
		PreConditionTask task = (PreConditionTask) t;
		ArrayList<Task> subTasks = Processor.unmeetTasks(task);
		boolean meetPre = task.getAdapter().condition().hasMeetPreCondition();
		BotScope.message("PreTask:"+task.getKey()+" p:"+task.isPaused()+
				" meet solutions"+task.solutions().meetCondition()+
				" meet cond:"+task.getAdapter().meetCondition()+
				" has failed:"+task.isFailed());
	
		
		
			if(!meetPre && !task.isPaused()) {
			BotScope.message("Entrou a pre task:current:"+task.getKey());
			for(Task subTask : subTasks) {     					
				TaskManager.submit(subTask);     
				BotScope.message("Colocou pre task:"+subTask.getKey());
			}
			task.setPaused(true);
			//task.setInterceptor(new ConditionTaskInterceptor(task, subTasks));
			BotScope.message("Pausou a "+task.getKey()); 		
		} else if(!meetPre && task.isPaused()) {
			BotScope.message("Waiting another pre task, current:"+task.getKey());   
		}
		
		if(meetPre && task.isPaused() && !task.isFailed()) {
				task.setPaused(false);
	    		//this.interceptor.getSubmitedTask().running = false;
	    		BotScope.message("pre Completed");
		}else if(meetPre && !task.isPaused() &&
				!task.getAdapter().meetCondition() &&
				task.isFailed() &&
				!task.solutions().meetCondition()) {
			task.setPaused(true);			
			task.solutions().putSolutionSetInQeue();
			BotScope.message("Inicia solução");
			
		}else if(meetPre && task.isPaused() &&
				!task.getAdapter().meetCondition() &&
				task.isFailed() &&
				task.solutions().meetCondition()) { //paused !meet failed meetSol
			task.setPaused(false);			
			BotScope.message("Completou PreCond");
		}else if(meetPre && 
				!task.isPaused() && 
					task.solutions().meetCondition() &&
					task.getAdapter().meetCondition()) {
			BotScope.message("InTask:"+task.getKey()+" p:"+task.isPaused()+
					" meet solutions"+task.solutions().meetCondition()+
					" meet cond:"+task.getAdapter().meetCondition()+
					" has failed:"+task.isFailed());
					task.setPaused(true);
					BotScope.message("Parou");
					
					if(task.getTaskLinker() != null) {
						GlobalAdapter gl = (GlobalAdapter) task.getTaskLinker().getOwner();
						gl.setTaskState(task.getTaskLinker().getStateOnComplete());
						//gl.getPreCondition().reset();
						BotScope.message("----------- UNPAUSED -----------"+gl.getKeyName()+" state:"+task.getTaskState().name());
					}
		}else if(meetPre && task.isPaused() && task.isFailed()){
			BotScope.message("Completou Tudo");
			task.stop();
		}else if(task.deleteAfterMeet() && task.getTaskExecutions() > 0){
			BotScope.message("Deletou e meet");
			task.stop();
		}else {				
			BotScope.message("PosTask:"+task.getKey()+" p:"+task.isPaused()+
					" meet solutions"+task.solutions().meetCondition()+
					" meet cond:"+task.getAdapter().meetCondition()+
					" has failed:"+task.isFailed());
		}
		
			
		

		/*if(task.deleteAfterMeet() && task.getAdapter().meetCondition()) {
			BotScope.message("Deleted:");
			task.stop();
		}*/
		
		}catch(Exception e) {
		      for (StackTraceElement s: e.getStackTrace())
		      {
		       BotScope.message("Exception: " + e.getMessage());
		       BotScope.message("Class: " + s.getClassName());
		       BotScope.message("Method Name: " + s.getMethodName());
		       BotScope.message("Line number: " + s.getLineNumber());
		      }
			 
		}
		return true;
	}
	public static boolean processGameTask(Task t) {
		try {
		ProcessingState state = StateAssigner.assignState(t);
		GameTask task = (GameTask) t;
		ArrayList<Task> subTasks = Processor.unmeetTasks(task);		
		BotScope.message("GAME STATE:"+state.name());
		BotScope.message("Game STATE:"+state.name()+" "+task.getKeyName()+" P:"+task.isPaused()+" MeetSub:"+((GlobalAdapter)task).getPreCondition().hasMeetPreCondition()); 
		
		switch(state) {
		case NEED_MEET_SUB_TASKS:
			PreConditionTask sub = task.getPreCondition().next();
			if(sub != null) {
				TaskManager.submit(sub);     
				BotScope.message("Colocou task:"+sub.getKey());
			}
			/*for(Task subTask : subTasks) {     					
				TaskManager.submit(subTask, true);     
				BotScope.message("Colocou task:"+subTask.getKey());
			}*/
			task.setPaused(true);
			break;
		case WAITING_SUB_TASKS:
    		BotScope.message("Waiting another task current :"+task.getKey()); 
    		break;
		case COMPLETED_SUB_TASKS:
			task.setPaused(false);
			BotScope.message("Completed Game");
			break;
		default:
			break;		
		}
	/*	BotScope.message("PreGameTask:"+task.getKey()+" p:"+task.isPaused()+
				" meet tasks cond:"+conditions.hasMeetPreCondition()+
				" has failed:"+task.isFailed());
		if((!conditions.hasMeetPreCondition())) {
			if((!task.isPaused())){ 
				BotScope.message("Entrou a task:current:"+task.getKey());
				for(Task subTask : subTasks) {     					
					TaskManager.submit(subTask, true);     
					BotScope.message("Colocou task:"+subTask.getKey());
				}
				//task.setInterceptor(new ConditionTaskInterceptor(task, subTasks));
				BotScope.message("Pausou a task"+task.getKey());
				task.setPaused(true);    
			} else if(task.isPaused()) {
	    		BotScope.message("Waiting another task current:"+task.getKey()); 
	    		BotScope.message("Status do outro:"+task.getPreCondition().getLastAdded().isRunning()); 
	    		
	    		//task.getInterceptor().compare(subTasks).forEach(i->BotScope.message("Sobrou:"+i.getKey()));
		}			
		} else if(conditions != null &&
				conditions.hasMeetPreCondition()) {
			
			if(task.isPaused()) {
				task.setPaused(false);
	    		//this.interceptor.getSubmitedTask().running = false;
	    		BotScope.message("Completed");
		}	
			
		}*/
		//BotScope.message("Terminou process game task");
		}catch(Exception e) {
		      for (StackTraceElement s: e.getStackTrace())
		      {
		       BotScope.message("Exception: " + e.getMessage());
		       BotScope.message("Class: " + s.getClassName());
		       BotScope.message("Method Name: " + s.getMethodName());
		       BotScope.message("Line number: " + s.getLineNumber());
		      }
			 
		}
		return true;
	}
	public static boolean processSolutionTask(Task t) {
		try {
		BotScope.message("Iniciou process critera:"+t.getKey());
		SolutionTask task = (SolutionTask) t;
		
		//Flusho de paused e unpaused aqu
		ArrayList<Task> subTasks = Processor.unmeetTasks(task);		
		PreCondition conditions = task.getPreCondition();
		if(conditions != null &&
				!task.getPreCondition().hasMeetPreCondition()) {
			if((!task.isPaused())){ 
				BotScope.message("Entrou a task:current:"+task.getKey());
				for(Task subTask : subTasks) {     					
					TaskManager.submit(subTask);     
					BotScope.message("Colocou task:"+subTask.getKey());
				}
				//task.setInterceptor(new ConditionTaskInterceptor(task, subTasks));
				BotScope.message("Pausou a task"+task.getKey());
				task.setPaused(true);    
			} else if(task.isPaused()) {
	    		BotScope.message("Waiting another task current:"+task.getKey());   
	    		//task.getInterceptor().compare(subTasks).forEach(i->BotScope.message("Sobrou:"+i.getKey()));
		}			
		} 
		if(conditions != null &&
			 task.getPreCondition().hasMeetPreCondition()) {	
			
			if(task.isPaused()) {
				task.setPaused(false);
	    		//this.interceptor.getSubmitedTask().running = false;
	    		BotScope.message("paused");
		} else {
			task.stop();
			BotScope.message("Completed");
		}
			
		}
	}catch(Exception e) {
	      for (StackTraceElement s: e.getStackTrace())
	      {
	       BotScope.message("Exception: " + e.getMessage());
	       BotScope.message("Class: " + s.getClassName());
	       BotScope.message("Method Name: " + s.getMethodName());
	       BotScope.message("Line number: " + s.getLineNumber());
	      }
		 
	}
		return true;
	}
	
	private static ArrayList<Task> unmeetTasks(Task task){
		ArrayList<Task> list = new ArrayList<>();
		PreCondition conditions = ((GlobalAdapter)task).getPreCondition();
		if(task instanceof GameTask) {		
			if(Objects.isNull(conditions))
				return list;
			return conditions.getUnmeetUpperTasks();
		}
		if(task instanceof PreConditionTask) {
			if(Objects.isNull(conditions))
				return list;
			return conditions.getUnmeetUpperTasks();
		}
		if(task instanceof SolutionTask) {
			if(Objects.isNull(conditions))
				return list;
			return conditions.getUnmeetUpperTasks();
		}
		return list;

	}
	
}
