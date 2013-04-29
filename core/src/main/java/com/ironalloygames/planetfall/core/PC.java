package com.ironalloygames.planetfall.core;

import java.util.Collections;
import java.util.Comparator;

import com.ironalloygames.planetfall.core.dialog.InfoDialog;
import com.ironalloygames.planetfall.core.info.Person;

import playn.core.Color;
import playn.core.PlayN;

public class PC extends Unit {
	
	public Person pd = new Person();

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '@', fixCol(Color.rgb(255, 255, 255)));
		
		sortItems();
		
		if(inventory.size() > 1 && PlayN.storage().getItem("itemTutorial") == null && PFG.s.curDialog == null){
			PFG.s.curDialog = new InfoDialog("You now have more than one item. Use the up and down arrow keys to switch between them.");
			PlayN.storage().setItem("itemTutorial", "yes");
		}
		
		super.render();
	}
	
	public void sortItems(){
		Collections.sort(inventory, new Comparator<Actor>(){

			@Override
			public int compare(Actor o1, Actor o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
	}

	@Override
	public String getDesc() {
		return "Mechanic Trainee " + pd.firstName + " " + pd.lastName + "." + super.getDesc();
	}

	@Override
	public int getRenderPriority() {
		return super.getRenderPriority()+1;
	}
	
	float oldTemp = temperature;

	@Override
	public void update() {
		
		if(temperature < 278){
			hp -= (278 - temperature) / 20 / 2800;
		}
		
		foodNeed += 1.f / PFG.DAY_LENGTH;
		
		if(actionTimer == 30) hp = Math.min(hp + 0.03f, 1);
		
		if(sickness > 0) sickness += 1.f / PFG.DAY_LENGTH;
		
		if(sickness > 1) hp -= 1.f / PFG.DAY_LENGTH;
		
		if(foodNeed > 1) hp -= 1.f / PFG.DAY_LENGTH / 3;
		
		if(oldTemp > 278 && temperature < 278 && actionTimer > 25){
			PFG.s.curDialog = new InfoDialog("You are awoken by extreme cold!");
			actionTimer = 0;
		}
		
		if(oldTemp < getHeatDamagePoint() && temperature > getHeatDamagePoint() && actionTimer > 25){
			PFG.s.curDialog = new InfoDialog("You are awoken by extreme heat!");
			actionTimer = 0;
		}
		
		oldTemp = temperature;
		
		super.update();
	}
	
	@Override
	public String getName() {
		return "Mechanic Trainee " + pd.firstName + " " + pd.lastName;
	}
	
	String activeItem;
	
	public void saveActiveItem(){
		activeItem = inventory.get(PFG.s.equippedItem).getName();
	}
	
	public void restoreActiveItem(){
		for(int i=0;i<inventory.size();++i){
			if(inventory.get(i).getName().equals(activeItem)) PFG.s.equippedItem = i;
		}
	}
}
