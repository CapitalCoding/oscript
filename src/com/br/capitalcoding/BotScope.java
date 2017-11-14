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
import com.br.capitalcoding.scripts.Shearer;
import com.br.capitalcoding.scripts.StrongholdFighter;
import com.br.capitalcoding.task.impl.combat.EatFood;
import com.br.capitalcoding.task.manager.TaskManager;
import com.br.capitalcoding.task.types.GameTask;
import com.br.capitalcoding.util.Timer;
import com.br.capitalcoding.util.Utils;

@ScriptManifest(author = "CapitalCoding", info = "Shearer",name = "A basic f2p script", version = 1.0, logo = "")

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
		log("-----------------------------------------------------------------------------------------------------");
		Task task = new Shearer();
		BotScope.TASK = task;
		TaskManager.submit(task);		
	}
	@Override
	public int onLoop() throws InterruptedException {
		TaskManager.sequence();
		return Utils.random((Configuration.BOT_EXECUTION_SPEED - 300), Configuration.BOT_EXECUTION_SPEED);
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
	}
	public static Walker getWalker() {
		return walker;
	}
	public static void setWalker(Walker walker) {
		BotScope.walker = walker;
	}
	
}
