package com.ironalloygames.planetfall.core.interactable;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.level.Level;

import playn.core.Color;

public class CommGear extends Actor {

	@Override
	public String getName() {
		return "Comm Gear";
	}

	public CommGear(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		hp = 100000;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'G', Color.rgb(255, 255, 255));
		super.render();
	}

	@Override
	public String getDesc() {
		return "Long range comm gear. Only the comm officer would know how to use it, however.";
	}

	@Override
	public float getIgnitionPoint() {
		return 100000;
	}

	@Override
	public boolean isPickupable() {
		return false;
	}
	
	
}
