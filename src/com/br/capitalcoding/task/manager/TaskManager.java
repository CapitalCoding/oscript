package com.br.capitalcoding.task.manager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.types.PreConditionTask;
import com.br.capitalcoding.task.types.solution.SolutionSet;

public final class TaskManager {
 
    private final static Queue<Task> pendingTasks = new LinkedList<>();
 
    private final static List<Task> activeTasks = new LinkedList<>();
 
    public static Task getTask(String key) {
        try {
            Task t;
            while ((t = pendingTasks.poll()) != null) {
                if (t.getKeyName().equals(key)) {
                   return t;
                }

            }
        } catch(Exception e) {
		      for (StackTraceElement s: e.getStackTrace())
		      {
		       BotScope.message("Exception: " + e.getMessage());
		       BotScope.message("Class: " + s.getClassName());
		       BotScope.message("Method Name: " + s.getMethodName());
		       BotScope.message("Line number: " + s.getLineNumber());
		      }
        }
        return null;
    }
    public static boolean removeTask(String key) {
        try {
            Task t;
            while ((t = pendingTasks.poll()) != null) {
                if (t.getKeyName().equals(key)) {
                   return pendingTasks.remove(t);
                }

            }
            for(Task task : activeTasks) {
            	if(task.getKeyName().equals(key))
            		return activeTasks.remove(task);
            }
            
        } catch(Exception e) {
		      for (StackTraceElement s: e.getStackTrace())
		      {
		       BotScope.message("Exception: " + e.getMessage());
		       BotScope.message("Class: " + s.getClassName());
		       BotScope.message("Method Name: " + s.getMethodName());
		       BotScope.message("Line number: " + s.getLineNumber());
		      }
        }
        return false;
    }
    private TaskManager() {
        throw new UnsupportedOperationException(
                "This class cannot be instantiated!");
    }
    
    public void handlePoll() {
		

	}
    public static void sequence() {
        try {
            Task t;
            while ((t = pendingTasks.poll()) != null) {
                if (t.isRunning()) {
                    activeTasks.add(t);
                }

            }
            Iterator<Task> it = activeTasks.iterator();
            while (it.hasNext()) {
                t = it.next();
                BotScope.message("tICANDO:"+t.getKeyName()+" p:"+t.isPaused()+" "+t.getDelay());
                if (!t.tick()) {
                	t.setEventRunning(true);
                    it.remove();                 
                    it.forEachRemaining(i-> BotScope.message("Remain:"+i.getKeyName()));
                }
                if(t.getTaskTriesUntilStop() > 0) { //0 equals infinite
                	if(t.getTaskExecutions() > t.getTaskTriesUntilStop()) {
                		if(t instanceof PreConditionTask) {
                			PreConditionTask pre = (PreConditionTask) t;
                			pre.setFailed(true);
                		}
                	}
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
    }
   public static void pauseTask(Task t) {
    	t.setPaused(true);
	}
    public static void pauseUntilIsDone(Task task, Task pauser) {
    	
    	while(pauser.isRunning()) {
    		task.setDelay(1);
    	}
    		
    }
    public static void submit(Task task) {
        if(!task.isRunning()) {
        	BotScope.message("Ta running porra P:"+task.isPaused()+" r"+task.isRunning());
            return;
        }
        if (task.isImmediate()) {
            task.execute();
        }
        if(!pendingTasks.contains(task) && !activeTasks.contains(task)) {
        pendingTasks.add(task);
        BotScope.message("---------------------ADDED "+task.getKeyName()+" ---------------------");
        }else {
        	BotScope.message("++++++++++++++++++++NAOADDED "+task.getKeyName()+" ---------------------");
        }
       
    }

    private void remove() {
		

	}
    public static void cancelTasks(Object key) {
        try {
            pendingTasks.stream().filter(t -> t.getKey().equals(key)).forEach(t -> t.stop());
            activeTasks.stream().filter(t -> t.getKey().equals(key)).forEach(t -> t.stop());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
 
    public static int getTaskAmount() {
        return (pendingTasks.size() + activeTasks.size());
    }

}
