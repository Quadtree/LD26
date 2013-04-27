package com.ironalloygames.planetfall.core;

public abstract class Berry extends Actor {
	public abstract int getColor();
	
	public Berry(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '+', getColor());
		super.update();
	}
}
