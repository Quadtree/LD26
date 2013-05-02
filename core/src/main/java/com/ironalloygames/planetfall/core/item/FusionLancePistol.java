package com.ironalloygames.planetfall.core.item;

import playn.core.Color;
import playn.core.PlayN;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.Level;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;
import com.ironalloygames.planetfall.core.interactable.PodDoor;
import com.ironalloygames.planetfall.core.unit.Unit;

public class FusionLancePistol extends Actor {
	public FusionLancePistol(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'L', Color.rgb(0, 190, 255));
		super.render();
	}

	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Unit user) {
		
		PowerCell cell = null;
		
		for(Actor a : user.inventory){
			if(a instanceof PowerCell) cell = (PowerCell) a;
		}
	
		if(cell != null)
			user.inventory.remove(cell);
		else
			return;
		
		int cx = user.x;
		int cy = user.y;
		
		int count=0;
		
		char c = '-';
		
		if(Math.abs(Math.sin(dir)) > 0.2) c = '|';
		
		user.actionTimer = 15;
		
		while(true){
			cx += Math.round(Math.cos(dir));
			cy += Math.round(Math.sin(dir));
			
			PlayN.log().debug(cx + " " + cy);
			
			PFG.s.vfx.add(new VisualEffect(count, 4, cx, cy, Color.rgb(0, 190, 255), c));
			
			for(Actor a : user.curLevel.actors){
				if(a.x == cx && a.y == cy){
					a.hp -= 10;
					
					if(a instanceof PodDoor) return;
				}
			}
			
			if(!user.curLevel.isLOSable(cx, cy)) break;
			
			count++;
		}
		
		super.useInDirection(dir, user);
	}
	
	

	@Override
	public String getDesc() {
		return "Handheld fusion lance pistol.";
	}

	@Override
	public String getItemDesc() {
		return "Miniature handheld version of the massive fusion lances used by starships. Still deals enough damage to vaporize most things. Requires one energy cell for each shot.";
	}
	
	@Override
	public String getName() {
		return "Fusion Lance Pistol";
	}
}
