package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;

public class CommOfficerComa extends Dialog {
	enum S {
		Start,
		Start2,
		Wounds,
		Wake,
		End
	}

	public CommOfficerComa(){
		
		states.put(S.Start.toString(), new State("",
			"You open the pod, and notice someone lying on the floor in the middle. Judging by their uniform, they are also a member of the " +
			PFG.s.alliedEmpire.name + " space forces. " + PFG.s.commOfficer.pd.genderSpecific("He", "She") + " does not move to acknowledge your entry.")
			.add(S.Start2.toString())
		);
		
		states.put(S.Start2.toString(),
			new State("Examine " + PFG.s.commOfficer.pd.getHimHer(),
					"Getting closer, you realize that it is Lieutenant " + PFG.s.commOfficer.pd.lastName +
					", the chief comms officer of the " + PFG.s.alliedShip.name + ". You remember shaking " + PFG.s.commOfficer.pd.getHisHer() + " hand when you first boarded the " + PFG.s.alliedShip.name + ". From the amount of dried blood, you can " +
					"surmise that " + PFG.s.commOfficer.pd.getHeShe() + "'s hurt very badly. There is an IV running into " + PFG.s.commOfficer.pd.getHisHer() + " arm.")
			.add(S.Wake.toString())
			.add(S.Wounds.toString())
			.add(S.End.toString())
		);
		
		states.put(S.Wake.toString(),
			new State("Try to wake " + PFG.s.commOfficer.pd.getHimHer(), "You shake and prod " + PFG.s.commOfficer.pd.getHimHer() + ", but " + PFG.s.commOfficer.pd.getHeShe() + " does not respond.")
			.add(S.Wounds.toString())
			.add(S.End.toString())
		);
		
		states.put(S.Wounds.toString(), 
			new State("Examine " + PFG.s.commOfficer.pd.getHisHer() + " wounds",
					PFG.s.commOfficer.pd.genderSpecific("He", "She") + " has several deep gashes, probably from falling debris. These have become infected, and " +
					PFG.s.commOfficer.pd.getHeShe() + " is now running a high fever. This is well beyond your ability to treat. You're a mechanic, not a doctor.")
			.add(S.Wake.toString())
			.add(S.End.toString())
		);
		
		states.put(S.End.toString(), new State("Leave", "").add(new State.End()));
	}
}
