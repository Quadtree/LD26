package com.ironalloygames.planetfall.core.interactable;

import com.ironalloygames.planetfall.core.item.Berry;
import com.ironalloygames.planetfall.core.item.YellowBerry;
import com.ironalloygames.planetfall.core.level.Level;

import playn.core.Color;

public class YellowBerryBush extends BerryBush {
	
	public YellowBerryBush(int x, int y, Level level) {
		super(x, y, level);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getColor() {
		return Color.rgb(255, 255, 0);
	}
	
	@Override
	public Berry getBerry(int x, int y) {
		return new YellowBerry(x,y,curLevel);
	}
	
	@Override
	public String getDesc() {
		return "A bush with some yellow colored berries lying around." + super.getDesc();
	}
	
	public float heatGainModifier(){ return 1; }
	
	@Override
	public String getName() {
		return "Yellow Berry Bush";
	}
}
