package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;

public class AntiSicknessPill extends Actor {
	public AntiSicknessPill(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'o', fixCol(Color.rgb(255, 255, 255)));
		super.render();
	}
}
