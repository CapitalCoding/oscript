package com.br.capitalcoding.task.condition;

import java.util.ArrayList;
import java.util.List;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.types.PreConditionTask;

public class ConditionTaskInterceptor {

	Task task;
	ArrayList<PreConditionTask> submitedTasks;
	boolean flag = false;
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ArrayList<PreConditionTask> getSubmitedTasks() {
		return submitedTasks;
	}

	public ConditionTaskInterceptor(Task task, ArrayList<PreConditionTask> subTasks) {
	this.task = task;
	this.submitedTasks = subTasks;
	}
	//actual list of unmeet [a,b,c] [a,c]
	public List<PreConditionTask> compare(ArrayList<PreConditionTask> actual) {
		List<PreConditionTask> c = new ArrayList<>(actual);
		c.removeAll(submitedTasks);
		return c;
	}
	public Task getTask() {
		return task;
	}

}
