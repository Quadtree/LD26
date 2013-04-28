package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;

import playn.core.Color;

public class Wood extends Actor {
	public Wood(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		
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
