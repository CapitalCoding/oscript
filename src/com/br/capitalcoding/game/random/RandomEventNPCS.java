package com.br.capitalcoding.game.random;

import java.util.Arrays;

import com.br.capitalcoding.BotScope;

public enum RandomEventNPCS {

	BEEKEEPER,
	CAPT,
	ARNAV,
	NILES,
	GILES,
	SEAGEANT_DAMIEN,
	DRUNKEN_DWARF,
	FREAKY_FORESTER,
	FROGS,
	PRINCE,
	PRINCESS,
	GENIE,
	EVIL_BOB,
	PROSTIE_PETE,
	LEO,
	JEKYLL,
	HYDE,
	MYSTERIOUS_OLD_MAN,
	MIME,
	PILLORY_GUARD,
	FLIPPA,
	TILT,
	QUIZ_MASTER,
	RICK_TURPENTINE,
	SANDWICH_LADY,
	SECURITY_GUARD,
	STRANGE_PLANT,
	MR_MORDAUT;
	
	public static final String[] RANDOM_ID = {"Beekeeper","Capt' Arnav","Niles","Miles","Giles","Sergeant Damien","Drunken dwarf",
			"Freaky Forester","Frogs","Prince","Princess","Genie","Evil Bob","Postie Pete",
			"Leo","Jekyll","Hyde","Mysterious Old Man","Mime","Pillory Guard","Flippa","Tilt",
			"Quiz Master","Rick Turpentine","Sandwich lady","Security Guard","Strange plant","Mr. Mordaut"};
	
	public static boolean isNearby() {
		for(RandomEventNPCS npc : RandomEventNPCS.values()) {		
			if(BotScope.getContext().getNpcs().closest(npc.name()).exists())
				return true;
		}
		return false;
	}
}
