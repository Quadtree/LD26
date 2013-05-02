package com.ironalloygames.planetfall.core.unit;

import playn.core.Color;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.info.Person;
import com.ironalloygames.planetfall.core.level.Level;

public class CommOfficer extends Unit {
	public Person pd = new Person();
	
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
	
	public CommOfficer(int x, int y, Level lvl) {
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		PFG.s.commOfficer = this;
	}
	
}
