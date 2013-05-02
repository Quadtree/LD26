package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.unit.Unit;

import playn.core.Color;

public class YellowBerry extends Berry {
	public YellowBerry(int x, int y, Level lvl) {
		super(x, y);
		this.curLevel = lvl;
	}

	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}
	
	@Override
	public String getDesc() {
		return "A yellow colored berry. Tasty?" + super.getDesc();
	}
	
	@Override
	public void useOnSelf(Unit user) {
		if(PFG.s.yellowBerriesPoisonous) user.sickness += 0.01f;
		super.useOnSelf(user);
	}
	
	@Override
	public String getName() {
		return "Yellow Berry";
	}
}
