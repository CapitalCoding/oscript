package com.br.capitalcoding.task.cancellation;

public class Cancellation {

	public Cancellation(boolean judment) {
		this.judment = judment;
	}
	private final boolean judment;
	/**
	 * When judment is true it automatically cancel the task
	 * @return
	 */
	
	public boolean isJudment() {
		return judment;
	}
}
