package com.ironalloygames.planetfall.core.dialog;

import java.util.HashMap;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.item.FusionLancePistol;
import com.ironalloygames.planetfall.core.item.Javelin;

public class BadEnding extends Dialog {
	private static final String STATE_THE_END = "TheEnd";
	private static final String STATE_POW = "POW";
	private static final String STATE_BLAZE_OF_GLORY2 = "BlazeOfGlory2";
	private static final String STATE_BLAZE_OF_GLORY1 = "BlazeOfGlory1";
	private static final String STATE_BREAK = "Break";
	private static final String STATE_STUPID1 = "Stupid1";
	private static final String STATE_SURRENDER = "Surrender";
	private static final String STATE_START2 = "Start2";
	private static final String STATE_START = "Start";

	public BadEnding(){
		
		states.put(STATE_START,
			new State("", "You hear a thunderous roar. Looking up, you are horrified to see a " + PFG.s.enemyEmpire.name + " assault transport descending towards you.")
			.add(STATE_START2)
			.add(STATE_STUPID1)
			.add(STATE_BLAZE_OF_GLORY1)
		);
		
		states.put(STATE_START2,
			new State("Duck behind a rock", "After the transport lands, they seem to zero in on your location anyway (maybe they saw you from the air).")
			.add(STATE_SURRENDER)
			.add(STATE_BREAK)
			.add(STATE_BLAZE_OF_GLORY2)
		);
		
		states.put(STATE_SURRENDER, new State("Surrender", "You let the marines take you into custody.").add(STATE_POW));
		
		states.put(STATE_STUPID1, new State("Wave to get their attention", "The transport lands right next to you, and a platoon of space marines come out. Seeing your uniform, they take you into custody.").add(STATE_POW));
		
		states.put(STATE_BREAK, new State("Make a break for it", "Unfortunately, your puny human legs just can't outrun power armor wearing space marines, and they quickly catch you and take you into custody.")
			.add(STATE_POW));
		
		states.put(STATE_BLAZE_OF_GLORY1,
			new State("Start firing at them", "Your tiny pea-shooter just can't seem to penetrate the assault transport's shields. After it lands, the space marines inside quickly arrest you.")
			.add(STATE_POW)
			.add(new State.PlayerIsArmed())
		);
		
		states.put(STATE_BLAZE_OF_GLORY2,
			new State("Open fire", "Despite firing what seems to you like a ton of shots, you fail to hit anything except rocks and grass. Once you're out of ammo, the space marines close and grab you.")
			.add(STATE_POW)
			.add(new State.PlayerIsArmed())
		);
		
		states.put(STATE_POW,
			new State("Continue", "You spend the rest of the war in a POW camp, subsisting on bread and water.")
			.add(STATE_THE_END)
		);
		
		states.put(STATE_THE_END,
			new State("Continue", "The End (Refresh to play again)")
		);
	}
}
