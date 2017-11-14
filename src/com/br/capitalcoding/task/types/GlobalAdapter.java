package com.br.capitalcoding.task.types;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.osbot.rs07.script.MethodProvider;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.GameAdapter;
import com.br.capitalcoding.task.condition.GameCondition;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.TaskLinker;
import com.br.capitalcoding.task.condition.TaskLinkerManager;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.manager.TaskManager;

public abstract class GlobalAdapter extends Task{
	
	MethodProvider provider = BotScope.getContext();
	GameCondition adapter;
	boolean failed = false;
	private PreCondition preCondition = new PreCondition().none();
	private int retriesUntilStop;

	public GlobalAdapter(int delay, String key, boolean immediate,PreCondition preCondition, boolean meetCondition, int retriesUntilStop) {
		super(delay, key, immediate);
		this.preCondition = preCondition;
		this.retriesUntilStop = retriesUntilStop;
	}
	public GlobalAdapter(int delay, String key, boolean immediate, int retriesUntilStop) {
		super(delay, key, immediate);
		this.retriesUntilStop = retriesUntilStop;
	}
	public GlobalAdapter(GameCondition adapter, MethodProvider provider) {
		this(adapter.retryTimer(), adapter.keyValue().toString(), adapter.instant(), adapter.condition(), adapter.meetCondition(), adapter.retriesUntilStop());
		this.adapter = adapter;
		this.provider = provider;
	}
	
	public PreCondition getPreCondition() {		
		return conditionFor(taskState);
	}
	protected PreCondition conditionFor(TaskState state) {		
		return preCondition;
		
	}
	public PreCondition conditionForBanking() {
		return preCondition;
		
	}

	public boolean isMeetCondition() {
		return false;
	}
	public int getRetriesUntilStop() {
		return retriesUntilStop;
	}
	
	public boolean isFailed() {
		return failed;
	}
	public void setFailed(boolean failed) {
		this.failed = failed;
	}
	public abstract void run();
	private TaskState taskState = TaskState.NORMAL;
	
	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
		this.onStateBack();
	}
	protected void onStateBack() {
				
	}
	public void setTaskState(TaskState taskState, TaskState nextState) {
		//this.conditionFor(this.getTaskState()).pauseAll();
		this.taskState = taskState;				
		TaskLinkerManager.attach(this, getPreCondition().getLast(), nextState);
		String listString = this.getPreCondition().getConditions().stream().map(Task::getKeyName)
        .collect(Collectors.joining(", "));
		BotScope.message("STATE:"+listString+" "+this.getPreCondition().getLastAdded().getKeyName());

	}
	public TaskState getTaskState() {
		return taskState;
	}
	
	@Override
	public void execute() {
	run();		
	}
	
	public MethodProvider getProvider() {
		return BotScope.getContext();
	}

}
