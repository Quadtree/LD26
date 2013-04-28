package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;

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
}
