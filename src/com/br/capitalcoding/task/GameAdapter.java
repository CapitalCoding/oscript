package com.br.capitalcoding.task;

import com.br.capitalcoding.task.condition.PreCondition;

public interface GameAdapter {

	public boolean instant();
	public Object keyValue();
	public int retryTimer();
	public int retriesUntilStop();
	public PreCondition condition();
	
}
