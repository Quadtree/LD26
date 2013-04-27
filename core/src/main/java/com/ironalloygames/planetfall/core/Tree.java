package com.ironalloygames.planetfall.core;

import playn.core.Color;
import playn.core.PlayN;

public class Tree extends Actor {
	
	public Tree(int x, int y, Level curLevel){
		this.x = x;
		this.y = y;
		
		for(int i=0;i<1;++i){
			curLevel.actors.add(new Wood((int)(x + PFG.s.r.nextGaussian() * 2), (int)(y + PFG.s.r.nextGaussian() * 2)));
		}
	}

	@Override
	public void update() {
		PFG.s.setCharAtReal(x, y, 'Y', Color.rgb(0, 128, 0));
		PFG.s.setCharAtReal(x, y - 1, '@', Color.rgb(0, 128, 0));
		
		PlayN.log().info(x + " " + y);
		
		super.update();
	}

}
