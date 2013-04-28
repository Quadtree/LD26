package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;
import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.item.*;

public class EnemyDoctorAboutCommDialog extends Dialog {
	public EnemyDoctorAboutCommDialog(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("\"What is it?\"", ""));
		states.get("Start").put("1", new StateTransition("\"I just found another member of my crew, but they're injured\"", "Medicines"));
		
		states.put("Medicines", new HashMap<String, StateTransition>());
		states.get("Medicines").put("P", new StateTransition("\"Well that's perfect, seeing as how I'm a doctor\"", ""));
		states.get("Medicines").put("1", new StateTransition("\"Perfect!\"", "GoodEnding"));
	}

	@Override
	public void newState(String stateName) {
		if(stateName.equals("GoodEnding")){
			PFG.s.curDialog = new GoodEnding();
		}
		super.newState(stateName);
	}
	
	
}
