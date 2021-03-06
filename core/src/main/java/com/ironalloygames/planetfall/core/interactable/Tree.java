package com.ironalloygames.planetfall.core.interactable;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.item.Wood;
import com.ironalloygames.planetfall.core.level.Level;

import playn.core.Color;
import playn.core.PlayN;

public class Tree extends Actor {
	
	public Tree(int x, int y, Level curLevel){
		this.x = x;
		this.y = y;
		this.curLevel = curLevel;
		
		if(PFG.s.r.nextInt(3) == 0){
			curLevel.actors.add(new Wood((int)(x + PFG.s.r.nextGaussian() * 2), (int)(y + PFG.s.r.nextGaussian() * 2), curLevel));
		}
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'Y', fixCol(Color.rgb(0, 128, 0)));
		PFG.s.setCharAtReal(x, y - 1, '@', fixCol(Color.rgb(0, 128, 0)));
		
		super.render();
	}

	@Override
	public String getDesc() {
		return "A large tree. Probably too heavy to carry" + super.getDesc();
	}

	@Override
	public boolean isPickupable() {
		return false;
	}

	@Override
	public float fireDamageMultiplier() {
		return 1.f / 1500.f;
	}
	
	@Override
	public String getName() {
		return "Tree";
	}
}
