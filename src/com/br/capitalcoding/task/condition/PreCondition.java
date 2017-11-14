package com.br.capitalcoding.task.condition;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.util.Utils;
import com.br.capitalcoding.task.types.GlobalAdapter;
import com.br.capitalcoding.task.types.PreConditionTask;
public class PreCondition {
	
	private List<Task> conditions = new ArrayList<>();
	private Task lastAdded;

	public PreCondition add(Task condition) {	
		BotScope.message("Adding "+condition.getKeyName());
		conditions.add(condition);
		lastAdded = condition;
		/*String listString = conditions.stream().map(Task::getKeyName)
		        .collect(Collectors.joining(", "));
				BotScope.message("%------------%");
				BotScope.message(listString);*/
		return this;
	}
	public PreCondition remove(Task condition) {	
		conditions.add(condition);
		return this;
	}
	public boolean hasMeetPreCondition(Task task) {		
		return  ( (GlobalAdapter) getCondition(task).get()).isMeetCondition();
	}
	
	public ArrayList<Task> getUnmeetUpperTasks(){
		ArrayList<Task> list = new ArrayList<>();
		if(Objects.isNull(conditions)) 
			return list;
		for(Task tarefas : conditions) {
			if( !((GlobalAdapter) tarefas).isMeetCondition()) {
				//BotScope.message("Task not meet:"+tarefas.getKey());
				if(!Utils.keyExists(list, tarefas))
				list.add(tarefas);
			}
		}
		return list;
	}
	public PreConditionTask next() {
		Queue<Task> pendingTasks = new LinkedList<>(conditions);
		PreConditionTask t;
        while ((t = (PreConditionTask) pendingTasks.poll()) != null) {
            if (!t.isMeetCondition()) {
                return t;
            }

        }
		return null;
	}
	public PreConditionTask getLast() {
		PreConditionTask t;
       List<Task> task = getUnmeetUpperTasks();
       PreConditionTask last = (PreConditionTask) task.get(task.size() -1);
		return last;
	}
	/*public ArrayList<PreConditionTask> unmeetTaskUpperInner() {	
		ArrayList<PreConditionTask> unmeetTask =  new ArrayList<>();
		for(PreConditionTask tarefas : getUnmeetUpperTasks()) {
			if(!Utils.keyExists(unmeetTask, tarefas))
				unmeetTask.add(tarefas);
		}
		for(PreConditionTask tarefas : getUnmeetInnerTasks()) {
			if(!Utils.keyExists(unmeetTask, tarefas))
				unmeetTask.add(tarefas);
		}
		/*if(!conditions.isEmpty()) {
			for(PreConditionTask tarefas : conditions) {
				if( !((GameCondition) tarefas.getAdapter()).meetCondition()) {
					BotScope.context.log("Task not meet:"+tarefas.getKey());
					if(!Utils.keyExists(unmeetTask, tarefas))
					unmeetTask.add(tarefas);
				}
				if(tarefas.getAdapter().condition() != null) {
				for(PreConditionTask innerTasks : tarefas.getAdapter().condition().getConditions()) {
					BotScope.context.log("Inner Task unchecked:"+innerTasks.getKey());
					if( !((GameCondition) innerTasks.getAdapter()).meetCondition()) {
						BotScope.context.log("Inner Task not meet:"+innerTasks.getKey());
						if(!Utils.keyExists(unmeetTask, innerTasks))
						unmeetTask.add(innerTasks);
					}
				}	
				}
			}
		}
		//unmeetTask.stream().forEach(i->BotScope.message("TaskA:"+i.getKey()));
		return unmeetTask;
	}*/
	public boolean hasMeetPreCondition() {	
		if(conditions.isEmpty())
			return true;
		// nao completou = [1,2] = true
		for(Task tarefas : conditions) {
			if( !((GlobalAdapter) tarefas).isMeetCondition())
				return false;
		}		
		return true;
	}
	public void stopAll() {
		for(Task tarefas : conditions) {
			if(tarefas.isRunning()) {
				BotScope.message("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Tarefas parou:"+tarefas.getKeyName());
				tarefas.stop();
			}
		}	
	}
	public void pauseAll() {
		for(Task tarefas : conditions) {
			if(tarefas.isRunning()) {
				BotScope.message("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Tarefas pausou:"+tarefas.getKeyName());
				tarefas.setPaused(true);
			}
		}	
	}
	public Optional<Task> getCondition(Task task){
		return conditions.stream().filter(i->i.equals(task)).findFirst();
	}
	
	public PreCondition none() {
		return this;
	}

	public List<Task> getConditions() {
		return conditions;
	}
	public Task getLastAdded() {
		return lastAdded;
	}
	enum CustomState {		
		RESTOCK,
		BANK;
	}

}
