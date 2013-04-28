package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

public class RawMeat extends Actor {
	public RawMeat(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'm', fixCol(Color.rgb(255, 0, 0)));
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
		user.inventory.add(new Meat(0,0,user.curLevel));
		super.craft(user);
	}
	
	@Override
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		user.foodNeed = Math.max(user.foodNeed - 0.5f, 0);
		user.inventory.remove(this);
		user.sickness += 0.01f;
		super.useOnSelf(user);
	}

	@Override
	public String getDesc() {
		return "Raw meat. Eating this will work in a pinch, but it will probably make you sick. Bring it near a fire and press C to cook it.";
	}
	
	@Override
	public String getName() {
		return "Raw Meat";
	}
}
