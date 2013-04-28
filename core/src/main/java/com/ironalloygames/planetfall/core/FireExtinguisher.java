package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.PFG.VisualEffect;

import playn.core.Color;
import pythagoras.f.Matrix3;
import pythagoras.f.Vector3;

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
	
	Matrix3 rotMat = new Matrix3();
	Actor user;
	int color;

	@Override
	public void useInDirection(float dir, Actor user) {
		this.user = user;
		color = Color.rgb(190, 190, 255);
		
		
		rotMat.setToRotation(dir);
		
		addVfx(1, 0, 0);
		addVfx(2, -1, 1);
		addVfx(2, 0, 1);
		addVfx(2, 1, 1);
		addVfx(3, -1, 2);
		addVfx(3, 0, 2);
		addVfx(3, 1, 2);
		addVfx(4, -1, 3);
		addVfx(4, 0, 3);
		addVfx(4, 1, 3);
		
		super.useInDirection(dir, user);
	}
	
	private void addVfx(int x, int y, int stage){
		Vector3 pos = new Vector3(x,y,1);
		rotMat.transformLocal(pos);
		int px = (int)(user.x + pos.x);
		int py = (int)(user.y + pos.y);
		PFG.s.vfx.add(new VisualEffect(stage*4, 6, px, py, color, '*'));
		
		for(Actor a : PFG.s.currentLevel.actors)
		{
			if(a.x == px && a.y == py){
				a.temperature = ((a.temperature - 280) * 0.5f) + 280;
			}
		}
	}

}
