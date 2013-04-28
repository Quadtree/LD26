package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;

public class AntiSicknessPill extends Actor {
	public AntiSicknessPill(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'o', fixCol(Color.rgb(255, 255, 255)));
		super.render();
	}

	@Override
	public String getItemDesc() {
		return "These seemingly miraculous pills can cure any minor illness. Use them if you get sick.";
	}

	@Override
	public String getDesc() {
		return "Anti-Sickness Pill";
	}

	@Override
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		user.sickness = 0;
	}
	
	@Override
	public String getName() {
		return "Anti-Sickness Pill";
	}
}
