package com.ironalloygames.planetfall.core.item;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

import playn.core.Color;

public class Wood extends Actor {
	public Wood(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'w', fixCol(Color.rgb(255, 128, 0)));
		
		super.render();
	}
	
	@Override
	public String getDesc() {
		return "Some wood. Good for fires!" + super.getDesc();
	}

	@Override
	public boolean isCraftable(Unit user) {
		return true;
	}

	@Override
	public void craft(Unit user) {
		user.inventory.remove(this);
		user.inventory.add(new Javelin(0,0,user.curLevel));
		super.craft(user);
	}

	@Override
	public String getItemDesc() {
		return "Some wood. Good for burning, and can also be crafted into javelins.";
	}
	
	@Override
	public String getName() {
		return "Wood";
	}
}
