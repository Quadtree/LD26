package com.ironalloygames.planetfall.core.item;

import playn.core.Color;
import playn.core.PlayN;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;
import com.ironalloygames.planetfall.core.interactable.PodDoor;
import com.ironalloygames.planetfall.core.level.Level;
import com.ironalloygames.planetfall.core.unit.Unit;

public class Javelin extends Actor {
	public Javelin(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		curLevel = lvl;
	}
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '/', Color.rgb(255, 140, 0));
		super.render();
	}

	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Unit user) {
		
		user.inventory.remove(this);
		
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
			
			PFG.s.vfx.add(new VisualEffect(count, 1, cx, cy, Color.rgb(255, 140, 0), c));
			
			for(Actor a : user.curLevel.actors){
				if(a.x == cx && a.y == cy){
					if(a instanceof PodDoor){
						a.hp -= 0.07f;
						PFG.s.vfx.add(new VisualEffect(0, 12, cx, cy, Color.rgb(192, 192, 192), '*'));
					} else {
						a.hp -= PFG.s.r.nextFloat() * 1.3f;
						PFG.s.vfx.add(new VisualEffect(0, 12, cx, cy, Color.rgb(255, 0, 0), '*'));
					}
					
					return;
				}
			}
			
			if(!user.curLevel.isLOSable(cx, cy)) break;
			
			count++;
		}
		
		super.useInDirection(dir, user);
	}

	@Override
	public String getDesc() {
		return "Long throwing spear. Not as deadly as a fusion lance, but suffices.";
	}
	
	@Override
	public String getName() {
		return "Javelin";
	}
}
