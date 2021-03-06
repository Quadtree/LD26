package com.ironalloygames.planetfall.core.interactable;

import com.ironalloygames.planetfall.core.item.Berry;
import com.ironalloygames.planetfall.core.item.GreenBerry;
import com.ironalloygames.planetfall.core.level.Level;

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
		return new GreenBerry(x,y,curLevel);
	}

	@Override
	public String getDesc() {
		return "A bush with some green colored berries lying around." + super.getDesc();
	}

	@Override
	public String getName() {
		return "Green Berry Bush";
	}
}
