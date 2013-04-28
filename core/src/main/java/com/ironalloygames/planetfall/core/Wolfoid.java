package com.ironalloygames.planetfall.core;

import playn.core.Color;

import com.ironalloygames.planetfall.core.item.RawMeat;

public class Wolfoid extends Unit {
	
	int dx,dy;
	
	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'W', fixCol(Color.rgb(140, 140, 140)));
		super.render();
	}

	@Override
	public void destroyed() {
		curLevel.actors.add(new RawMeat(x,y,curLevel));
		super.destroyed();
	}

	@Override
	public String getDesc() {
		return "Named for their superficial similarity to the wolves of old Earth, the wolfoid likes to eat... everything.";
	}
	
	public Wolfoid(int x, int y, Level lvl){
		this.x = x;
		this.y = y;
		this.curLevel = lvl;
		
		dx = x;
		dy = y;
	}

	@Override
	public void update() {
		if(curLevel.hasLOS(x, y, PFG.s.pc.x, PFG.s.pc.y, true)){
			dx = PFG.s.pc.x;
			dy = PFG.s.pc.y;
			
			if(Math.abs(x - PFG.s.pc.x) + Math.abs(y - PFG.s.pc.y) <= 1){
				dx = x;
				dy = y;
				
				if(actionTimer <= 0){
					PFG.s.pc.hp -= 0.07f;
					actionTimer = 12;
					
					if(PFG.s.pc.actionTimer > 15) PFG.s.pc.actionTimer = 15;
				}
			}
		}
		
		int ox = x, oy = y;
		
		if(actionTimer <= 0){
			if(dx != x || dy != y){
				if(dx < x) move(-1, 0);
				if(dx > x) move(1, 0);
				if(dy < y) move(0, -1);
				if(dy > y) move(0, 1);
			}
		}
		
		if(ox == x && oy == y){
			dx = PFG.s.r.nextInt(curLevel.map.length);
			dy = PFG.s.r.nextInt(curLevel.map[0].length);
		}
		
		super.update();
	}

	@Override
	protected int moveSpeed() {
		return 5;
	}

	@Override
	public String getName() {
		return "Wolfoid";
	}
}
