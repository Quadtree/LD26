package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.Unit;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;

public class StarshipFuelCan extends Actor {
	public StarshipFuelCan(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '$', fixCol(Color.rgb(0, 128, 255)));
		super.render();
	}
	
	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Unit user) {
		int tx = user.x + (int)Math.round(Math.cos(dir));
		int ty = user.y + (int)Math.round(Math.sin(dir));
		
		user.curLevel.actors.add(new StarshipFuel(tx, ty, user.curLevel));
		user.inventory.remove(this);
		
		user.actionTimer = 10;
	}

	@Override
	public String getDesc() {
		return "Container of highly volatile starship fuel.";
	}

	@Override
	public String getItemDesc() {
		return "Container of highly volatile starship fuel. Use to pour it all out on the ground.";
	}

	@Override
	public void destroyed() {
		StarshipFuel sf = new StarshipFuel(x, y, curLevel);
		sf.temperature = temperature;
		curLevel.actors.add(sf);
		super.destroyed();
	}
	
	@Override
	public String getName() {
		return "Starship Fuel Can";
	}
}
