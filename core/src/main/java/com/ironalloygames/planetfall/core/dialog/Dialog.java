package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;
import java.util.Map;

import playn.core.Color;

import com.ironalloygames.planetfall.core.PFG;

public class Dialog {
	public boolean active = true;
	
	protected HashMap<String, State> states = new HashMap<String, State>();
	
	public String curState = "Start";
	
	public void render(){
		if(!states.containsKey(curState)){
			active = false;
			return;
		}
		
		PFG.s.setTextAt(3, 7, states.get(curState).prompt, Color.rgb(255, 255, 255));
		
		for(int i=0;i<=states.get(curState).getOptions().size();++i){
			PFG.s.setTextAt(3, 15 + i, (i+1) + " - " + states.get(curState).prompt, Color.rgb(255, 255, 255));
		}
	}
	
	public void pick(int option){
		if(states.get(curState).getOptions().size() > option){
			curState = states.get(curState).getOptions().get(option).newState;
		}
	}
}
