package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.unit.Unit;

public abstract class Berry extends Actor {
	public abstract int getColor();
	
	public Berry(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '+', fixCol(getColor()));
		super.render();
	}

	@Override
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		user.foodNeed = Math.max(user.foodNeed - 0.2f, 0);
		user.inventory.remove(this);
		super.useOnSelf(user);
	}
	
	
}
