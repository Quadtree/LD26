package com.ironalloygames.planetfall.core;

public class PlanetLevel extends Level {
	public PlanetLevel(){
		map = new GroundType[128][128];
		
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;++y){
				if(PFG.s.r.nextInt(8) == 0){
					if(PFG.s.r.nextBoolean())
						map[x][y] = GroundType.GRASS5;
					else
						map[x][y] = GroundType.GRASS6;
				} else {
					map[x][y] = GroundType.values()[PFG.s.r.nextInt(4)];
				}
			}
		}
	}
}
