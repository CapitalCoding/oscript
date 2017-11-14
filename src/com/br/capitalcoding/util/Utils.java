package com.br.capitalcoding.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.api.model.Player;
import org.osbot.rs07.api.model.RS2Object;
import org.osbot.rs07.api.ui.Skill;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.task.types.PreConditionTask;

public class Utils {

	public static boolean keyExists(ArrayList<Task> unmeetTask, Task task) {
		for(Task t : unmeetTask) {
			if(t.getKey().equals(task.getKey()))
				return true;
		}
		return false;
	}
	
	public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
        try {
            return clazz.cast(o);
        } catch (ClassCastException e) {
            return null;
        }
    }
	public static int randomXScreen() {
		return new Random().nextInt(750);
	}
	public static int randomYScreen() {
		return new Random().nextInt(490);
	}
	public static int random(int start, int finish) {
		if(start == 0)
			return new Random().nextInt(finish);
		return new Random().nextInt(start) + finish;

	}
	public static int random(int finish) {
		return Utils.random(0, finish);

	}

	public static void sleep(int start, int finish) {
		try {
			Thread.sleep(Utils.random(start, finish));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public HashMap<String, Integer> getExchangeInfo(int id){

	    HashMap<String, Integer> exchangeInfo = new HashMap<>();

	    try {
	        URL url = new URL("http://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + id);
	        URLConnection con = url.openConnection();
	        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
	        con.setUseCaches(true);
	        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String json = br.readLine();
	        br.close();
	        json = json.replaceAll("[{}\"]", "");
	        String[] items = json.split(",");
	        for(String item : items) {
	            String[] splitItem = item.split(":");
	            exchangeInfo.put(splitItem[0], Integer.parseInt(splitItem[1]));
	        }
	    } catch (final Exception e){
	        e.printStackTrace();
	    }
	    return exchangeInfo;
	}
	public static Optional<Integer> getPrice(int id){
	    Optional<Integer> price = Optional.empty();

	    try {
	        URL url = new URL("http://api.rsbuddy.com/grandExchange?a=guidePrice&i=" + id);
	        URLConnection con = url.openConnection();
	        con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36");
	        con.setUseCaches(true);
	        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        String[] data = br.readLine().replace("{", "").replace("}", "").split(",");
	        br.close();
	        price = Optional.of(Integer.parseInt(data[0].split(":")[1]));
	    } catch(Exception e){
	        e.printStackTrace();
	    }
	    return price;
	}
	public final double percentToNextLevel(final Skill skill){
	    int curLvl = BotScope.getContext().getSkills().getStatic(skill),
	    curXP = BotScope.getContext().getSkills().getExperience(skill),
	    xpCurLvl = BotScope.getContext().getSkills().getExperienceForLevel(curLvl),
	    xpNextLvl = BotScope.getContext().getSkills().getExperienceForLevel(curLvl + 1);

	    return (((curXP - xpCurLvl) * 100) / (xpNextLvl - xpCurLvl));
	}
	public final String formatValue(final long l) {
	    return (l > 1_000_000) ? String.format("%.2fm", ((double) l / 1_000_000))
	           : (l > 1000) ? String.format("%.1fk", ((double) l / 1000)) 
	           : l + "";
	}
	
	public static int getPerHour(final int base, final long time) {
		return (int) ((base) * 3600000D / (System.currentTimeMillis() - time));
	}
	public static boolean isCharacterFacing(Player character, Position position) {
		
		int rotation = character.getRotation();
		Position charPos = character.getPosition();
		int xDiff = charPos.getX() - position.getX();
		int yDiff = charPos.getY() - position.getY();
		
		// Character position equals position.
		if (xDiff == 0 && yDiff == 0) {
			return false; 
		}
		
		// Position is east of character.
		if (xDiff < 0) {
			if (rotation < 1024) {
				return false;
			}
		}
		// Position is west of character.	
		else if (xDiff > 0) {
			if (rotation > 1024) {
				return false;
			}
		}
		// Position is on the same vertical axis.
		else {
			// Position is exactly south of character.
			if (yDiff > 0) {
				return rotation < 256 || rotation > 1792;
			}
			// Position is exactly north of character.
			else {
				return rotation > 762 && rotation < 1286;
			}
		}
		
		// Position is south of character.
		if (yDiff > 0) {
			if (rotation > 512 && rotation < 1536) {
				return false;
			}
		}
		// Position is north of character.
		else if (yDiff < 0) {
			if (rotation < 512 || rotation > 1536) {
				return false;
			}
		}
		// Position is on the same horizontal axis.
		else {
			// Position is exactly east of character.
			if (xDiff < 0) {
				return rotation > 1286 && rotation < 1792;
			}
			// Position is exactly west of character.
			else {
				return rotation > 256 && rotation < 762;
			}
		}
		
		return true;
	}

	public static boolean isCharacterFacing(Player character, RS2Object object) {
		return isCharacterFacing(character, object.getPosition());
	}

}
