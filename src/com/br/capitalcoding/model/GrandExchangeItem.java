package com.br.capitalcoding.model;

public class GrandExchangeItem {
  
	
	public GrandExchangeItem(int id, String name, double recent_high, double recent_low) {
		this.id = id;
		this.name = name;
		this.recent_high = recent_high;
		this.recent_low = recent_low;
	}
	public GrandExchangeItem(int id, String name) {
		this.id = id;
		this.name = name;
	}
	private final int id;
	private final String name;
	
	public double getRecent_high() {
		return recent_high;
	}
	public void setRecent_high(double recent_high) {
		this.recent_high = recent_high;
	}
	public double getRecent_low() {
		return recent_low;
	}
	public void setRecent_low(double recent_low) {
		this.recent_low = recent_low;
	}
	public double getAverage() {
		average = ((recent_high+recent_low) / 2);
		return average;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	private double recent_high,recent_low,average;
	
}
