package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.ShipLevel;

public class StartCinematic extends Dialog {
	public StartCinematic(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("You feel a sudden lurch as the pod springs to life. Well, at least you're away... (Press 1 to continue)", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		states.get("Start").put("2", new StateTransition("Skip Opening Cinematic", "Skip"));
		
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
		
		states.put("DoReflect", new HashMap<String, StateTransition>());
		states.get("DoReflect").put("P", new StateTransition("It all began with your (in retrospect) possibly poor decision to join the " + PFG.s.alliedEmpire.name + " space forces.", ""));
		states.get("DoReflect").put("1", new StateTransition("Continue", "DoReflect2"));
		
		states.put("DoReflect2", new HashMap<String, StateTransition>());
		states.get("DoReflect2").put("P", new StateTransition("After the outbreak of the war with the " + PFG.s.enemyEmpire.name + ", you visited " + PFG.s.rec.getDesc() + "'s office to join up", ""));
		states.get("DoReflect2").put("1", new StateTransition("Continue", "DoReflect3"));
		
		states.put("DoReflect3", new HashMap<String, StateTransition>());
		states.get("DoReflect3").put("P", new StateTransition(PFG.s.rec.getDesc() + ": \"Let's start with the basics. What is your name?\"", ""));
		states.get("DoReflect3").put("1", new StateTransition(PFG.s.pc.pd.firstName + " " + PFG.s.pc.pd.lastName, "DoReflect4"));
		
		states.put("DoReflect4", new HashMap<String, StateTransition>());
		states.get("DoReflect4").put("P", new StateTransition(PFG.s.rec.getDesc() + ": \"Alright " + PFG.s.pc.pd.firstName + " you seem like a bright young " + PFG.s.pc.pd.getManWoman() + ". What skills do you have that would be useful to the space forces?\"", ""));
		states.get("DoReflect4").put("1", new StateTransition("\"I'm an ace fighter pilot!\"", "AceFighterPilot"));
		states.get("DoReflect4").put("2", new StateTransition("\"I'm a good mechanic.\"", "Mechanic1"));
		states.get("DoReflect4").put("3", new StateTransition("\"I've gotten 120,000 points playing Space Buster!\"", "SpaceBuster"));
		
		states.put("AceFighterPilot", new HashMap<String, StateTransition>());
		states.get("AceFighterPilot").put("P", new StateTransition(PFG.s.rec.getDesc() + ": \"I'm sorry to say the obsolete fighter forces were retired years ago. Any other skills?\"", ""));
		states.get("AceFighterPilot").put("1", new StateTransition("\"I'm a good mechanic.\"", "Mechanic1"));
		states.get("AceFighterPilot").put("2", new StateTransition("\"I've gotten 120,000 points playing Space Buster!\"", "SpaceBuster"));
		
		states.put("SpaceBuster", new HashMap<String, StateTransition>());
		states.get("SpaceBuster").put("P", new StateTransition(PFG.s.rec.getDesc() + ": \"How nice. Any *useful* skills?\"", ""));
		states.get("SpaceBuster").put("1", new StateTransition("\"I'm an ace fighter pilot!\"", "AceFighterPilot"));
		states.get("SpaceBuster").put("2", new StateTransition("\"I'm a good mechanic.\"", "Mechanic1"));
		
		states.put("Mechanic1", new HashMap<String, StateTransition>());
		states.get("Mechanic1").put("P", new StateTransition(PFG.s.rec.getDesc() + ": \"That'll do. Welcome to the space forces, mechanic trainee " + PFG.s.pc.pd.lastName + ".\"", ""));
		states.get("Mechanic1").put("1", new StateTransition("Continue", "Mechanic2"));
		
		states.put("Mechanic2", new HashMap<String, StateTransition>());
		states.get("Mechanic2").put("P", new StateTransition("After some brief basic training, you are assigned to the " + PFG.s.alliedShip.className + " " + PFG.s.alliedShip.name + 
				". Your fleet is assigned to the battlefront, and you are assigned to repair Fuel Pump Room 2.", ""));
		states.get("Mechanic2").put("1", new StateTransition("Continue", "Mechanic3"));
		
		states.put("Mechanic3", new HashMap<String, StateTransition>());
		states.get("Mechanic3").put("P", new StateTransition("One day, while you are fixing a small pressure imbalance in the system, you hear a voice come over the shipwide PA system.", ""));
		states.get("Mechanic3").put("1", new StateTransition("Continue", "Mechanic4"));
		
		states.put("Mechanic4", new HashMap<String, StateTransition>());
		states.get("Mechanic4").put("P", new StateTransition("Captain " + PFG.s.captain.lastName + ": \"We're seeing multiple unidentified jump-in signatures on scan. Command assumes that they are hostile. Estimated contact in four minutes, all crew to combat alert red.\"", ""));
		states.get("Mechanic4").put("1", new StateTransition("Continue", "Mechanic5"));
		
		states.put("Mechanic5", new HashMap<String, StateTransition>());
		states.get("Mechanic5").put("P", new StateTransition("This *is* your battle station. How lucky! Now all you need to do is wait for something to break. Things are mostly boring, just a few tremors, until...", ""));
		states.get("Mechanic5").put("1", new StateTransition("Continue", "Mechanic6"));
		
		states.put("Mechanic6", new HashMap<String, StateTransition>());
		states.get("Mechanic6").put("P", new StateTransition("An enemy fusion lance cuts through the wall! EVERYTHING IS ON FIRE! (Walk to the red F, press P to pick up the fire extinguisher, walk back to the fire and press U to use it)", ""));
		states.get("Mechanic6").put("1", new StateTransition("Continue", "Mechanic7"));
	}
	
	@Override
	public void newState(String stateName){
		if(stateName.equals("DoReflect")){
			PFG.s.currentLevel.actors.remove(PFG.s.pc);
			PFG.s.currentLevel = PFG.s.officeLevel;
			PFG.s.currentLevel.actors.add(PFG.s.pc);
			PFG.s.pc.curLevel = PFG.s.officeLevel;
			PFG.s.pc.x = 8;
			PFG.s.pc.y = 5;
		}
		if(stateName.equals("Mechanic2") || stateName.equals("Skip")){
			PFG.s.currentLevel.actors.remove(PFG.s.pc);
			PFG.s.currentLevel = PFG.s.shipLevel;
			PFG.s.currentLevel.actors.add(PFG.s.pc);
			PFG.s.pc.curLevel = PFG.s.shipLevel;
			PFG.s.pc.x = 18;
			PFG.s.pc.y = 25;
		}
		if(stateName.equals("Mechanic6") || stateName.equals("Skip")){
			((ShipLevel) PFG.s.currentLevel).cataclysm();
		}
		super.newState(stateName);
	}
}
