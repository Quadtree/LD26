package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class YellowBerryBush extends BerryBush {
	
	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}

	@Override
	public Class<? extends Berry> getBerry() {
		return YellowBerry.class;
	}
}
