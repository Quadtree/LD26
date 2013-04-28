package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

public class FilledCanteen extends Actor {
	public FilledCanteen(int x, int y, Level lvl){
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
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		user.waterNeed = Math.max(user.waterNeed - 0.5f, 0);
		
		user.inventory.remove(this);
		user.inventory.add(new Canteen(0,0,user.curLevel));
		
		super.useOnSelf(user);
	}
}
