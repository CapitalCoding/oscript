package com.br.capitalcoding.task.impl.walk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.osbot.rs07.api.filter.Filter;
import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.map.constants.Banks;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.event.WebWalkEvent;
import org.osbot.rs07.utility.Condition;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.game.area.stronghold.StrongholdAreas;
import com.br.capitalcoding.game.area.stronghold.StrongholdNPC;
import com.br.capitalcoding.task.condition.PreCondition;
import com.br.capitalcoding.task.condition.TaskState;
import com.br.capitalcoding.task.impl.dialogue.StrongholdDialogue;
import com.br.capitalcoding.task.types.PreConditionTask;

public class StrongholdPath extends PreConditionTask{

	final PreCondition conditions = new PreCondition().add(new StrongholdDialogue());
	final Area ladderArea = new Area(1858, 5239, 1865, 5244);
	Map<String, Area> npcs = new HashMap<>();
	private boolean banking;
	
	public StrongholdPath() {
		super(1, "Walking Mediator", false, 0);

	}
	public StrongholdPath(TaskState state) {
		super(1, "Walking Mediator", false, 0);
		setTaskState(state);

	}
	final Area destination = StrongholdNPC.FLESH_CRAWLER.getArea();
	@Override
	public boolean isMeetCondition() {
		switch (this.getTaskState()) {
		case BANKING:		
			return Banks.EDGEVILLE.contains(getProvider().myPosition());
		default:			
			return destination.contains(getProvider().myPosition());
		}
		
	}
	
	@Override
	public PreCondition conditionFor(TaskState state) {
		switch (state) {
		case NORMAL:		
			return conditions;
		case BANKING:		
			return conditions;
		default:
			return super.getPreCondition();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		BotScope.message("Floor:"+getCurrentFloor()+" Dest:"+getDestinationFloor());
		switch (this.getTaskState()) {		
		case NORMAL:	
			if(getCurrentFloor() == -1) {
				if(getProvider().getWalking().webWalk(new Position(3080, 3421, 0))){ //hole
					RS2Object obj = getProvider().getObjects().getObjects().closest("Entrance");
					if(obj != null) {
						if(obj.interact("Climb-down")) {
							
							}
					}
				}
			}else if(getCurrentFloor() == getDestinationFloor()){
				//webWalkEvent(destination);
				List<Position> path = new ArrayList<Position>() {{
					add(new Position(2029, 5236, 0));
					add(new Position(2020, 5243, 0));
				}};
				Position[] pos =  {new Position(2029, 5236, 0), new Position(2020, 5243, 0), 
						 new Position(2013, 5243, 0)};			
					//BotScope.getContext().getWalking().walk(destination);
					//if(BotScope.getWalker().walkPathMM(pos)) {
				this.webWalkEvent(destination);
				
			}else if(getCurrentFloor() != getDestinationFloor()){
				RS2Object portal = getProvider().getObjects().closest(this.portal);
				RS2Object ladderDown = getProvider().getObjects().closest(ladder);
				if(StrongholdAreas.values()[getCurrentFloor()-1].
						getEntranceArea().contains(BotScope.getContext().myPosition())){
					portal.interact("Use");					
				}else {
					BotScope.message("Tentando climb down");
					if(ladderDown != null && ladderDown.interact("Climb-down")) {
						
					}
						
				}			
			
		}
			break;
			
		case BANKING:
			RS2Object ladderUp = getProvider().getObjects().closest(new Filter<RS2Object>() {

				@Override
				public boolean match(RS2Object obj) {
					if(!obj.hasAction("Climb-down") && obj.getName().contains("Ladder"))
						return true;
					return false;
				}
				
			});
			
			boolean surface = getCurrentFloor() == -1;
			BotScope.message("Ta indo bank certo"+surface);
			if(getCurrentFloor() == -1) {
				getProvider().getWalking().webWalk(Banks.EDGEVILLE);
				BotScope.message("Surface");
			}else if(getCurrentFloor() > 0) {
				StrongholdAreas floor = StrongholdAreas.values()[getCurrentFloor() -1];
				webWalkEvent(floor.getEntranceArea());
					if(ladderUp != null && 
							floor.getEntranceArea().contains(BotScope.getContext().myPosition()) &&
							ladderUp.interact("Climb-up")) {
						
					}
				
			}
			break;
		default:
			break;
		}

		
	}
	Filter<RS2Object> portal = new Filter<RS2Object>() {

		@Override
		public boolean match(RS2Object obj) {
			if(obj.getName().contains("Portal") && 
					obj.getArea(10).contains(BotScope.getContext().myPosition()))
				return true;
			return false;
		}
		
	};
	Filter<RS2Object> ladder = new Filter<RS2Object>() {

		@Override
		public boolean match(RS2Object obj) {
			if(obj.hasAction("Climb-down") && obj.getName().contains("Ladder"))
				return true;
			return false;
		}
		
	};
	private int getDestinationFloor() {
		return getFloorLevel(destination.getPositions().get(0).getX());
		
	}
	private int getFloorLevel(int baseX) {
		if(baseX >= 1800 && baseX <= 1986)
			return 1;
		else if(baseX >= 1987 && baseX <= 2120)
			return 2;
		else if(baseX >= 2121 && baseX <= 2500)
			return 3;
		else
			return -1;
	}
	private int getCurrentFloor() {
		return getFloorLevel(BotScope.getContext().myPosition().getX());
		
	}

	public void webWalkEvent(Area area) {
	    WebWalkEvent event = new WebWalkEvent(area);
	    event.setBreakCondition(new Condition() {
	        @Override
	        public boolean evaluate() {
	        	// Return when you want the event to break.
	            return getProvider().getDialogues().inDialogue();

	        }
	    });

	    BotScope.getContext().execute(event);
	}

}
