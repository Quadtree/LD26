package com.ironalloygames.planetfall.core;

import playn.core.Color;

import com.ironalloygames.planetfall.core.info.Person;

public class CommOfficer extends Unit {
	public Person pd;
	
	@Override
	public String getDesc() {
		return "Comm Officer " + pd.firstName + " " + pd.lastName + (PFG.s.commOfficerStillInjured ? ", " + pd.getHeShe() + " appears to be near death" : "");
	}

	@Override
	public String getName() {
		return "Comm Officer " + pd.firstName + " " + pd.lastName;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'C', Color.rgb(0, 0, 255));
		super.render();
	}
	
}
