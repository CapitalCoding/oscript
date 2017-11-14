package com.br.capitalcoding.gui.stronghold;

import javax.swing.ImageIcon;

public enum GuiFloors {

	WAR(new ImageIcon("raw/war.png")) {
		@Override
		public String[] npcs() {
			return new String[] {"Goblins","Minotaurs","Wolves"};
		}
	},
	FAMINE(new ImageIcon("raw/famine.png")) {
		@Override
		public String[] npcs() {
			return new String[] {"Zombies","Giant Rats","Flesh Crawlers"};
		}
	},
	PESTILENCE(new ImageIcon("raw/pestilence.png")) {
		@Override
		public String[] npcs() {
			return new String[] {"Catablepons","Scorpions","Giant Spiders"};
		}
	},
	DEATH(new ImageIcon("raw/death.png")) {
		@Override
		public String[] npcs() {
			return new String[] {"Ankous","Ghosts","Shades","Skeletons"};
		}
	};
	
	private ImageIcon img;

	public abstract String[] npcs();
	private GuiFloors(ImageIcon img) {
		this.img = img;
	}

	public ImageIcon getImg() {
		return img;
	}
}
