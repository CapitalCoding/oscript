package com.br.capitalcoding;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.text.DecimalFormat;

import org.osbot.rs07.script.Script;
import org.osbot.rs07.script.ScriptManifest;
import com.br.capitalcoding.model.Task;
import com.br.capitalcoding.model.Walker;
import com.br.capitalcoding.scripts.BasicFighter;
import com.br.capitalcoding.scripts.MockWalker;
import com.br.capitalcoding.scripts.StrongholdFighter;
import com.br.capitalcoding.task.impl.combat.EatFood;
import com.br.capitalcoding.task.manager.TaskManager;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.util.Timer;
import com.br.capitalcoding.util.Utils;

@ScriptManifest(author = "Digdig18", info = "A Basic fighter",name = "Stronghold Fighter", version = 1.0, logo = "")

public class BotScope extends Script {
	private static BotScope context;
	public static final Timer runtimer = new Timer(0);
	public static Task TASK;
	public static int timesInBank = 0;
	DecimalFormat df = new DecimalFormat("#");
	public static Graphics2D g;
	private static Walker walker;
	public void addTimesInBank() {
		timesInBank++;
	}
	public static BotScope getContext() {
		return context;
	}
	@Override
	public void onStart() throws InterruptedException {
		context = this;
		setWalker(new Walker(this));
		decision();
		//int price = Utils.getPrice(1947).get();
		//item = new Item(1947, 1, price);
	}
	@Override
	public int onLoop() throws InterruptedException {
		TaskManager.sequence();
		return Utils.random((Configuration.BOT_EXECUTION_SPEED - 300), Configuration.BOT_EXECUTION_SPEED);
	}

	private void decision() {	
		log("-----------------------------------------------------------------------------------------------------");
		//Task task = new WeatherPicker();
		GameTask task = new StrongholdFighter();
		//task.setTaskState(TaskState.BANKING);
		//Task task = new StrongholdDialogue();
		//Task task = new StrongholdPath();
		//Task task = new BasicFighter(myPlayer().getArea(10));
		//Task task = new Restock(Banks.EDGEVILLE);
		//Task task = new DropWatcher("Bones");
		//Task task = new MockWalker();
		BotScope.TASK = task;
		TaskManager.submit(task);	
		TaskManager.submit(new EatFood(45));	
		//message("Area:"+StrongholdAreas.FAMINE.getNpcLocations().get("Flesh Crawler"));
		
	}

	public static void message(String str) {
		if(Configuration.devMode)
		getContext().log(str);
	}
	@Override
	public void onPaint(Graphics2D g) {
	g.setColor(Color.RED);
	g.drawString("Time Ran: " + BotScope.runtimer.toElapsedString(), 20, 40);
	g.drawString("Times in Bank: "+timesInBank, 20, 60);
   // g.drawString("Amount per hour: "+ df.format( (item.getTotalGpGained() / 1000)) + " k", 20, 80);
	}
	public static Walker getWalker() {
		return walker;
	}
	public static void setWalker(Walker walker) {
		BotScope.walker = walker;
	}
	
}
