package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

public class DirtyWaterCanteen extends Actor {
	public DirtyWaterCanteen(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'U', fixCol(Color.rgb(255, 255, 255)));
		super.render();
	}
	
	@Override
	public boolean isCraftable(Unit user) {
		for(Actor a : curLevel.actors){
			if(a.temperature > 400 && Math.abs(x - a.x) < 5 && Math.abs(y - a.y) < 5){
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void craft(Unit user) {
		user.inventory.remove(this);
		user.inventory.add(new FilledCanteen(0,0,user.curLevel));
		super.craft(user);
	}

	@Override
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		user.waterNeed = Math.max(user.waterNeed - 0.5f, 0);
		user.sickness += 0.01f;
		
		user.inventory.remove(this);
		user.inventory.add(new Canteen(0,0,user.curLevel));
		
		super.useOnSelf(user);
	}
}
