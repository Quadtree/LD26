package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.unit.Unit;

public class Meat extends Actor {
	public Meat(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'm', fixCol(Color.rgb(160, 0, 0)));
		super.render();
	}
	
	@Override
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		user.foodNeed = Math.max(user.foodNeed - 0.5f, 0);
		user.inventory.remove(this);
		super.useOnSelf(user);
	}

	@Override
	public String getDesc() {
		return "Delicious cooked meat. Press U to eat.";
	}
	
	@Override
	public String getName() {
		return "Meat";
	}
}
