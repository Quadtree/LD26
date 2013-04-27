package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

public class StartCinematic extends Dialog {
	public StartCinematic(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("You feel a sudden lurch as the pod springs to life. Well, at least you're away... (Press 1 to continue)", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("Apruptly, an alarm begins to blare. Its too far away for you to read it exactly. Could be enemy fire, or an air leak or...", ""));
		states.get("Start2").put("1", new StateTransition("Move closer and examine the alarm", "Examine1"));
		states.get("Start2").put("2", new StateTransition("Ponder how you got here anyway", "Reflect"));
		states.get("Start2").put("3", new StateTransition("Panic", "Panic1"));
		states.get("Start2").put("4", new StateTransition("Take this moment to consider the meaning of life", "MeaningOfLife"));
		
		states.put("Examine1", new HashMap<String, StateTransition>());
		states.get("Examine1").put("P", new StateTransition("You're pulling way too many Gs to get out of your seat. Whatever genius designed this should have put the panel closer", ""));
		states.get("Examine1").put("1", states.get("Start2").get("2"));
		states.get("Examine1").put("2", states.get("Start2").get("3"));
		states.get("Examine1").put("3", states.get("Start2").get("4"));
		
		states.put("MeaningOfLife", new HashMap<String, StateTransition>());
		states.get("MeaningOfLife").put("P", new StateTransition("You take a moment to quietly meditate on the meaning of life. Unfortunately, you do not find enlightenment, and the alarm is still going off", ""));
		states.get("MeaningOfLife").put("1", states.get("Start2").get("1"));
		states.get("MeaningOfLife").put("2", states.get("Start2").get("2"));
		states.get("MeaningOfLife").put("3", states.get("Start2").get("3"));
		
		states.put("Panic1", new HashMap<String, StateTransition>());
		states.get("Panic1").put("P", new StateTransition("As fear grips you, your life flashes before your eyes...", ""));
		states.get("Panic1").put("1", new StateTransition("Continue", "DoReflect"));
		
		states.put("Reflect", new HashMap<String, StateTransition>());
		states.get("Reflect").put("P", new StateTransition("You remember how it all started...", ""));
		states.get("Reflect").put("1", new StateTransition("Continue", "DoReflect"));
	}
	
	@Override
	public void newState(String stateName){
		if(stateName.equals("DoReflect")){
			active = false;
		}
		super.newState(stateName);
	}
}
