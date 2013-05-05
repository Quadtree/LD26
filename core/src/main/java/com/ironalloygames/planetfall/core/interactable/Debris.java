package com.ironalloygames.planetfall.core.interactable;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.level.Level;

public class Debris extends Actor {

	public Debris(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
	}
	
	@Override
	public String getName() {
		return "Debris";
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '%', fixCol(Color.rgb(128, 128, 128)));
		
		super.render();
	}

}
