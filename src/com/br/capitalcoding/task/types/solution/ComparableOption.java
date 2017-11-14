package com.br.capitalcoding.task.types.solution;

import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.types.GlobalAdapter;

public class ComparableOption {

	private Task task;
	public ComparableOption(Task task) {
		this.task = task;
	}
	public int sumAdvantage() {
		GlobalAdapter t = (GlobalAdapter) task;
	
		for(Advantage ad : Advantage.values()) {
			
		}
		return 0;
	}
	
}
