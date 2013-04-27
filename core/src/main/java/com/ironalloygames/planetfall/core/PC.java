package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.info.Person;

import playn.core.Color;

public class PC extends Unit {
	
	public Person pd = new Person();

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '@', Color.rgb(255, 255, 255));
		
		super.update();
	}

	@Override
	public String getDesc() {
		return "Intrepid space explorer";
	}

}
