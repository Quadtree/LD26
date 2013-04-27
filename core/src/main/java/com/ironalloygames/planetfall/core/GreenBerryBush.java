package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class GreenBerryBush extends BerryBush {

	public GreenBerryBush(int x, int y, Level level) {
		super(x, y, level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColor() {
		return Color.rgb(0, 255, 0);
	}

	@Override
	public Berry getBerry(int x, int y) {
		return new GreenBerry(x,y);
	}

}
