package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.dialog.Dialog.StateTransition;
import com.ironalloygames.planetfall.core.item.*;

public class EnemyDoctorDialog extends Dialog {
	public EnemyDoctorDialog(){
		
		boolean armed = false;
		
		for(Actor a : PFG.s.pc.inventory){
			if(a instanceof Javelin || a instanceof FusionLancePistol) armed = true;
		}
		
		states.put("Start", new HashMap<String, StateTransition>());
		if(armed)
			states.get("Start").put("P", new StateTransition("You enter the pod, weapon drawn", ""));
		else
			states.get("Start").put("P", new StateTransition("You enter the pod", ""));
		
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("In the pod, sitting on the floor is a suprised young " + PFG.s.enemyDoctor.pd.getManWoman() + ", wearing the uniform of the " +
				PFG.s.enemyEmpire.name + " space forces. " + PFG.s.enemyDoctor.pd.getHeShe() + " reaches for a fusion lancer pistol lying on the floor.", ""));
		states.get("Start2").put("1", new StateTransition("Duck around the corner, and retreat", "Retreat"));
		if(armed)
			states.get("Start2").put("2", new StateTransition("Yell \"Freeze!\", holding your weapon out", "Freeze"));
		
		states.put("Freeze", new HashMap<String, StateTransition>());
		states.get("Freeze").put("P", new StateTransition(PFG.s.enemyDoctor.pd.genderSpecific("He", "She") + " is quicker on the draw than you'd expect, and brings " + PFG.s.enemyDoctor.pd.getHisHer() +
				" gun up before you can level yours. It leads to a standoff.", ""));
		
		if(PFG.s.talkedToCommOfficer && PFG.s.commOfficer.hp > 0)
			states.get("Freeze").put("1", new StateTransition("\"Look, I'm just looking for medicines for my friend.\"", "Medicines"));
		else
			states.get("Freeze").put("1", new StateTransition("\"I think we're all alone on this planet. We need to work together to survive.\"", "Alliance"));
		
		states.put("Alliance", new HashMap<String, StateTransition>());
		states.get("Alliance").put("P", new StateTransition("\"I agree.\" You both lower your weapons together.", ""));
		states.get("Alliance").put("1", new StateTransition("Continue", "Alliance2"));
		
		states.put("Alliance2", new HashMap<String, StateTransition>());
		states.get("Alliance2").put("P", new StateTransition("\"I've been running my distress beacon for days, but no response so far... This planet seems uninhabited.\"", ""));
		states.get("Alliance2").put("1", new StateTransition("\"What? Turn it off! Are you trying to kill me?\"", "AllianceIssue"));
		states.get("Alliance2").put("2", new StateTransition("\"And mine's broken. Bah.\"", "YourName"));
		states.get("Alliance2").put("3", new StateTransition("\"What's your name?\"", "YourName"));
		states.get("Alliance2").put("4", new StateTransition("\"How did you get here?\"", "GotHere"));
		
		states.put("AllianceIssue", new HashMap<String, StateTransition>());
		states.get("AllianceIssue").put("P", new StateTransition("\"Calm down. If they show up, I have a plan: Since they don't know who you are, I'll just say you're some stranded merchant I met.\"", ""));
		states.get("AllianceIssue").put("1", new StateTransition("\"Okay then, lets go our separate ways for now\"", "End"));
		states.get("AllianceIssue").put("2", new StateTransition("\"What's your name?\"", "YourName"));
		states.get("AllianceIssue").put("3", new StateTransition("\"How did you get here?\"", "GotHere"));
		
		states.put("YourName", new HashMap<String, StateTransition>());
		states.get("YourName").put("P", new StateTransition("\"I am " + PFG.s.enemyDoctor.pd.firstName + " " + PFG.s.enemyDoctor.pd.lastName + ", a medic on the " + PFG.s.enemyShip.name + "\"", ""));
		states.get("YourName").put("1", new StateTransition("\"Okay then, lets go our separate ways for now\"", "End"));
		states.get("YourName").put("2", new StateTransition("\"How did you get here?\"", "GotHere"));
		
		states.put("GotHere", new HashMap<String, StateTransition>());
		states.get("GotHere").put("P", new StateTransition("\"My ship was destroyed. I just barely escaped with my life.\"", ""));
		states.get("GotHere").put("1", new StateTransition("\"Okay then, lets go our separate ways for now\"", "End"));
		states.get("GotHere").put("2", new StateTransition("\"What's your name?\"", "YourName"));
		
		states.put("Medicines", new HashMap<String, StateTransition>());
		states.get("Medicines").put("P", new StateTransition("\"Well that's perfect, seeing as how I'm a doctor\" You both lower your weapons together", ""));
		states.get("Medicines").put("1", new StateTransition("\"Perfect!\"", "GoodEnding"));
	}

	@Override
	public void newState(String stateName) {
		if(stateName.equals("Retreat")){
			PFG.s.pc.x -= 3;
			PFG.s.pc.y += 3;
		}
		if(stateName.equals("GoodEnding")){
			PFG.s.curDialog = new GoodEnding();
		}
		if(stateName.equals("Freeze")){
			PFG.s.talkedToEnemyDoctor = true;
		}
		super.newState(stateName);
	}
	
	
}
