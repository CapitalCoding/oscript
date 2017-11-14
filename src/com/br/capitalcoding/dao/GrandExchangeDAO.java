package com.br.capitalcoding.dao;

import java.util.List;

import com.br.capitalcoding.model.GrandExchangeItem;
public interface GrandExchangeDAO {

	  public List<GrandExchangeItem> getAllGEItems();
	   public GrandExchangeItem getGEItemById(int rollNo);
	   public GrandExchangeItem getGEItemByName(String query);
	   public void updateItem(GrandExchangeItem item, int high, int low);
	   public void deleteItem(GrandExchangeItem item);
}
