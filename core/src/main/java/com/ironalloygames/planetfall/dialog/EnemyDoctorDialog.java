package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;
import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.item.*;

public class EnemyDoctorDialog extends Dialog {
	public EnemyDoctorDialog(){
		
		boolean armed = false;
		
		for(Actor a : PFG.s.pc.inventory){
			if(a instanceof Javelin || a instanceof FusionLancePistol) armed = true;
		}
		
		states.put("Start", new HashMap<String, StateTransition>());
		if(armed)
			states.get("Start").put("P", new StateTransition("You enter the pod, weapon drawn", ""));
		else
			states.get("Start").put("P", new StateTransition("You enter the pod", ""));
		
		states.get("Start2").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("In the pod, sitting on the floor is a suprised young " + PFG.s.enemyDoctor.pd.getManWoman() + ", wearing the uniform of the " +
				PFG.s.enemyEmpire.name + " space forces. " + PFG.s.enemyDoctor.pd.getHeShe() + " reaches for a fusion lancer pistol lying on the floor.", ""));
		states.get("Start2").put("1", new StateTransition("Duck around the corner, and retreat", "Retreat"));
		if(armed)
			states.get("Start2").put("1", new StateTransition("Yell \"Freeze!\", holding your weapon out", "Freeze"));
		
		states.put("Freeze", new HashMap<String, StateTransition>());
		states.get("Freeze").put("P", new StateTransition("The " + PFG.s.enemyDoctor.pd.getManWoman() + " seems to consider trying to make a break for the pistol, but decides against it.", ""));
		states.get("Freeze").put("1", new StateTransition("Approach " + PFG.s.enemyDoctor.pd.getHimHer() + " and restrain " + PFG.s.enemyDoctor.pd.getHimHer(), "Restrain"));
		
		states.put("Restrain", new HashMap<String, StateTransition>());
		states.get("Restrain").put("P", new StateTransition("You now have " + PFG.s.enemyDoctor.pd.getHimHer() + " completely tied up with some convienent rope that was in the pod.", ""));
		states.get("Restrain").put("1", new StateTransition("\"Alright, lets start with your name.\"", "Name"));
		states.get("Restrain").put("1", new StateTransition("\"Are there any more of you nearby?\"", "Allies"));
		states.get("Restrain").put("1", new StateTransition("\"Hah, not so tough now, " + PFG.s.enemyEmpire.name + " dog!\"", "Name"));
	}

	@Override
	public void newState(String stateName) {
		if(stateName.equals("Retreat")){
			PFG.s.pc.x -= 3;
			PFG.s.pc.y -= 3;
		}
		super.newState(stateName);
	}
	
	
}
