package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;

public class DeadEnding extends Dialog {
	public DeadEnding(){
		states.put("Start",
			new State("", "You are slain on an alien world. Your body is never found, and you are still technically classified as \"Missing In Action\"")
			.add("Start2")
		);
		
		states.put("Start2", new State("Continue", "Eventually you are legally declared dead, allowing your family and friends to have a proper funeral.")
			.add("TheEnd")
		);
		
		states.put("TheEnd", new State("Continue", "The End (Refresh to play again)"));
	}
}
