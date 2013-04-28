package com.ironalloygames.planetfall.core;

import com.ironalloygames.planetfall.core.Level.GroundType;

public class PodLevel extends Level {
	public PodLevel(){
		map = new GroundType[16][16];
		
		for(int x=0;x<16;++x){
			for(int y=0;y<16;++y){
				double dist = Math.sqrt(Math.pow(x - 8, 2) + Math.pow(y - 8, 2));
				
				if(dist > 2.2){
					if(x != 8 && y != 8){
						map[x][y] = GroundType.SHIP_WALL;
					} else {
						map[x][y] = GroundType.SHIP_DOOR;
					}
					
				} else {
					map[x][y] = GroundType.SHIP_FLOOR;
				}
			}
		}
	}
}
