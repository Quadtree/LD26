package com.ironalloygames.planetfall.core;

public abstract class BerryBush extends Actor {
	public abstract int getColor();
	public abstract Berry getBerry(int x, int y);
	
	public BerryBush(int x, int y, Level level){
		this.curLevel = level;
		this.x = x;
		this.y = y;
		if(PFG.s.r.nextInt(3) == 0){
			curLevel.actors.add(getBerry((int)(x + PFG.s.r.nextGaussian() * 2), (int)(y + PFG.s.r.nextGaussian() * 2)));
		}
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '%', getColor());
		super.render();
	}
}
