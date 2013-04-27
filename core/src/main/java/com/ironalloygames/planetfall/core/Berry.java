package com.ironalloygames.planetfall.core;

public abstract class Berry extends Actor {
	public abstract int getColor();
	
	@Override
	public void update() {
		PFG.s.setCharAtReal(x, y, '+', getColor());
		super.update();
	}
}
