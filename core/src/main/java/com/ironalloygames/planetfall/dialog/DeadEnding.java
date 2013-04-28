package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;

public class DeadEnding extends Dialog {
	public DeadEnding(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("You are slain on an alien world. Your body is never found, and you are still technically classified as \"Missing In Action\"", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("Eventually you are legally declared dead, allowing your family and friends to have a proper funeral.", ""));
		states.get("Start2").put("1", new StateTransition("Continue", "TheEnd"));
		
		states.put("TheEnd", new HashMap<String, StateTransition>());
		states.get("TheEnd").put("P", new StateTransition("The End (Refresh to play again)", ""));
	}
}
