package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class GreenBerry extends Berry {
	public GreenBerry(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}
	
	@Override
	public String getDesc() {
		return "A green colored berry. Tasty?";
	}
}
