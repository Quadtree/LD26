package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;

import playn.core.Color;

public class Kindling extends Actor {
	public Kindling(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'k', fixCol(Color.rgb(255, 128, 0)));
		
		super.render();
	}
	
	@Override
	public String getDesc() {
		return "Some kindling. Good for starting fires with flint!" + super.getDesc();
	}

	@Override
	public float getHeatGainMultiplier() {
		return 5;
	}

	@Override
	public float fireDamageMultiplier() {
		return 1.f / 200.f;
	}
}
