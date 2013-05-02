package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.level.Level;

import playn.core.Color;

public class StarshipFuel extends Actor {
	
	public StarshipFuel(int x, int y, Level curLevel){
		this.x = x;
		this.y = y;
		this.curLevel = curLevel;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '#', fixCol(Color.rgb(0, 128, 255)));
		super.render();
	}

	@Override
	public String getDesc() {
		return "Pool of extremely flammable starship fuel.";
	}

	@Override
	public float getIgnitionPoint() {
		return 450;
	}

	@Override
	public float getBurnTemperature() {
		return 700;
	}

	@Override
	public float fireDamageMultiplier() {
		return 1.f / 2000.f;
	}
	
	@Override
	public String getName() {
		return "Starship Fuel";
	}

	@Override
	public boolean isPickupable() {
		return false;
	}
}
