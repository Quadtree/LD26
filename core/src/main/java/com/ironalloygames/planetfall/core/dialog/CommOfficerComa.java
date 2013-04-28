package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.dialog.Dialog.StateTransition;

public class CommOfficerComa extends Dialog {
	public CommOfficerComa(){
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("You open the pod, and notice someone lying on the floor in the middle. Judging by their uniform, they are also a member of the " +
				PFG.s.alliedEmpire.name + " space forces. They don't move to acknowledge your entry.", ""));
		states.get("Start").put("1", new StateTransition("Examine " + PFG.s.commOfficer.pd.getHimHer(), "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("Getting closer, you realize that it is Lieutenant " + PFG.s.commOfficer.pd.lastName +
				", the chief comms officer of the " + PFG.s.alliedShip.name + ". You remember shaking " + PFG.s.commOfficer.pd.getHisHer() + " hand when you first boarded the " + PFG.s.alliedShip.name + ". From the amount of dried blood, you can " +
				"surmise that " + PFG.s.commOfficer.pd.getHeShe() + "'s hurt very badly. There is an IV running into " + PFG.s.commOfficer.pd.getHisHer() + " arm.", ""));
		states.get("Start2").put("1", new StateTransition("Try to wake " + PFG.s.commOfficer.pd.getHimHer(), "Wake"));
		states.get("Start2").put("2", new StateTransition("Examine " + PFG.s.commOfficer.pd.getHisHer() + " wounds", "Wounds"));
		states.get("Start2").put("3", new StateTransition("Leave", "End"));
		
		states.put("Wake", new HashMap<String, StateTransition>());
		states.get("Wake").put("P", new StateTransition("You shake and prod " + PFG.s.commOfficer.pd.getHimHer() + ", but " + PFG.s.commOfficer.pd.getHeShe() + " does not respond.", ""));
		states.get("Wake").put("1", new StateTransition("Examine " + PFG.s.commOfficer.pd.getHisHer() + " wounds", "Wounds"));
		states.get("Wake").put("2", new StateTransition("Leave", "End"));
		
		states.put("Wounds", new HashMap<String, StateTransition>());
		states.get("Wounds").put("P", new StateTransition(PFG.s.commOfficer.pd.genderSpecific("He", "She") + " has several deep gashes, probably from falling debris. These have become infected, and " +
				PFG.s.commOfficer.pd.getHeShe() + " is now running a high fever. This is well beyond your ability to treat. You're a mechanic, not a doctor.", ""));
		states.get("Wounds").put("1", new StateTransition("Try to wake " + PFG.s.commOfficer.pd.getHimHer(), "Wake"));
		states.get("Wounds").put("2", new StateTransition("Leave", "End"));
	}
}
