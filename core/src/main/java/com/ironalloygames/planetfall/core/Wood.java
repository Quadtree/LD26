package com.ironalloygames.planetfall.core;

import playn.core.Color;

public class Wood extends Actor {
	public Wood(int x, int y){
		this.x = x;
		this.y = y;
		
		
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'w', Color.rgb(255, 128, 0));
		
		super.render();
	}
	
	@Override
	public String getDesc() {
		return "Some wood. Good for fires!" + super.getDesc();
	}
}
