package com.br.capitalcoding.dao;

import java.util.List;
import java.util.Optional;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.types.PreConditionTask;

public interface PreConditionsDAO {
	
	/**
	 * Add task object to list
	 * @param task
	 * @return the same object 
	 */
	public PreCondition add(Task task);
	public List<Task> getConditions();
	public PreCondition none();
	public Optional<Task> getCondition(Task task);
	
}
