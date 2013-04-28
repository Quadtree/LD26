package com.ironalloygames.planetfall.core;

import java.util.Collections;
import java.util.Comparator;

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

	@Override
	public void update() {
		
		if(temperature < 278){
			hp -= (278 - temperature) / 20 / 1800;
		}
		
		foodNeed += 1.f / PFG.DAY_LENGTH;
		
		if(sickness > 0) sickness += 1.f / PFG.DAY_LENGTH;
		
		Collections.sort(inventory, new Comparator<Actor>(){

			@Override
			public int compare(Actor o1, Actor o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		super.update();
	}
	
	@Override
	public String getName() {
		return "Mechanic Trainee " + pd.firstName + " " + pd.lastName;
	}
}
