package com.ironalloygames.planetfall.core.unit;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.info.Person;

public class EnemyDoctor extends Unit {

	public Person pd = new Person();
	
	@Override
	public String getDesc() {
		return "Doctor " + pd.firstName + " " + pd.lastName;
	}

	@Override
	public String getName() {
		return "Doctor " + pd.firstName + " " + pd.lastName;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'D', Color.rgb(255, 0, 0));
		super.render();
	}
	
	public EnemyDoctor(int x, int y, Level lvl) {
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		PFG.s.enemyDoctor = this;
	}

}
