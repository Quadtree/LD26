package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class PC extends Unit {

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '@', Color.rgb(255, 255, 255));
		
		super.update();
	}

}
