package com.ironalloygames.planetfall.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.PlanetLevel;
import com.ironalloygames.planetfall.core.Level.GroundType;
import com.ironalloygames.planetfall.dialog.Dialog.StateTransition;

public class EscapeShip extends Dialog {
	public EscapeShip(){
		states.put("StartAlt", new HashMap<String, StateTransition>());
		states.get("StartAlt").put("P", new StateTransition("The fire is just too hot! You are forced back.", ""));
		states.get("StartAlt").put("1", new StateTransition("Continue", "Start"));
		
		states.put("Start", new HashMap<String, StateTransition>());
		states.get("Start").put("P", new StateTransition("You hear a voice over the PA", ""));
		states.get("Start").put("1", new StateTransition("Continue", "Start2"));
		
		states.put("Start2", new HashMap<String, StateTransition>());
		states.get("Start2").put("P", new StateTransition("Captain " + PFG.s.captain.lastName + ": \"All crew are to make for their designated lifepods! I repeat, all crew are to evacuate to their designated lifepods!\"", ""));
		states.get("Start2").put("1", new StateTransition("Rush to the lifepod", "Landing"));
		
		states.put("Landing", new HashMap<String, StateTransition>());
		states.get("Landing").put("P", new StateTransition("Your reverie is suddenly disrupted by the pod's retro-rockets firing. You must be about to land on a planet.", ""));
		states.get("Landing").put("1", new StateTransition("Wait for the pod to settle", "Landing2"));
		
		states.put("Landing2", new HashMap<String, StateTransition>());
		states.get("Landing2").put("P", new StateTransition("The pod finishes landing automatically.", ""));
		states.get("Landing2").put("1", new StateTransition("Check the atmospheric sensors", "Atmo"));
		states.get("Landing2").put("2", new StateTransition("Open the outside doors", "OutsideDoors"));
		states.get("Landing2").put("3", new StateTransition("Check the alarm", "CheckAlarm"));
		
		states.put("Atmo", new HashMap<String, StateTransition>());
		states.get("Atmo").put("P", new StateTransition("Sensors indicate that the air outside is safe to breathe.", ""));
		states.get("Atmo").put("1", new StateTransition("Open the outside doors", "OutsideDoors"));
		states.get("Atmo").put("2", new StateTransition("Check the alarm", "CheckAlarm"));
		
		states.put("OutsideDoors", new HashMap<String, StateTransition>());
		states.get("OutsideDoors").put("P", new StateTransition("The outside door opens. You are relieved not to be overwhelmed by toxins.", ""));
		states.get("OutsideDoors").put("1", new StateTransition("Check the alarm", "CheckAlarm"));
		states.get("OutsideDoors").put("2", new StateTransition("Venture forth into the unknown!", "End"));
		
		states.put("CheckAlarm", new HashMap<String, StateTransition>());
		states.get("CheckAlarm").put("P", new StateTransition("You finally manage to take a look at the poorly placed alarm. It indicates that the distress beacon is non-functional due to missing components.", ""));
		states.get("CheckAlarm").put("1", new StateTransition("Examine the beacon", "BeaconCheck"));
		
		states.put("BeaconCheck", new HashMap<String, StateTransition>());
		states.get("BeaconCheck").put("P", new StateTransition("You examine the beacon. Several components are indeed missing. There is a note on it: \"Removed components pending replacement due to potential overheating\" -- " + PFG.s.pc.pd.firstName.substring(0, 1) + PFG.s.pc.pd.lastName.substring(0, 1), ""));
		states.get("BeaconCheck").put("1", new StateTransition("Check the atmospheric sensors", "Atmo"));
		states.get("BeaconCheck").put("2", new StateTransition("Open the outside doors", "OutsideDoors"));
	}
	
	@Override
	public void newState(String stateName){
		if(stateName.equals("OutsideDoors")){
			states.get("Atmo").put("1", new StateTransition("Venture forth into the unknown!", "End"));
			states.get("BeaconCheck").put("2", new StateTransition("Venture forth into the unknown!", "End"));
			
			PFG.s.currentLevel.map[PFG.s.pc.x][PFG.s.pc.y + 3] = GroundType.SHIP_FLOOR;
		}
		if(stateName.equals("Landing")){
			PFG.s.currentLevel.actors.remove(PFG.s.pc);
			PFG.s.currentLevel = PFG.s.planetLevel;
			PFG.s.currentLevel.actors.add(PFG.s.pc);
			PFG.s.pc.curLevel = PFG.s.planetLevel;
			
			PFG.s.pc.x = PFG.s.planetLevel.pcLifepodX;
			PFG.s.pc.y = PFG.s.planetLevel.pcLifepodY;
			PFG.s.pc.temperature = PFG.s.planetLevel.ambientTemp;
		}
	}
}
