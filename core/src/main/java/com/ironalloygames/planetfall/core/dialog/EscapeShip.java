package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.info.Person;
import com.ironalloygames.planetfall.core.item.AntiSicknessPill;
import com.ironalloygames.planetfall.core.item.LifeformDetector;
import com.ironalloygames.planetfall.core.item.PowerCell;
import com.ironalloygames.planetfall.core.item.RawMeat;
import com.ironalloygames.planetfall.core.item.StarshipFuelCan;
import com.ironalloygames.planetfall.core.level.PlanetLevel;
import com.ironalloygames.planetfall.core.level.Level.GroundType;

public class EscapeShip extends Dialog {
	public EscapeShip(){
		states.put("GunnerFailed", new State("", "As your are sucked through the hole, you somehow manage to snag some torn-up wiring sticking out of the side of the hole.")
			.add("GunnerFailed2")
		);
		
		states.put("GunnerFailed2", new State("Hang on with all your might", "As hard as you try, your grip is slipping, and you can barely breathe in the thin air...")
			.add("GunnerFailed3")
		);
		
		Person marine = new Person();
		
		states.put("GunnerFailed3", new State("Continue", "Abruptly, a power-armor covered arm grabs yours and pulls you in. It's one of your ship's space marines. " + marine.genderSpecific("He", "She") + " carries you to a lifepod, and then dashes off as the doors close...")
			.add("LandingCon")
		);
		
		states.put("StartAlt", new State("", "The fire is just too hot! You are forced back.")
			.add("Start")
		);
		
		states.put("Start", new State("Continue", "You hear a voice over the PA system.")
			.add("Start2")
		);
		
		states.put("Start2", new State("Continue", "Captain " + PFG.s.captain.lastName + ": \"All crew are to make for their designated lifepods! " +
			"I repeat, all crew are to evacuate to their designated lifepods!\"")
			.add("Landing")
		);
		
		states.put("LandingCon", new State("Continue", "Your reverie is suddenly disrupted by the pod's retro-rockets firing. You must be about to land on a planet.")
			.add("Landing2")
			.add(new ReverieDisrupted())
		);
		
		states.put("Landing", new State("Rush to the lifepod", "Your reverie is suddenly disrupted by the pod's retro-rockets firing. You must be about to land on a planet.")
			.add("Landing2")
			.add(new ReverieDisrupted())
		);
		
		states.put("Landing2", new State("Wait for the pod to settle", "The pod finishes landing automatically.")
			.add("Atmo")
			.add("OutsideDoors")
			.add("CheckAlarm")
			.add("VentureForth")
		);
		
		states.put("Atmo", new State("Check the atmospheric sensors", "Sensors indicate that the air outside is safe to breathe.")
			.add("OutsideDoors")
			.add("CheckAlarm")
			.add("VentureForth")
		);
		
		states.put("OutsideDoors", new State("Open the outside doors", "The outside door opens. You are relieved not to be overwhelmed by toxins.")
			.add("CheckAlarm")
			.add("VentureForth")
			.add(new State.ChangeListener(){

				@Override
				public void stateChanged(State s) {
					PFG.s.podDoorsOpen = true;
					PFG.s.currentLevel.map[PFG.s.pc.x][PFG.s.pc.y + 3] = GroundType.SHIP_FLOOR;
				}
			})
		);
		
		states.put("CheckAlarm", new State("Check the alarm", "You finally manage to take a look at the poorly placed alarm. "+
				"It indicates that the distress beacon is non-functional due to missing components.")
			.add("BeaconCheck")
		);
		
		states.put("BeaconCheck", new State(
			"Examine the beacon",
			"You examine the beacon. Several components are indeed missing. There is a note on it: \"Removed components pending replacement due to potential overheating\" -- " +
			PFG.s.pc.pd.firstName.substring(0, 1) + PFG.s.pc.pd.lastName.substring(0, 1))
			.add("Atmo")
			.add("OutsideDoors")
		);
		
		states.put("VentureForth", new State(
			"Venture forth into the unknown!",
			"You search the various lockers on the pod for any useful items.")
			.add("End")
			.add(new State.ChangeConditional(){

				@Override
				public boolean allowChange(State s) {
					return PFG.s.podDoorsOpen;
				}
			})
			.add(new State.ChangeListener() {
				
				@Override
				public void stateChanged(State s) {
					PFG.s.planetLevel.actors.add(new PowerCell(PFG.s.planetLevel.pcLifepodX, PFG.s.planetLevel.pcLifepodY+1, PFG.s.planetLevel));
					PFG.s.planetLevel.actors.add(new PowerCell(PFG.s.planetLevel.pcLifepodX, PFG.s.planetLevel.pcLifepodY+1, PFG.s.planetLevel));
					PFG.s.planetLevel.actors.add(new AntiSicknessPill(PFG.s.planetLevel.pcLifepodX+1, PFG.s.planetLevel.pcLifepodY+1, PFG.s.planetLevel));
					PFG.s.planetLevel.actors.add(new AntiSicknessPill(PFG.s.planetLevel.pcLifepodX+1, PFG.s.planetLevel.pcLifepodY+1, PFG.s.planetLevel));
					PFG.s.planetLevel.actors.add(new PowerCell(PFG.s.planetLevel.pcLifepodX, PFG.s.planetLevel.pcLifepodY+1, PFG.s.planetLevel));
					PFG.s.planetLevel.actors.add(new StarshipFuelCan(PFG.s.planetLevel.pcLifepodX - 1, PFG.s.planetLevel.pcLifepodY+1, PFG.s.planetLevel));
					PFG.s.planetLevel.actors.add(new LifeformDetector(PFG.s.planetLevel.pcLifepodX - 2, PFG.s.planetLevel.pcLifepodY, PFG.s.planetLevel));
				}
			})
		);
		
		states.put("End", new State("Continue", "").add(new State.End()));
	}
	
	static class ReverieDisrupted implements State.ChangeListener {

		@Override
		public void stateChanged(State s) {
			PFG.s.currentLevel.actors.remove(PFG.s.pc);
			PFG.s.currentLevel = PFG.s.planetLevel;
			PFG.s.currentLevel.actors.add(PFG.s.pc);
			PFG.s.pc.curLevel = PFG.s.planetLevel;
			
			PFG.s.pc.x = PFG.s.planetLevel.pcLifepodX;
			PFG.s.pc.y = PFG.s.planetLevel.pcLifepodY;
			PFG.s.pc.temperature = PFG.s.planetLevel.ambientTemp;
			PFG.s.pc.hp = 1;
		}
	}
}
