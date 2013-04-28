package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.PFG.VisualEffect;

import playn.core.Color;
import pythagoras.f.Matrix3;

public class FusionTorch extends Actor {

	public FusionTorch(int x, int y, Level lvl){
		this.curLevel = lvl;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'T', fixCol(Color.rgb(0, 192, 255)));
		super.render();
	}

	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Unit user) {
		int tx = user.x + (int)(Math.cos(dir) * 1.5);
		int ty = user.y + (int)(Math.sin(dir) * 1.5);
		
		PFG.s.vfx.add(new VisualEffect(0, 12, tx, ty, Color.rgb(0, 192, 255), '%'));
		
		for(Actor a : PFG.s.currentLevel.actors)
		{
			if(a.x == tx && a.y == ty){
				a.temperature += 200 * a.getHeatGainMultiplier();
			}
		}
	}
}
