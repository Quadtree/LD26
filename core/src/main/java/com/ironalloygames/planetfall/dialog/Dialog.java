package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;
import java.util.Map;

import playn.core.Color;

import com.ironalloygames.planetfall.core.PFG;

public class Dialog {
	public boolean active = true;
	
	protected HashMap<String, HashMap<String, StateTransition>> states = new HashMap<String, HashMap<String, StateTransition>>();
	
	protected String curState = "Start";
	
	public class StateTransition {
		public String text;
		public StateTransition(String text, String newStateName) {
			super();
			this.text = text;
			this.newStateName = newStateName;
		}
		public String newStateName;
	}
	
	public void render(){
		if(!states.containsKey(curState)){
			active = false;
			return;
		}
		
		PFG.s.setTextAt(3, 3, states.get(curState).get("P").text, Color.rgb(255, 255, 255));
		
		for(int i=1;i<=5;++i){
			if(states.get(curState).containsKey("" + i)){
				PFG.s.setTextAt(3, 15 + i, i + " - " + states.get(curState).get("" + i).text, Color.rgb(255, 255, 255));
			}
		}
	}
	
	public void pick(int option){
		if(states.get(curState).containsKey("" + option)){
			curState = states.get(curState).get("" + option).newStateName;
			newState(curState);
		}
	}
	
	public void newState(String stateName){
	}
}
