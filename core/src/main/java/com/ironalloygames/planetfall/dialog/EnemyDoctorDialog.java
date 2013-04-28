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
		states.get("Start").put("P", new StateTransition("From seeing the outside of this pod, you're very suspicious, so you enter with weapon drawn.", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("In the pod, sitting on the floor is a suprised young " + PFG.s.enemyDoctor.pd.getManWoman() + ", wearing the uniform of the " +
				PFG.s.enemyEmpire.name + " space forces. " + PFG.s.enemyDoctor.pd.getHeShe() + " reaches for a fusion lancer pistol lying on the floor.", ""));
		int x = 1;
		states.get("Start2").put("" + x++, new StateTransition("Yell \"Freeze!\", holding your weapon out", "Freeze"));
		
		if(hasSpear) states.get("Start2").put("" + x++, new StateTransition("Charge and run " + PFG.s.enemyDoctor.pd.getHimHer() + " through with your spear", "SpearAttack"));
		if(hasJavelin) states.get("Start2").put("" + x++, new StateTransition("Throw your javelin at " + PFG.s.enemyDoctor.pd.getHimHer(), "JavelinAttack"));
		if(hasGun) states.get("Start2").put("" + x++, new StateTransition("Shoot " + PFG.s.enemyDoctor.pd.getHimHer() + " with your pistol", "GunAttack"));
		
		states.put("SpearAttack", new HashMap<String, StateTransition>());
		states.get("SpearAttack").put("P", new StateTransition("Charging an enemy armed with a fusion lancer pistol armed only with a spear turns out to be a bad idea. The last thing you see is a jet of blue-green light cutting through your chest.", ""));
		states.get("SpearAttack").put("1", new StateTransition("Continue", "End"));
		
		states.put("SpearAttack", new HashMap<String, StateTransition>());
		states.get("SpearAttack").put("P", new StateTransition("Your javelin strikes " + PFG.s.enemyDoctor.pd.getHimHer() + " in the chest right as " + PFG.s.enemyDoctor.pd.getHeShe() +
				" is levelling " + PFG.s.enemyDoctor.pd.getHisHer() + " pistol " + PFG.s.enemyDoctor.pd.getHeShe() + " crumples over backwards.", ""));
		states.get("SpearAttack").put("1", new StateTransition("Continue", "End"));
	}
}
