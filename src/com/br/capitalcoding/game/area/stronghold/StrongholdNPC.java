package com.br.capitalcoding.game.area.stronghold;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

import org.osbot.rs07.api.map.Area;

import com.br.capitalcoding.BotScope;

public enum StrongholdNPC {
	FLESH_CRAWLER(new Area(1989,5234,1996,5244)),
	GIANT_RAT(new Area(2012,5234,2022,5238)),
	GOBLINS(new Area(1858,5214, 1865,5227)),
	SPIDERS(new Area(2121,5267, 2134, 5275)),
	SPIDERS2(new Area(2143,5302, 2156, 5309));
	
	
	public Area getArea() {
		return area;
	}

	private Area area;

	private StrongholdNPC(Area area) {
		this.area = area;
	}
	public static Optional<StrongholdNPC> getCurrentArea() {
		return Arrays.asList(
				StrongholdNPC.values())
				.stream().filter(i->i.getArea().contains(BotScope.getContext().myPosition()))
				.findFirst();
		
	}
	/**
	new HashMap<String, Area>(){{
	put("Flesh Crawler", new Area(1989,5234,1996,5244));
	put("Giante Rats", new Area(2012,5234,2022,5238));
	}}*/
}
