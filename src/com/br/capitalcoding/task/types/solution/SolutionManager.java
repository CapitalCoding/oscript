package com.br.capitalcoding.task.types.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.manager.TaskManager;
import com.br.capitalcoding.task.types.PreConditionTask;
import com.br.capitalcoding.task.types.Solution;
import com.br.capitalcoding.task.types.SolutionTask;


public class SolutionManager {

	List<Solution> possibleSolutions = new LinkedList<>();
	//List<SolutionSet> poll = new LinkedList<>();
	
	SolutionSet lastSet;
	public void determineBestOption() {
		//List<SolutionTask> current = new LinkedList<>();
		List<PreConditionTask> current = new LinkedList<>();
		if(possibleSolutions.isEmpty())
			return;
		for(Solution solution : possibleSolutions) {
			for(ArrayList<SolutionSet> set : solution.taskOptions()) { //array
				for(SolutionSet innerTask : set) {
					//for(SolutionTask task: innerTask) {
					for(PreConditionTask task: innerTask) {
						current.add(task);
						BotScope.message("Task Solution added:"+task);
					}
				
				}
			}
		}

	}
	
	private List<PreConditionTask> getAllTasks() {
		//List<SolutionTask> current = new LinkedList<>();
		List<PreConditionTask> current = new LinkedList<>();
		if(possibleSolutions.isEmpty())
			return current;
		for(Solution solution : possibleSolutions) {
			for(ArrayList<SolutionSet> set : solution.taskOptions()) { //array
				for(SolutionSet innerTask : set) {
					//for(SolutionTask task: innerTask) {
					for(PreConditionTask task: innerTask) {
						current.add(task);
						BotScope.message("Task Solution added:"+task.getKey());
					}
				
				}
			}
		}
		return current;
	}
	private List<SolutionSet> retrieveSetsFromSolutions() {
		List<SolutionSet> current = new LinkedList<>();
		if(possibleSolutions.isEmpty())
			return current;
		for(Solution solution : possibleSolutions) {
			for(ArrayList<SolutionSet> set : solution.taskOptions()) { //array
				for(SolutionSet innerTask : set) {
						current.add(innerTask);
						for(Task tasks: innerTask)
						BotScope.message("Task SETS added:"+tasks.getKey());
					
				
				}
			}
		}
		return current;
	}
	public boolean meetCondition() {
		for(Solution solution : possibleSolutions) {
			if(!solution.meetCondition())
				return false;
		}
		return true;
	}
	public void putSolutionSetInQeue() {
		SolutionSet set = retrieveSetsFromSolutions().get(0);
		for(Task task : set) { //need create a method that chooses the best
			TaskManager.submit(task);
		}
		lastSet = set;
	}
	public List<Solution> getPoll() {
		return possibleSolutions;
	}

	public SolutionManager add(Solution solution) {
		possibleSolutions.add(solution);
		return this;
	}
}
