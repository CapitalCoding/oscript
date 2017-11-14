package com.br.capitalcoding.game.area;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public enum CityAreas {

	DRAYNOR(new Area(3087, 3248,3097, 3241)),
	VARROCK_WEST(new Area(3180, 3448,3191, 3433)),
	VARROCK2_EAST(new Area(3250, 3425,3256, 3418)),
	CANIFIS(new Area(3507, 3484,3516, 3476)),
	EDGEVILLE(new Area(3090, 3501,3098, 3488)),
	FALADOR_EAST(new Area(3008, 3359,3022, 3352)),
	FALADOR_WEST(new Area(2942, 3374,2949, 3366))
	;
	
	private Area bankArea;

	private CityAreas(Area bankArea) {
		this.bankArea = bankArea;
	}

	public Area getBankArea() {
		return bankArea;
	}

}
