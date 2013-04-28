package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

public class InfoDialog extends Dialog {
	public InfoDialog(String text){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition(text, ""));
		states.get("Start").put("1", new StateTransition("Close", "End"));
	}
}
