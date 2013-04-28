package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;
import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.item.*;

public class EnemyDoctorDialog extends Dialog {
	public EnemyDoctorDialog(){
		
		boolean armed = false;
		
		boolean hasSpear = false;
		boolean hasJavelin = false;
		boolean hasGun = false;
		
		for(Actor a : PFG.s.pc.inventory){
			if(a instanceof Spear || a instanceof Javelin || a instanceof FusionLancePistol) armed = true;
			if(a instanceof Spear) hasSpear = true;
			if(a instanceof Javelin) hasJavelin = true;
			if(a instanceof FusionLancePistol) hasGun = true;
		}
		
		states.put("Start", new HashMap<String, StateTransition>());
		if(armed)
			states.get("Start").put("P", new StateTransition("From seeing the outside of this pod, you're very suspicious, so you enter with weapon drawn.", ""));
		else
			states.get("Start").put("P", new StateTransition("You enter the unknown pod.", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("In the pod, sitting on the floor is a suprised young " + PFG.s.enemyDoctor.pd.getManWoman() + ", wearing the uniform of the " +
				PFG.s.enemyEmpire.name + " space forces. " + PFG.s.enemyDoctor.pd.getHeShe() + " reaches for a fusion lancer pistol lying on the floor.", ""));
		int x = 1;
		if(armed)
			states.get("Start2").put("" + x++, new StateTransition("Yell \"Freeze!\", holding your weapon out", "Freeze"));
		else
			states.get("Start2").put("" + x++, new StateTransition("Hold your hands out, palms first", "Unarmed"));
		
		states.get("Start2").put("" + x++, new StateTransition("Duck around the corner", "Duck1"));
	}
}
