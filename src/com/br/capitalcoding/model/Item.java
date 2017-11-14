package com.br.capitalcoding.model;

public class Item {

	int id, amount;
	private int costOfItem;
	private int itemsMade;
	private double gpGained;
	private double totalGpGained;
	public Item(int id, int amount) {
		super();
		this.id = id;
		this.amount = amount;
	}
	public Item(int id) {
		super();
		this.id = id;
		this.amount = 1;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getCostOfItem() {
		return costOfItem;
	}
	public void setCostOfItem(int costOfItem) {
		this.costOfItem = costOfItem;
	}
	public int getItemsMade() {
		return itemsMade;
	}
	public void setItemsMade(int itemsMade, boolean add) {
		if(add)
			this.itemsMade += itemsMade;
		else
		this.itemsMade = itemsMade;
	}

	public double getTotalGpGained() {
		return gpGained = itemsMade * costOfItem;
	}
	public void setTotalGpGained(double totalGpGained) {
		this.totalGpGained = totalGpGained;
	}
	public Item(int id,int amount, int costOfItem) {
		super();
		this.id = id;
		this.amount = 1;
		this.costOfItem = costOfItem;
		this.itemsMade = 0;
		this.gpGained = 0;
		this.totalGpGained = 0;
	}
}
