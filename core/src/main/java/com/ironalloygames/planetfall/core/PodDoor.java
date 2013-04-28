package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.Level.GroundType;

public class PodDoor extends Actor {

	public PodDoor(int x, int y, Level curLevel){
		this.x = x;
		this.y = y;
		this.curLevel = curLevel;
	}
	
	@Override
	public float getIgnitionPoint() {
		return Float.MAX_VALUE;
	}

	@Override
	public float getHeatDamagePoint() {
		return 570;
	}

	@Override
	public void destroyed() {
		curLevel.map[x][y] = GroundType.SHIP_FLOOR;
		super.destroyed();
	}

	@Override
	public String getDesc() {
		return "Metal door. It would require something really hot or destructive to break it. " + super.getDesc();
	}

	@Override
	public float fireDamageMultiplier() {
		return 1.f / 100.f;
	}
	
	@Override
	public String getName() {
		return "Pod Door";
	}

	@Override
	public boolean isPickupable() {
		return false;
	}
}
