package com.ironalloygames.planetfall.core;

public abstract class BerryBush extends Actor {
	public abstract int getColor();
	public abstract Class<? extends Berry> getBerry();
	
	public BerryBush(int x, int y, Level level){
		this.curLevel = level;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void update() {
		PFG.s.setCharAtReal(x, y, '%', getColor());
		super.update();
	}
}
