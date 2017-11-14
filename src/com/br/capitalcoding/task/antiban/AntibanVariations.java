package com.br.capitalcoding.task.antiban;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.model.NPC;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.api.ui.Tab;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.antiban.AntibanTask;
import com.br.capitalcoding.util.Utils;

public enum AntibanVariations {

	SKILLS {
		
		
		@Override
		public
		AntibanTask[] antiban() {
			return new AntibanTask[] {new AntibanTask() {	
				@Override
				public void run() {
					Tab skill = Tab.values()[Utils.random(0, Tab.values().length)];
					BotScope.getContext().getTabs().open(skill);
					BotScope.getContext().getMouse().move(575, 485);
					
				}
			},new AntibanTask() {
				
				@Override
				public void run() {
					Tab skill = Tab.values()[Utils.random(0, Tab.values().length)];
					BotScope.message("Skill :"+skill.name());
					BotScope.getContext().getTabs().open(skill);
					
				}
			},new AntibanTask() {
				
				@Override
				public void run() {
					Tab skill = Tab.values()[Utils.random(0, Tab.values().length)];
					BotScope.message("Skill :"+skill.name());
					BotScope.getContext().getTabs().open(skill);
					try {
						Thread.sleep(Utils.random(500, 1200));
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BotScope.getContext().getMouse().move(Utils.randomXScreen(), Utils.randomYScreen());
					
				}
			}};
		
		}
	},
	RANDOM {

		@Override
		public
		AntibanTask[] antiban() {
		
			return new AntibanTask[] {new AntibanTask() {

				@Override
				public void run() {
				BotScope.getContext().getMouse().move(Utils.randomXScreen(), Utils.randomYScreen());
				try {
					Thread.sleep(Utils.random(500, 1200));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BotScope.getContext().getMouse().move(Utils.randomXScreen(), Utils.randomYScreen());
				}
				
			},new AntibanTask() {

				@Override
				public void run() {
					BotScope.getContext().getMouse().move(Utils.randomXScreen(), Utils.randomYScreen());
				try {
					Thread.sleep(Utils.random(500, 1200));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				BotScope.getContext().getMouse().click(true);
				
				}
				
			}
		};
		}
	},
	CAMERA {

		@Override
		public
		AntibanTask[] antiban() {
			return new AntibanTask[] {new AntibanTask() {
				
				@Override
				public void run() {
					List<NPC> players = BotScope.getContext().getNpcs().getAll();
					if(!players.isEmpty()) {
					NPC player = players.get(Utils.random(players.size()));
					BotScope.getContext().getCamera().toEntity(player);
					}
					
				}
			},
				new AntibanTask() {
				
				@Override
				public void run() {
					BotScope.getContext().getCamera().moveYaw(Utils.random(360));					
					
				}
			},
				new AntibanTask() {
				
				@Override
				public void run() {
					BotScope.getContext().getCamera().moveYaw(Utils.random(180));					
					
				}
			},
			new AntibanTask() {
			
			@Override
			public void run() {
				BotScope.getContext().getCamera().moveYaw(Utils.random(180,360));					
				
			}
		}};
		}
		
	},
	ENTITY {

		@Override
		public
		AntibanTask[] antiban() {
			return new AntibanTask[] {new AntibanTask() {
				
				@Override
				public void run() { //Find nearby
					List<Player> players = BotScope.getContext().getPlayers().getAll();
					if(!players.isEmpty()) {
					Optional<Player> found = players.stream().filter(i->!i.getName().equals(BotScope.getContext().myPlayer().getName())).findFirst();
					String pl = players.stream()
					.map(Player::getName) // maps Car Object to a value returned by toString method
					.collect(Collectors.joining(","));
					BotScope.message("Players nearby:"+pl);
					if(found.isPresent() && BotScope.getContext().getCamera().toEntity(found.get()) && found.get().hover()) {
						BotScope.getContext().getMouse().click(true);
						BotScope.message("Hovering player:"+found.get().getName());
					}else {
						AntibanVariations.CAMERA.getAnyTask().run();
					}
					
					}
				}
			},
				new AntibanTask() {
				
				@Override
				public void run() {//Find on screen
					List<Player> players = BotScope.getContext().getPlayers().getAll();
					if(!players.isEmpty()) {
					Optional<Player> found = players.stream().filter(i-> (!i.getName().equals(BotScope.getContext().myPlayer().getName()) && i.isOnScreen())).findFirst();
					if(found.isPresent() && found.get().hover()) {
						BotScope.getContext().getMouse().click(true);
						BotScope.message("Hovering player:"+found.get().getName());
					}else {
							AntibanVariations.CAMERA.getAnyTask().run();
						
					}
					
					}
				}
			},
				new AntibanTask() {
				
				@Override
				public void run() {//Find on screen
					List<NPC> players = BotScope.getContext().getNpcs().getAll();
					if(!players.isEmpty()) {
					Optional<NPC> found = players.stream().filter(i-> (!i.getName().equals(BotScope.getContext().myPlayer().getName()) && i.isOnScreen())).findFirst();
					if(found.isPresent() && found.get().hover()) {
						BotScope.getContext().getMouse().click(true);
						BotScope.message("Hovering NPC:"+found.get().getName());
					}
					
					}
				}
			},
				new AntibanTask() {
				
				@Override
				public void run() { //Find nearby
					List<Player> players = BotScope.getContext().getPlayers().getAll();
					if(!players.isEmpty()) {
					Optional<Player> found = players.stream().filter(i->!i.getName().equals(BotScope.getContext().myPlayer().getName())).findFirst();
					String pl = players.stream()
					.map(Player::getName) // maps Car Object to a value returned by toString method
					.collect(Collectors.joining(","));
					BotScope.message("Players nearby:"+pl);
					if(found.isPresent() && BotScope.getContext().getCamera().toEntity(found.get()) && found.get().hover()) {
						BotScope.getContext().getMouse().click(true);
						BotScope.message("Hovering player:"+found.get().getName());
					}else {
						AntibanVariations.getAnyOfAny().run();
					}
					
					}
				}
			}};
		}
		
	};
	//I need new object everytime, yes because they would have the same action, or no? HAHHDAHASHA
	
	public abstract AntibanTask[] antiban();
	 //do you mean statically then, I need it on the enum like I can call Friends.getAnyTask(); So it will return me a random antiban task
	public AntibanTask getAnyTask() {
		Random r = new Random();
		int num = r.nextInt(this.antiban().length);
		//this.antiban() //how would you do that instead	
		BotScope.message("Chosen number of anti :"+num);
		return this.antiban()[num];
	}
	public static AntibanTask getAnyOfAny() {
		Random r = new Random();
		int num = r.nextInt(AntibanVariations.values().length);
		AntibanVariations variation = AntibanVariations.values()[num];
		AntibanTask task = variation.getAnyTask();
		BotScope.message("Var :"+variation.name());
		return task;
	}

}
