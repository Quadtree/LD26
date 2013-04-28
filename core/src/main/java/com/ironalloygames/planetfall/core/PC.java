package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.info.Person;

import playn.core.Color;

public class PC extends Unit {
	
	public Person pd = new Person();

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '@', fixCol(Color.rgb(255, 255, 255)));
		
		super.render();
	}

	@Override
	public String getDesc() {
		return "Mechanic Trainee " + pd.firstName + " " + pd.lastName + "." + super.getDesc();
	}

	@Override
	public int getRenderPriority() {
		return super.getRenderPriority()+1;
	}
}
