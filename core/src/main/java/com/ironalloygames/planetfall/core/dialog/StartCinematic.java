package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import playn.core.PlayN;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.level.ShipLevel;

public class StartCinematic extends Dialog {
	public StartCinematic(){
		states.put("Start", new State("", "You feel a sudden lurch as the pod springs to life. Well, at least you're away... (Press 1 to continue)")
			.add("Start2")
			.add("Skip")
		);
		
		states.put("Start2", new State("Continue", "Apruptly, an alarm begins to blare. Its too far away for you to read it exactly. Could be enemy fire, or an air leak or...")
			.add("Examine1")
			.add("Reflect")
			.add("Panic1")
			.add("MeaningOfLife")
		);
		
		states.put("Examine1", new State(
			"Move closer and examine the alarm",
			"You're pulling way too many Gs to get out of your seat. Whatever genius designed this should have put the panel closer.")
			.add("Examine1")
			.add("Reflect")
			.add("Panic1")
			.add("MeaningOfLife")
		);
		
		states.put("MeaningOfLife", new State(
			"Take this moment to consider the meaning of life",
			"You take a moment to quietly meditate on the meaning of life. Unfortunately, you do not find enlightenment, and the alarm is still going off.")
			.add("Examine1")
			.add("Reflect")
			.add("Panic1")
			.add("MeaningOfLife")
		);
		
		states.put("Panic1", new State("Panic", "As fear grips you, your life flashes before your eyes...").add("DoReflect"));
		states.put("Reflect", new State("Ponder how you got here anyway", "You remember how it all started...").add("DoReflect"));
		
		states.put("DoReflect", new State("Continue", "It all began with your (in retrospect) possibly poor decision to join the " + PFG.s.alliedEmpire.name + " space forces.")
			.add("DoReflect2")
			.add(new State.ChangeListener() {
				
				@Override
				public void stateChanged(State s) {
					PFG.s.currentLevel.actors.remove(PFG.s.pc);
					PFG.s.currentLevel = PFG.s.officeLevel;
					PFG.s.currentLevel.actors.add(PFG.s.pc);
					PFG.s.pc.curLevel = PFG.s.officeLevel;
					PFG.s.pc.x = 8;
					PFG.s.pc.y = 5;
				}
			})
		);
		
		states.put("DoReflect2", new State("Continue", "After the outbreak of the war with the " + PFG.s.enemyEmpire.name + ", you visited " + PFG.s.rec.getDesc() + "'s office to join up.")
			.add("DoReflect3")
		);
		
		states.put("DoReflect3", new State("Continue", PFG.s.rec.getDesc() + ": \"Let's start with the basics. What is your name?\"").add("DoReflect4"));
		
		states.put("DoReflect4", new State(
			PFG.s.pc.pd.firstName + " " + PFG.s.pc.pd.lastName,
			PFG.s.rec.getDesc() + ": \"All right " + PFG.s.pc.pd.firstName + ", you seem like a bright young " + PFG.s.pc.pd.getManWoman() +
			". What skills do you have that would be useful to the space forces?\"")
			.add("AceFighterPilot")
			.add("Mechanic1")
			.add("SpaceBuster")
		);
		
		states.put("AceFighterPilot", new State("\"I'm an ace fighter pilot!\"", PFG.s.rec.getDesc() + ": \"I'm sorry to say the obsolete fighter forces were retired years ago. Any other skills?\"")
			.add("AceFighterPilot")
			.add("Mechanic1")
			.add("SpaceBuster")
		);
		
		states.put("SpaceBuster", new State("\"I've gotten 120,000 points playing Space Buster!\"", PFG.s.rec.getDesc() + ": \"How nice. Any *useful* skills?\"")
			.add("AceFighterPilot")
			.add("Mechanic1")
			.add("SpaceBuster")
		);
		
		states.put("Mechanic1", new State("\"I'm a good mechanic.\"", PFG.s.rec.getDesc() + ": \"That'll do. Welcome to the space forces, mechanic trainee " + PFG.s.pc.pd.lastName + ".\"").add("Mechanic2"));
		
		states.put("Mechanic2", new State("Continue", "After some brief basic training, you are assigned to the " + PFG.s.alliedShip.className + " " + PFG.s.alliedShip.name + 
				". Your fleet is assigned to the battlefront, and you are assigned to repair Fuel Pump Room 2.").add("Mechanic3").add(new Mechanic2()));
		
		states.put("Mechanic3", new State("Continue", "One day, while you are fixing a small pressure imbalance in the system, you hear a voice come over the shipwide PA system.").add("Mechanic4"));
		
		states.put("Mechanic4", new State("Continue", "Captain " + PFG.s.captain.lastName + ": \"We're seeing multiple unidentified jump-in signatures on scan. "+
			"Command assumes that they are hostile. Estimated contact in four minutes, all crew to combat alert red.\"").add("Mechanic5"));
		
		states.put("Mechanic5", new State("Continue", "This *is* your battle station. How lucky! Now all you need to do is wait for something to break. Things are mostly boring, just a few tremors, until...").add("Mechanic6"));
		
		states.put("Mechanic6", new State("Continue", "An enemy fusion lance cuts through the wall! EVERYTHING IS ON FIRE! Bulkheads slam to keep the air in, "+
			"but the fires still rage. (Walk to the red F, press P to pick up the fire extinguisher, press U to use it)").add("Mechanic7").add(new Mechanic6()));
		
		states.put("Skip", new State("Skip Opening Cinematic", "").add(new Mechanic2()).add(new Mechanic6()).add(new State.ChangeConditional() {
			
			@Override
			public boolean allowChange(State s) {
				return PlayN.storage().getItem("playedBefore") != null;
			}
		}));
	}
	
	class Mechanic2 implements State.ChangeListener {

		@Override
		public void stateChanged(State s) {
			PFG.s.currentLevel.actors.remove(PFG.s.pc);
			PFG.s.currentLevel = PFG.s.shipLevel;
			PFG.s.currentLevel.actors.add(PFG.s.pc);
			PFG.s.pc.curLevel = PFG.s.shipLevel;
			PFG.s.pc.x = 18;
			PFG.s.pc.y = 25;
			PlayN.storage().setItem("playedBefore", "yes");
		}
	}
	
	class Mechanic6 implements State.ChangeListener {

		@Override
		public void stateChanged(State s) {
			((ShipLevel) PFG.s.currentLevel).cataclysm();
		}
	}
}
