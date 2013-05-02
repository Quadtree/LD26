package com.ironalloygames.planetfall.core.unit;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.item.RawMeat;

public class HexapedDeer extends Unit {

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'D', fixCol(Color.rgb(128, 255, 0)));
		super.render();
	}

	@Override
	public void destroyed() {
		curLevel.actors.add(new RawMeat(x,y,curLevel));
		curLevel.actors.add(new RawMeat(x,y,curLevel));
		super.destroyed();
	}

	@Override
	public String getDesc() {
		return "Like a regular deer, except that it has six legs! Seems docile, though.";
	}
	
	public HexapedDeer(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
	}
	
	@Override
	public String getName() {
		return "Hexaped Deer";
	}
}
