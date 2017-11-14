package com.br.capitalcoding.game.area.stronghold;

import java.util.HashMap;
import java.util.Map;

import org.osbot.rs07.api.map.Area;

import com.br.capitalcoding.task.impl.walk.StrongholdPath;

public enum StrongholdAreas {

	WAR(new Area(1858, 5239, 1865, 5244), StrongholdNPC.GOBLINS),
	FAMINE(new Area(2040,5240, 2046, 5245), StrongholdNPC.FLESH_CRAWLER),
	PESTILENCE(new Area(2116,5250,2133,5256), StrongholdNPC.SPIDERS)
	;

	
	
	public StrongholdNPC getNpcLocations() {
		return npcLocations;
	}
	public Area getEntranceArea() {
					return entranceArea;
				}

	private Area entranceArea;
	private StrongholdNPC npcLocations;
	private StrongholdAreas(Area entranceArea, StrongholdNPC npcLocations) {
		this.entranceArea = entranceArea;
		this.npcLocations = npcLocations;
	}
}
enum War {
	GOBLINS;
}
