package com.br.capitalcoding.task.impl.dialogue;

import java.util.List;

import org.osbot.BotCallback;
import org.osbot.rs07.api.ui.Option;
import org.osbot.rs07.api.ui.RS2Widget;

import com.br.capitalcoding.BotScope;
import com.br.capitalcoding.task.types.PreConditionTask;

public class StrongholdDialogue extends PreConditionTask{

	private static final int[] ANSWER_INTERFACES = new int[]{230, 228};

	private static final int[] QUESTION_INTERFACES = new int[]{243, 242, 244, 241};
	
	private static final String[] CORRECT_ANSWERS = new String[]

			{

			"Nobody",

			"Don't tell them anything and ignore them.",

			"Talk to any banker in RuneScape.",

			"Nothing",

			"Memorable",

			"Politely tell them no and then use the 'Report Abuse' button.",

			"Don't tell them anything and inform Jagex through the game website.",

			"No, it might steal my password.",

			"Don't give him my password.",

			"To recover my account if i don't remember my password.",

			"Nowhere",

			"No",

			"To help me recover my password if I forget it or if it is stolen.",

			"Recovering your account if you forget your password.",

			"Game Inbox on the RuneScape website.",
			
			"Report the incident and do not click any links.",
			
			"Don't click any links",
			
			"Secure my computer",
			
			"Use the Account Recovery System.",
			
			"Set up",
			"Only on the Runescape website",
			"Through account settings on runescape.com.",
			"The birthday of a famous person or event"

			};
	public StrongholdDialogue() {
		super(1, "Stronghold dialogue", false, 0);
	}

	@Override
	public boolean isMeetCondition() {
		return !getProvider().getDialogues().inDialogue();
	}

	@Override
	public void run() {	
		BotScope.message("Tentando resolver");
        if(BotScope.getContext().getDialogues().isPendingContinuation()){
        	BotScope.getContext().getDialogues().clickContinue();
        }else if (BotScope.getContext().getDialogues().isPendingOption()){
            if(BotScope.getContext().getDialogues().selectOption(CORRECT_ANSWERS)){
                BotScope.message("Foi");
            }
        }
		
		
	}

}
