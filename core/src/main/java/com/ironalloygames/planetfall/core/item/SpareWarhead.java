package com.ironalloygames.planetfall.core.item;

import playn.core.Color;
import playn.core.PlayN;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;
import com.ironalloygames.planetfall.core.interactable.PodDoor;
import com.ironalloygames.planetfall.core.level.Level;
import com.ironalloygames.planetfall.core.unit.Unit;

public class SpareWarhead extends Actor {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public SpareWarhead(int x, int y, Level curLevel){
		this.x = x;
		this.y = y;
		this.curLevel = curLevel;
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, '*', fixCol(Color.rgb(255, 128, 0)));
		super.render();
	}
	
	@Override
	public void destroyed() {
		
		super.destroyed();
	}

	@Override
	public String getDesc() {
		return "An unstable-looking spare mini-warhead.";
	}

	@Override
	public boolean isUsableInDirection() {
		return true;
	}

	@Override
	public void useInDirection(float dir, Unit user) {
		int cx = user.x;
		int cy = user.y;
		
		int count=0;
		
		user.actionTimer = 15;
		
		while(true){
			cx += Math.round(Math.cos(dir));
			cy += Math.round(Math.sin(dir));
			
			PlayN.log().debug(cx + " " + cy);
			
			PFG.s.vfx.add(new VisualEffect(count*2, 2, cx, cy, Color.rgb(255, 128, 0), '*'));
			
			boolean doBreak = false;
			
			for(Actor a : user.curLevel.actors){
				if(a.x == cx && a.y == cy){
					doBreak = true;
				}
			}
			
			if(doBreak || !user.curLevel.isLOSable(cx, cy)) break;
			
			count++;
		}
		
		curLevel.actors.add(this);
		x = cx;
		y = cy;
		hp = -1;
	}
	
	public static void explosion(int x, int y){
		
		int speed = 3;
		
		PFG.s.vfx.add(new VisualEffect(0, speed, x, y, Color.rgb(255, 255, 255), '#'));
		PFG.s.vfx.add(new VisualEffect(0, speed, x - 1, y, Color.rgb(255, 255, 0), '-'));
		PFG.s.vfx.add(new VisualEffect(0, speed, x + 1, y, Color.rgb(255, 255, 0), '-'));
		PFG.s.vfx.add(new VisualEffect(0, speed, x, y - 1, Color.rgb(255, 255, 0), '|'));
		PFG.s.vfx.add(new VisualEffect(0, speed, x, y + 1, Color.rgb(255, 255, 0), '|'));
		
		for(int cx=x - 1;cx <= x + 1;++cx){
			for(int cy=y - 1;cy <= y + 1;++cy){
				if(x == cx && y == cy){
					PFG.s.vfx.add(new VisualEffect(speed*1, speed, cx, cy, Color.rgb(255, 255, 0), '#'));
				} else {
					PFG.s.vfx.add(new VisualEffect(speed*1, speed, cx, cy, Color.rgb(255, 0, 0), '+'));
				}
			}
		}
		
		for(int cx=x - 1;cx <= x + 1;++cx){
			for(int cy=y - 1;cy <= y + 1;++cy){
				PFG.s.vfx.add(new VisualEffect(speed*2, speed, cx, cy, Color.rgb(128, 0, 0), '%'));
			}
		}
		
	}
}
