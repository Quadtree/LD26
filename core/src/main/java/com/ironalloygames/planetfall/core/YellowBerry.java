package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class YellowBerry extends Berry {
	public YellowBerry(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}
}
