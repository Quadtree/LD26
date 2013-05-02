package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;
import com.ironalloygames.planetfall.core.level.Level;
import com.ironalloygames.planetfall.core.unit.Unit;

public class PowerCell extends Actor {
	public PowerCell(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'p', fixCol(Color.rgb(0, 0, 255)));
		super.render();
	}

	@Override
	public String getDesc() {
		return "Energy cell used to power the fusion lance pistol.";
	}
	
	@Override
	public String getItemDesc() {
		return "Energy cell used to power the fusion lance pistol. Can also be thrown on the ground to start a fire";
	}
	
	@Override
	public String getName() {
		return "Power Cell";
	}
	
	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Unit user) {
		int tx = user.x + (int)Math.round(Math.cos(dir));
		int ty = user.y + (int)Math.round(Math.sin(dir));
		
		PFG.s.vfx.add(new VisualEffect(0, 12, tx, ty, Color.rgb(0, 192, 255), '%'));
		
		for(Actor a : PFG.s.currentLevel.actors)
		{
			if(a.x == tx && a.y == ty){
				a.temperature += 200 * a.getHeatGainMultiplier();
			}
		}
		
		user.actionTimer = 10;
		
		user.inventory.remove(this);
	}
}
