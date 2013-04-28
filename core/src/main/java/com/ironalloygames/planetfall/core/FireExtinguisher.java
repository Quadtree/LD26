package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.PFG.VisualEffect;

import playn.core.Color;
import pythagoras.f.Matrix3;

public class FireExtinguisher extends Actor {

	public FireExtinguisher(int x, int y, Level lvl){
		this.curLevel = lvl;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'F', Color.rgb(255, 0, 0));
		super.render();
	}

	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Actor user) {
		
		int color = Color.rgb(190, 190, 255);
		
		Matrix3 rotMat = new Matrix3();
		rotMat.setToRotation(dir);
		
		PFG.s.vfx.add(new VisualEffect(0, 6, user.x + (int)(Math.cos(dir) * 1), user.y, color, ')'));
		PFG.s.vfx.add(new VisualEffect(4, 6, user.x + (int)(Math.cos(dir) * 2), user.y - (int)(Math.sin(dir) * 1), color, ')'));
		PFG.s.vfx.add(new VisualEffect(4, 6, user.x + (int)(Math.cos(dir) * 2), user.y, color, ')'));
		PFG.s.vfx.add(new VisualEffect(4, 6, user.x + (int)(Math.cos(dir) * 2), user.y + 1, color, ')'));
		PFG.s.vfx.add(new VisualEffect(8, 6, user.x + (int)(Math.cos(dir) * 3), user.y - 1, color, ')'));
		PFG.s.vfx.add(new VisualEffect(8, 6, user.x + (int)(Math.cos(dir) * 3), user.y, color, ')'));
		PFG.s.vfx.add(new VisualEffect(8, 6, user.x + (int)(Math.cos(dir) * 3), user.y + 1, color, ')'));
		PFG.s.vfx.add(new VisualEffect(12, 6, user.x + (int)(Math.cos(dir) * 4), user.y - 1, color, ')'));
		PFG.s.vfx.add(new VisualEffect(12, 6, user.x + (int)(Math.cos(dir) * 4), user.y, color, ')'));
		PFG.s.vfx.add(new VisualEffect(12, 6, user.x + (int)(Math.cos(dir) * 4), user.y + 1, color, ')'));
		
		super.useInDirection(dir, user);
	}
	
	private void addVfx(int x, int y, int stage){
		PFG.s.vfx.add(new VisualEffect(0, 6, user.x + (int)(Math.cos(dir) * 1), user.y, color, ')'));
	}

}
