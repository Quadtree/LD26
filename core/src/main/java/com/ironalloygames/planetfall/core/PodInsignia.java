package com.ironalloygames.planetfall.core;

import playn.core.Color;

import com.ironalloygames.planetfall.core.info.Empire;
import com.ironalloygames.planetfall.core.info.Ship;

public class PodInsignia extends Actor {
	public Ship originShip;
	public Empire originEmpire;
	
	@Override
	public String getDesc() {
		return "Insignia of the " + originShip.className + " " + originShip.name + " of the " + originEmpire.name;
	}
	public PodInsignia(int x, int y, Level curLevel, Ship originShip, Empire originEmpire) {
		super();
		this.x = x;
		this.y = y;
		this.curLevel = curLevel;
		this.originShip = originShip;
		this.originEmpire = originEmpire;
	}
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '^', Color.rgb(128, 128, 192));
		super.render();
	}
}