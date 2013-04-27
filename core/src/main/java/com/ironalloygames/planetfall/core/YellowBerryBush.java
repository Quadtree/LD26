package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class YellowBerryBush extends BerryBush {
	
	public YellowBerryBush(int x, int y, Level level) {
		super(x, y, level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}

	@Override
	public Class<? extends Berry> getBerry() {
		return YellowBerry.class;
	}
}
