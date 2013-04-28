package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.dialog.Dialog.StateTransition;
import com.ironalloygames.planetfall.core.item.FusionLancePistol;
import com.ironalloygames.planetfall.core.item.Javelin;

public class BadEnding extends Dialog {
	public BadEnding(){
		boolean armed = false;
		
		for(Actor a : PFG.s.pc.inventory){
			if(a instanceof FusionLancePistol) armed = true;
		}
		
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("You hear a thunderous roar. Looking up, you are horrified to see a " + PFG.s.enemyEmpire.name + " assault transport descending towards you.", ""));
		states.get("Start").put("1", new StateTransition("Duck behind a rock", "Start2"));
		states.get("Start").put("2", new StateTransition("Wave to get their attention", "Stupid1"));
		
		if(armed)
			states.get("Start").put("3", new StateTransition("Start firing at them", "BlazeOfGlory1"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("After the transport lands, they seem to zero in on your location anyway (maybe they saw you from the air).", ""));
		states.get("Start2").put("1", new StateTransition("Surrender", "Surrender"));
		states.get("Start2").put("2", new StateTransition("Make a break for it", "Break"));
		
		if(armed)
			states.get("Start2").put("3", new StateTransition("Open fire", "BlazeOfGlory2"));
		
		states.put("Surrender", new HashMap<String, StateTransition>());
		states.get("Surrender").put("P", new StateTransition("You let the marines take you into custody.", ""));
		states.get("Surrender").put("1", new StateTransition("Continue", "POW"));
		
		states.put("Stupid1", new HashMap<String, StateTransition>());
		states.get("Stupid1").put("P", new StateTransition("The transport lands right next to you, and a platoon of space marines come out. Seeing your uniform, they take you into custody.", ""));
		states.get("Stupid1").put("1", new StateTransition("Continue", "POW"));
		
		states.put("Break", new HashMap<String, StateTransition>());
		states.get("Break").put("P", new StateTransition("Unfortunately, your puny human legs just can't outrun power armor wearing space marines, and they quickly catch you and take you into custody.", ""));
		states.get("Break").put("1", new StateTransition("Continue", "POW"));
		
		states.put("BlazeOfGlory1", new HashMap<String, StateTransition>());
		states.get("BlazeOfGlory1").put("P", new StateTransition("Your tiny pea-shooter just can't seem to penetrate the assault transport's shields. After it lands, the space marines inside quickly arrest you.", ""));
		states.get("BlazeOfGlory1").put("1", new StateTransition("Continue", "POW"));
		
		states.put("BlazeOfGlory2", new HashMap<String, StateTransition>());
		states.get("BlazeOfGlory2").put("P", new StateTransition("Despite firing what seems to you like a ton of shots, you fail to hit anything except rocks and grass. Once you're out of ammo, the space marines close and grab you.", ""));
		states.get("BlazeOfGlory2").put("1", new StateTransition("Continue", "POW"));
		
		states.put("POW", new HashMap<String, StateTransition>());
		states.get("POW").put("P", new StateTransition("You spend the rest of the war in a POW camp, subsisting on bread and water.", ""));
		states.get("POW").put("1", new StateTransition("Continue", "TheEnd"));
		
		states.put("TheEnd", new HashMap<String, StateTransition>());
		states.get("TheEnd").put("P", new StateTransition("The End (Refresh to play again)", ""));
	}
}
