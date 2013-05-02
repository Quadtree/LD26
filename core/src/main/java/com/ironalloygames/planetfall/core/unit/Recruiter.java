package com.ironalloygames.planetfall.core.unit;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.info.Person;

import playn.core.Color;

public class Recruiter extends Unit {

	public Person pd = new Person();
	
	static String[] titles = {"Commander", "Admiral", "Commodore", "Colonel", "Captain"};
	
	public String title;
	
	public Recruiter() {
		super();
		title = titles[PFG.s.r.nextInt(titles.length)];
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'R', fixCol(Color.rgb(0, 190, 0)));
		super.render();
	}

	@Override
	public String getDesc() {
		return title + " " + pd.lastName + super.getDesc();
	}
	
	@Override
	public String getName() {
		return "Recruiter";
	}
}
