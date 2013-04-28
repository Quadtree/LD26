package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class FusionTorch extends Actor {

	public FusionTorch(int x, int y, Level lvl){
		this.curLevel = lvl;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'T', Color.rgb(0, 192, 255));
		super.render();
	}

}
