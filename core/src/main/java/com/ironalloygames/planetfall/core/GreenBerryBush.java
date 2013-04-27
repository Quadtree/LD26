package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class GreenBerryBush extends BerryBush {

	@Override
	public int getColor() {
		return Color.rgb(0, 255, 0);
	}

	@Override
	public Class<? extends Berry> getBerry() {
		return GreenBerry.class;
	}

}
