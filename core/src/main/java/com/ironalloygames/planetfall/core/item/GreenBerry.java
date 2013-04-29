package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

import playn.core.Color;

public class GreenBerry extends Berry {
	public GreenBerry(int x, int y, Level lvl) {
		super(x, y);
		this.curLevel = lvl;
	}

	@Override
	public int getColor() {
		return Color.rgb(0, 255, 0);
	}
	
	@Override
	public String getDesc() {
		return "A green colored berry. Tasty?" + super.getDesc();
	}

	@Override
	public void useOnSelf(Unit user) {
		if(!PFG.s.yellowBerriesPoisonous) user.sickness += 0.01f;
		super.useOnSelf(user);
	}
	
	@Override
	public String getName() {
		return "Green Berry";
	}
}
