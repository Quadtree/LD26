package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;

public class GoodEnding extends Dialog {
	public GoodEnding(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition(PFG.s.enemyDoctor.pd.firstName + " follows you to the other pod, where " + PFG.s.enemyDoctor.pd.getHeShe() + " works for several hours" +
		" to revive Lieutenant " + PFG.s.commOfficer.pd.lastName + ".", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("Lieutenant " + PFG.s.commOfficer.pd.lastName + " uses the long range comms to summon help, which soon arrives.", ""));
		states.get("Start2").put("1", new StateTransition("Continue", "Start3"));
		
		states.put("Start3", new HashMap<String, StateTransition>());
		states.get("Start3").put("P", new StateTransition("You both use a variety of vague statements, double meanings, and half-truths to keep " +  PFG.s.enemyDoctor.pd.firstName + " out of trouble. For saving Lieutenant " +
				PFG.s.commOfficer.pd.lastName + "'s life, you are awarded a medal. Not bad for a mechanic trainee!", ""));
		states.get("Start3").put("1", new StateTransition("Continue", "TheEnd"));
		
		states.put("TheEnd", new HashMap<String, StateTransition>());
		states.get("TheEnd").put("P", new StateTransition("The End (Refresh to play again)", ""));
	}
}
