package com.ironalloygames.planetfall.core;

public class Unit extends Actor {
	int actionTimer = 1;
	
	public void move(int dx, int dy){
		if(curLevel.isPassable(x + dx, y + dy)){
			x += dx;
			y += dy;
			
			actionTimer = 5;
		}
	}
	
	public boolean canAct(){
		return actionTimer <= 0;
	}

	@Override
	public void update() {
		actionTimer--;
		super.update();
	}
}