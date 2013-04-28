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
		states.get("Start").put("P", new StateTransition("How do you enter the pod", ""));
		int x = 1;
		if(armed)
			states.get("Start").put("" + x++, new StateTransition("Enter with weapon drawn", "Armed1"));
		
		states.get("Start").put("" + x++, new StateTransition("Enter unarmed", "Unarmed1"));
		
		states.put("Armed", new HashMap<String, StateTransition>());
		states.get("Armed").put("P", new StateTransition("In the pod, sitting on the floor is a suprised young " + PFG.s.enemyDoctor.pd.getManWoman() + ", wearing the uniform of the " +
				PFG.s.enemyEmpire.name + " space forces. " + PFG.s.enemyDoctor.pd.getHeShe() + " reaches for a fusion lancer pistol lying on the floor.", ""));
		states.get("Armed").put("1", new StateTransition("Yell \"Freeze!\", holding your weapon out", "Freeze"));
		states.get("Armed").put("2", new StateTransition("Duck around the corner", "Duck1"));
		
		states.put("Unarmed1", new HashMap<String, StateTransition>());
		states.get("Unarmed1").put("P", states.get("Armed").get("P"));
		states.get("Unarmed1").put("1", new StateTransition("Hold your hands out, palms first", "Unarmed"));
		states.get("Unarmed1").put("2", new StateTransition("Duck around the corner", "Duck1"));
		
		states.put("Duck1", new HashMap<String, StateTransition>());
		states.get("Duck1").put("P", new StateTransition("You hear " + PFG.s.enemyDoctor.pd.getHimHer() + " yell, \"Why are you here? Leave!\"", ""));
		states.get("Duck1").put("1", new StateTransition("\"No reason, i guess...\"", ""));
		states.get("Duck1").put("2", new StateTransition("Silently leave", ""));
		states.get("Duck1").put("3", new StateTransition("\"Listen, ", ""));
	}
}
