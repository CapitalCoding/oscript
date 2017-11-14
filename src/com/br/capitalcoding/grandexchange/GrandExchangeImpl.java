package com.br.capitalcoding.grandexchange;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.br.capitalcoding.dao.GrandExchangeDAO;
import com.br.capitalcoding.model.GrandExchangeItem;

public class GrandExchangeImpl implements GrandExchangeDAO {

	private List<GrandExchangeItem> items;
	
	public GrandExchangeImpl() {
		items = new ArrayList<>();
	}
	@Override
	public GrandExchangeItem getGEItemById(int rollNo) {
		Optional<GrandExchangeItem> item = items.stream().
				filter(i-> i.getId() == rollNo).
				findFirst();
		if(!item.isPresent())
			return null;
		return item.get();
	}

	@Override
	public GrandExchangeItem getGEItemByName(String rollNo) {
		Optional<GrandExchangeItem> item = items.stream().
				filter(i-> i.getName().contains(rollNo)).
				findFirst();
		if(!item.isPresent())
			return null;
		return item.get();
	}

	@Override
	public void updateItem(GrandExchangeItem item, int high, int low) {
			Optional<GrandExchangeItem> itemOnList = items.stream().filter(i-> i.equals(item)).findFirst();
				itemOnList.ifPresent(i->{
					i.setRecent_high(high);
					i.setRecent_low(low);
					});
						
	}

	@Override
	public void deleteItem(GrandExchangeItem item) {
		Optional<GrandExchangeItem> itemOnList = items.stream().filter(i-> i.equals(item)).findFirst();
		if(itemOnList.isPresent())
			items.remove(item);
	}

	@Override
	public List<GrandExchangeItem> getAllGEItems() {		
		return items;
	}

}
