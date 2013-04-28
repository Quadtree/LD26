package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;

public class FusionLancePistol extends Actor {
	public FusionLancePistol(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'L', Color.rgb(0, 190, 255));
		super.render();
	}
}
