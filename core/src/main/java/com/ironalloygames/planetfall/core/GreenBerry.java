package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class GreenBerry extends Berry {
	public GreenBerry(int x, int y, Level lvl) {
		super(x, y);
		this.curLevel = lvl;
	}

	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}
	
	@Override
	public String getDesc() {
		return "A green colored berry. Tasty?" + super.getDesc();
	}
}
