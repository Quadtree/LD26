package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.Level.GroundType;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

public class Canteen extends Actor {
	public Canteen(int x, int y, Level lvl){
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
		for(int x=user.x - 4;x <= user.x + 4;++x){
			for(int y=user.y - 4;y <= user.y + 4;++y){
				if(user.curLevel.map[x][y] == GroundType.WATER) return true;
			}
		}
		
		return false;
	}

	@Override
	public void craft(Unit user) {
		user.inventory.remove(this);
		user.inventory.add(new DirtyWaterCanteen(0,0,user.curLevel));
		super.craft(user);
	}
	
	
}
