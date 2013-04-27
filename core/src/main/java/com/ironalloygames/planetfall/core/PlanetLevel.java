package com.ironalloygames.planetfall.core;

import playn.core.PlayN;

public class PlanetLevel extends Level {
	private static final int PADDING = 20;
	public static final int MAP_SIZE = 384;

	public PlanetLevel(){
		map = new GroundType[MAP_SIZE][MAP_SIZE];
		
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;++y){
				map[x][y] = GroundType.WALL;
			}
		}
		
		createGrass(MAP_SIZE/2,MAP_SIZE/2);
		
		PlayN.log().debug("Starting world generation...");
		
		for(int i=0;i<(MAP_SIZE*MAP_SIZE/4);++i){
			while(true){
				int x = PFG.s.r.nextInt(map.length - PADDING*2) + PADDING;
				int y = PFG.s.r.nextInt(map.length - PADDING*2) + PADDING;
				
				if(map[x][y] == GroundType.WALL && (isGrass(x + 1, y) || isGrass(x - 1, y) || isGrass(x, y + 1) || isGrass(x, y - 1))){
					createGrass(x,y);
					break;
				}
			}
		}
		
		PlayN.log().debug("World generation complete");
	}
	
	private void createGrass(int x, int y){
		if(PFG.s.r.nextInt(8) == 0){
			if(PFG.s.r.nextBoolean())
				map[x][y] = GroundType.GRASS5;
			else
				map[x][y] = GroundType.GRASS6;
		} else {
			map[x][y] = GroundType.values()[PFG.s.r.nextInt(4)];
		}
	}
	
	private boolean isGrass(int x, int y){
		return map[x][y].ordinal() >= GroundType.GRASS1.ordinal() && map[x][y].ordinal() <= GroundType.GRASS6.ordinal();
	}
}
