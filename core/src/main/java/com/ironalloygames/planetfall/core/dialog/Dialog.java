package com.ironalloygames.planetfall.core.dialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import playn.core.Analytics.Category;
import playn.core.Color;
import playn.core.PlayN;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.dialog.State.Transition;

public class Dialog {
	public boolean active = true;
	
	protected HashMap<String, State> states = new HashMap<String, State>();
	
	public String curState = "Start";
	
	public static Category dialogCategory = new Category(1, "Dialog Category");
	
	public void render(){
		if(!states.containsKey(curState)){
			active = false;
			return;
		}
		
		PFG.s.setTextAt(3, 7, states.get(curState).prompt, Color.rgb(255, 255, 255));
		
		for(int i=0;i<getAvailableOptions().size();++i){
			if(states.get(getAvailableOptions().get(i).newState).isChangeAllowed()) PFG.s.setTextAt(3, 15 + i, (i+1) + " - " + states.get(getAvailableOptions().get(i).newState).text, Color.rgb(255, 255, 255));
		}
	}
	
	public void pick(int option){
		if(getAvailableOptions().size() > option){
			curState = getAvailableOptions().get(option).newState;
			PlayN.log().info("State changed to " + curState);
			states.get(curState).changedTo();
		}
	}
	
	public List<Transition> getAvailableOptions(){
		List<Transition> l = new ArrayList<Transition>();
		
		for(Transition t : states.get(curState).getOptions()){
			if(states.get(t.newState).isChangeAllowed()) l.add(t);
		}
		
		return l;
	}
}
