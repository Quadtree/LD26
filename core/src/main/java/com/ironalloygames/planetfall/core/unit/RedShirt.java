package com.ironalloygames.planetfall.core.unit;

import playn.core.Color;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.info.Person;
import com.ironalloygames.planetfall.core.level.Level;

public class RedShirt extends Unit {
	public Person p = new Person();
	
	public RedShirt(int x, int y, Level lvl){
		curLevel = lvl;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String getName() {
		return p.firstName + " " + p.lastName;
	}
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'C', fixCol(Color.rgb(255, 128, 128)));
		super.render();
	}
}
