package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

public class InfoDialog extends Dialog {
	public InfoDialog(String text){
		states.put("Start", new State("", text).add("End"));
		
		states.put("Close", new State("Close", "").add(new State.End()));
	}
}
