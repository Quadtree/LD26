package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class StarshipFuel extends Actor {
	
	public StarshipFuel(int x, int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '#', fixCol(Color.rgb(0, 128, 255)));
		super.render();
	}

	@Override
	public String getDesc() {
		return "Pool of extremely flammable starship fuel. " + super.getDesc();
	}

	@Override
	public float getIgnitionPoint() {
		return 450;
	}

	@Override
	public float getBurnTemperature() {
		return 1200;
	}
	
}