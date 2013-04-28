package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;

public class MediumEnding extends Dialog {
	public MediumEnding(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition(PFG.s.enemyDoctor.pd.firstName + " rushes up to you, \"Quick, change clothes!\" " + PFG.s.enemyDoctor.pd.getHeShe() + " says.", ""));
		states.get("Start").put("1", new StateTransition("Change clothes", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("You see why in a moment, as a platoon of crack " + PFG.s.enemyEmpire.name + " space marines arrives in moments. They whisk you both away on their transport.", ""));
		states.get("Start2").put("1", new StateTransition("Continue", "Start3"));
		
		states.put("Start3", new HashMap<String, StateTransition>());
		states.get("Start3").put("P", new StateTransition("Luckily, " + PFG.s.enemyDoctor.pd.firstName + " deception works, and they believe that you are just a stranded merchant. They release you, and eventually you make your way back to your own lines.", ""));
		states.get("Start3").put("1", new StateTransition("Continue", "TheEnd"));
		
		states.put("TheEnd", new HashMap<String, StateTransition>());
		states.get("TheEnd").put("P", new StateTransition("The End (Refresh to play again)", ""));
	}
}
