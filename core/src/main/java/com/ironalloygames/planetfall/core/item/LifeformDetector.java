package com.ironalloygames.planetfall.core.item;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.level.Level;
import com.ironalloygames.planetfall.core.unit.CommOfficer;
import com.ironalloygames.planetfall.core.unit.EnemyDoctor;
import com.ironalloygames.planetfall.core.unit.PC;
import com.ironalloygames.planetfall.core.unit.Unit;

public class LifeformDetector extends Actor {
	public LifeformDetector(int x, int y, Level curLevel){
		this.x = x;
		this.y = y;
		this.curLevel = curLevel;
	}

	@Override
	public String getName() {
		return "Lifeform Detector";
	}

	@Override
	public String getDesc() {
		return "Handheld scanner, used for detecing lifeforms.";
	}

	@Override
	public String getItemDesc() {
		return "Handheld scanner, used for detecing lifeforms. Currently set to detect only humans, as this planet is abundant with life.";
	}

	@Override
	public boolean isUsableOnSelf() {
		return true;
	}

	@Override
	public void useOnSelf(Unit user) {
		PFG.s.pdaScannerUp = true;
		
		int left = 1;
		int top = 10;
		
		int size = "+--------------+".length() - 2;
		
		PFG.s.setTextAt(left, top, "+--------------+", Color.rgb(255, 255, 255));
		
		for(int y=0;y<size;++y){
			PFG.s.setTextAt(left, y+top+1, "|", Color.rgb(255, 255, 255));
			PFG.s.setTextAt(left+size+1, y+top+1, "|", Color.rgb(255, 255, 255));
		}
		
		for(int x=0;x<size;++x){
			for(int y=0;y<size;++y){
				//PFG.s.setTextAt(x+left+1, y+top+1, "?", Color.rgb(255, 192, 192));
				
				PFG.s.setTextAt(x+left+1, y+top+1, " ", Color.rgb(0, 0, 0));
				
				for(Actor a : user.curLevel.actors){
					if(a.x * size / user.curLevel.map.length == x && a.y * size / user.curLevel.map[0].length == y){
						if(a instanceof EnemyDoctor || a instanceof CommOfficer){
							PFG.s.setTextAt(x+left+1, y+top+1, "*", Color.rgb(255, 255, 255));
						}
						if(a instanceof PC){
							PFG.s.setTextAt(x+left+1, y+top+1, "*", Color.rgb(150, 150, 255));
						}
					}
				}
			}
		}
		
		PFG.s.setTextAt(left, top + size, "+--------------+", Color.rgb(255, 255, 255));
		
		super.useOnSelf(user);
	}

	@Override
	public void render() {
		PFG.s.setCharAtReal(x, y, 'S', fixCol(Color.rgb(96, 255, 0)));
		super.render();
	}
}
