package com.ironalloygames.planetfall.core;

public class Unit extends Actor {
	public int actionTimer = 1;
	
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

	@Override
	public float getHeatGainMultiplier() {
		return 0.18f;
	}

	@Override
	public int getRenderPriority() {
		return super.getRenderPriority()+1;
	}

	@Override
	public float getHeatDamagePoint() {
		return 345;
	}
	
	public float foodNeed = 0;
	public float sickness = 0;

	@Override
	public float fireDamageMultiplier() {
		return 1.f / 20.f;
	}
}
