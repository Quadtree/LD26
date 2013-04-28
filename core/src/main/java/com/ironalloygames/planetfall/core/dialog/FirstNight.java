package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

public class FirstNight extends Dialog {
	public FirstNight(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("Night is falling. To your suprise, it starts getting extremely cold. You'll need to find a fire if you don't want to die of hypothermia. (Press R to rest for 2 hours)", ""));
		states.get("Start").put("1", new StateTransition("Continue", "End"));
	}
	
}
