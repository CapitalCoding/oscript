package com.br.capitalcoding.exceptions;

public class GrandExchangeItemIdNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		return "This GrandExchange Item isn't in memory.";
	}
}
