package com.br.capitalcoding.grandexchange;

import java.util.ArrayList;
import java.util.List;
import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.GrandExchangeItem;

public class GrandExchangeProperties {

	List<GrandExchangeItem> flippable = new ArrayList<>();
	
	public static void loadFlippables() {
		/*for(Flippable flip : Flippable.values()) {
			if(flip.getId().length > 1) {
				for(int o = 0; o < flip.getId().length; o++) {
					BotScope.getGeContext().getAllGEItems().add(new GrandExchangeItem(flip.getId()[o], flip.getName()+flip.getNameVariations()[o]));			
				}
			}else
				BotScope.getGeContext().getAllGEItems().add(new GrandExchangeItem(flip.getId()[0], flip.getName()));
		}*/
		/*Arrays.asList(Flippable.values()).forEach(i-> {
			if(i.getId().length > 1) {
				for(int o = 0; o < i.getId().length; o++) {
					BotScope.getGeContext().getAllGEItems().add(new GrandExchangeItem(i.getId()[o], i.getName()+i.getNameVariations()[o]));			
				}
			}else
				BotScope.getGeContext().getAllGEItems().add(new GrandExchangeItem(i.getId()[0], i.getName()));
		});*/
	}
	
	enum Flippable{
		STR_POT("Strenght Potion",
				new String[] {"(4)","(3)","(2)","(1)"},
				new int[] {113,115,117,119}),
		BONES("Bones", 526);
		
		private String name;
		private String[] nameVariations;
		private int[] id;
		private Flippable(String name, String[] nameVariations, int[] id) {
			this.name = name;
			this.nameVariations = nameVariations;
			this.id = id;
		}
		private Flippable(String name, int id) {
			this.name = name;
			this.nameVariations = null;
			this.id = new int[] {id};
		}
		public String getName() {
			return name;
		}
		public String[] getNameVariations() {
			return nameVariations;
		}
		public int[] getId() {
			return id;
		}
	}
}
