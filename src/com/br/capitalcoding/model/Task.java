package com.br.capitalcoding.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.TaskExecutor;
import com.br.capitalcoding.task.condition.ConditionTaskInterceptor;
import com.br.capitalcoding.task.condition.TaskLinker;
import com.br.capitalcoding.task.listeners.EventObserverAdapter;
import com.br.capitalcoding.task.listeners.Observer;
import com.br.capitalcoding.task.manager.TaskManager;

/**
 * Represents a periodic task that can be scheduled with a {@link TaskScheduler}.
 * @author Graham
 */
public abstract class Task {
  
	private boolean paused = false;
	private int taskExecutions = 0;
	private int taskTriesUntilStop = -1;
	private ConditionTaskInterceptor interceptor;
	public boolean flagged = false;
	private TaskExecutor executor = new TaskExecutor(this);
	//private EventListener listeners = new ArrayList<>();
	public List<Observer> listeners = new ArrayList<>();
	private TaskLinker taskLinker;

	
    /** The default key for every task. */
    public static final Object DEFAULT_KEY = new Object();
     
    /**
     * The number of cycles between consecutive executions of this task.
     */
    private int delay;
  
    /**
     * A flag which indicates if this task should be executed once immediately.
     */
    private final boolean immediate;
  
    /**
     * The current 'count down' value. When this reaches zero the task will be
     * executed.
     */
    private int countdown;
  
    public int getCountdown() {
        return countdown;
    }
 
    /**
     * A flag which indicates if this task is still running.
     */
    private boolean running = true;
  
    public void setEventRunning(boolean running) {
        this.running = running;
    }
  
    /**
     * The task's owner
     */
    private Object key;
  
    public final Object getKey() {
        return Objects.requireNonNull(key);
    }
    public final String getKeyName() {
        return (String) Objects.requireNonNull(key.toString());
    }
    public final Task bind(Object key) {
        this.key = Objects.requireNonNull(key);
        return this;
    }
  
    /**
     * Creates a new task with a delay of 1 cycle.
     */
    public Task() {
        this(1);
    }
    
    /**
     * Creates a new task with a delay of 1 cycle and immediate flag.
     * @param immediate A flag that indicates if for the first execution there
     * should be no delay.
     */
    public Task(boolean immediate) {
        this(1, immediate);
    }
  
    /**
     * Creates a new task with the specified delay.
     * @param delay The number of cycles between consecutive executions of this
     * task.
     * @throws IllegalArgumentException if the {@code delay} is not positive.
     */
    public Task(int delay) {
        this(delay, false);
        this.bind(DEFAULT_KEY);
    }
  
    /**
     * Creates a new task with the specified delay and immediate flag.
     * @param delay The number of cycles between consecutive executions of this
     * task.
     * @param immediate A flag which indicates if for the first execution there
     * should be no delay.
     * @throws IllegalArgumentException if the {@code delay} is not positive.
     */
    public Task(int delay, boolean immediate) {
        this.delay = delay;
        this.countdown = delay;
        this.immediate = immediate;
        this.bind(DEFAULT_KEY);
    }
  
    /**
     * Creates a new task with the specified delay and immediate flag.
     * @param delay The number of cycles between consecutive executions of this
     * task.
     * @param immediate A flag which indicates if for the first execution there
     * should be no delay.
     * @throws IllegalArgumentException if the {@code delay} is not positive.
     */
    public Task(int delay, Object key, boolean immediate) {
        this.delay = delay;
        this.countdown = delay;
        this.immediate = immediate;
        this.bind(key);
    }
  
    /**
     * Checks if this task is an immediate task.
     * @return {@code true} if so, {@code false} if not.
     */
    public boolean isImmediate() {
        return immediate;
    }
  
    /**
     * Checks if the task is running.
     * @return {@code true} if so, {@code false} if not.
     */
    public boolean isRunning() {
        return running;
    }
  
    /**
     * Checks if the task is stopped.
     * @return {@code true} if so, {@code false} if not.
     */
    public boolean isStopped() {
        return !running;
    }
  
    /**
     * This method should be called by the scheduling class every cycle. It
     * updates the {@link #countdown} and calls the {@link #execute()} method
     * if necessary.
     * @return A flag indicating if the task is running.
     */
    public boolean tick() {
    	
    	//executor.execute();
    	/*if( (this instanceof GameTask)) {	
    		GameTask task =  ((GameTask)this);    			
    			//Flusho de paused e unpaused aqu
    			ArrayList<PreConditionTask> subTasks = task.getAdapter().condition().getUnmeetUpperTasks();
    			subTasks.forEach(i-> BotScope.message("Unmeet:"+i.getKey()));
    			if(!task.getAdapter().condition().hasMeetPreCondition()) {
        			if((!this.isPaused())){ 
        				BotScope.message("Entrou a task");
        				for(Task subTask : subTasks) {     					
        					TaskManager.submit(subTask);     
        					BotScope.message("Colocou task:"+subTask.getKey());
        				}
        				this.setInterceptor(new ConditionTaskInterceptor(task, subTasks));
        				BotScope.message("Pausou a task");
        				this.paused = true;     
        			} else if(this.isPaused()) {
        	    		BotScope.message("Waiting another task");   
        	    		this.getInterceptor().compare(subTasks).forEach(i->BotScope.message("Sobrou:"+i.getKey()));
        		}
        			
    			} else {
    				if(this.isPaused()) {
    		    		this.paused = false;
    		    		//this.interceptor.getSubmitedTask().running = false;
    		    		BotScope.message("Completed");
    			}

    		
    	}
    			
    	}*/
    	/*if((this instanceof PreConditionTask)){
    		BotScope.context.log("pASSOU:"+this.getKey());
    		PreConditionTask task = (PreConditionTask) this;
    		if( ((GameCondition) task.getAdapter()).meetCondition()) {
    			task.setEventRunning(false);
    		}else {
    			if(this.getTaskExecutions() > 2) {
    				
    			}
    			//this.paused = true;
    			if( task.possibleMeetCriteriaTask() != null && 
    					!task.possibleMeetCriteriaTask().getConditions().isEmpty()) {
    				
    			}
    		
    			BotScope.context.log("Pausado:"+this.getKey());
    		}
    		
    		BotScope.context.log("PreCon:"+this.getKey());
    	}
    	if( (this instanceof SolutionTask)){
    		
    	}*/
        if (--countdown <= 0 && executor.execute() && running && !paused ) {
            execute();
            taskExecutions++;		
            countdown = delay;
        
    	}else {
    		 BotScope.message("NAO TICK:"+this.getKeyName()+" p:"+this.isPaused()+" "+this.getDelay()+
    				 " r:"+this.isRunning()+" cd:"+countdown);
    	}
        return running;
    }
  
    /**
     * Performs this task's action.
     */
    public abstract void execute();
  
    /**
     * Changes the delay of this task.
     * @param delay The number of cycles between consecutive executions of this
     * task.
     */
    public void setDelay(int delay) {
        if(delay > 0)
            this.delay = delay;
    }
  
    public int getDelay() {
        return this.delay;
    }
  
    /**
     * Stops this task.
     */
    public void stop() {
    	//TaskManager.cancelTasks(this.getKey());
        running = false;
    }


	public int getTaskExecutions() {
		return taskExecutions;
	}

	public int getTaskTriesUntilStop() {
		return taskTriesUntilStop;
	}

	public void setTaskTriesUntilStop(int taskTriesUntilStop) {
		this.taskTriesUntilStop = taskTriesUntilStop;
	}
	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public ConditionTaskInterceptor getInterceptor() {
		return interceptor;
	}

	public void setInterceptor(ConditionTaskInterceptor interceptor) {
		this.interceptor = interceptor;
	}

	public TaskLinker getTaskLinker() {
		return taskLinker;
	}

	public void setTaskLinker(TaskLinker taskLinker) {
		this.taskLinker = taskLinker;
	}
}