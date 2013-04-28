package com.ironalloygames.planetfall.core;

import playn.core.Color;

import com.ironalloygames.planetfall.core.item.RawMeat;

public class Wolfoid extends Unit {
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'W', fixCol(Color.rgb(140, 140, 140)));
		super.render();
	}

	@Override
	public void destroyed() {
		curLevel.actors.add(new RawMeat(x,y,curLevel));
		super.destroyed();
	}

	@Override
	public String getDesc() {
		return "Named for their superficial similarity to the wolves of old Earth, the wolfoid likes to eat... everything.";
	}
	
	public Wolfoid(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
	}
}
