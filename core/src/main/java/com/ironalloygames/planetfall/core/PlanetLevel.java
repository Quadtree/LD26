package com.ironalloygames.planetfall.core;

import java.lang.reflect.InvocationTargetException;

import playn.core.PlayN;

public class PlanetLevel extends Level {
	private static final int GROVE_SIZE = 9;
	private static final int PADDING = 20;
	public static final int MAP_WIDTH = 384;
	public static final int MAP_HEIGHT = 384*2;
	
	private static final byte[][] MAP_BIGTILES = {
		{0,0,0,0,0,0,0},
		{0,0,1,0,0,1,0},
		{0,0,1,0,1,1,0},
		{0,0,1,1,1,0,0},
		{0,1,1,1,1,1,0},
		{0,1,0,0,1,1,0},
		{0,0,0,0,0,1,0},
		{0,1,0,0,0,1,0},
		{0,1,1,0,1,1,0},
		{0,0,1,1,1,0,0},
		{0,0,1,1,0,0,0},
		{0,1,1,0,0,0,0},
		{0,0,0,0,0,0,0}
	};

	public PlanetLevel(){
		map = new GroundType[MAP_WIDTH][MAP_HEIGHT];
		
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;++y){
				map[x][y] = GroundType.WALL;
			}
		}
		
		int bigTileSize = MAP_WIDTH / MAP_BIGTILES[0].length;
		
		for(int y=1;y<MAP_BIGTILES.length - 1;++y){
			for(int x=1;x<MAP_BIGTILES[0].length - 1;++x){
				if(MAP_BIGTILES[y][x] == 1){
					createGrass(bigTileSize * x, bigTileSize * y);
					
					if(MAP_BIGTILES[y - 1][x] == 1){
						for(int i=0;i<bigTileSize;++i){
							createGrass(bigTileSize * x, bigTileSize * y - i);
						}
					}
					
					if(MAP_BIGTILES[y + 1][x] == 1){
						for(int i=0;i<bigTileSize;++i){
							createGrass(bigTileSize * x, bigTileSize * y + i);
						}
					}
					
					if(MAP_BIGTILES[y][x - 1] == 1){
						for(int i=0;i<bigTileSize;++i){
							createGrass(bigTileSize * x - i, bigTileSize * y);
						}
					}
					
					if(MAP_BIGTILES[y][x + 1] == 1){
						for(int i=0;i<bigTileSize;++i){
							createGrass(bigTileSize * x + i, bigTileSize * y);
						}
					}
				}
			}
		}
		
		PlayN.log().debug("Starting world generation...");
		
		for(int i=0;i<(MAP_WIDTH*MAP_HEIGHT/4);++i){
			while(true){
				int x = PFG.s.r.nextInt(MAP_WIDTH - PADDING*2) + PADDING;
				int y = PFG.s.r.nextInt(MAP_HEIGHT - PADDING*2) + PADDING;
				
				if(map[x][y] == GroundType.WALL && (isGrass(x + 1, y) || isGrass(x - 1, y) || isGrass(x, y + 1) || isGrass(x, y - 1))){
					createGrass(x,y);
					break;
				}
			}
		}
		
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;++y){
				if(map[x][y] == GroundType.WALL){
					if(isPassable(x - 1, y) && isPassable(x + 1, y) && isPassable(x, y - 1) && isPassable(x, y + 1)){
						map[x][y] = GroundType.ROCK;
					}
				}
			}
		}
		
		for(int i=0;i<90;++i){
			int groveX = PFG.s.r.nextInt(MAP_WIDTH);
			int groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			
			while(!isPassable(groveX, groveY)){
				groveX = PFG.s.r.nextInt(MAP_WIDTH);
				groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			}
			
			PlayN.log().debug("Grove placed at " + groveX + " " + groveY);
			
			Class<? extends Actor> treeType;
			
			if(PFG.s.r.nextBoolean()){
				treeType = Tree.class;
			} else {
				if(PFG.s.r.nextBoolean()){
					treeType = GreenBerryBush.class;
				} else {
					treeType = YellowBerryBush.class;
				}
			}
			
			int groveSize = PFG.s.r.nextInt(20);
			
			for(int j=0;j<groveSize;++j){
				int treeX = (int)(groveX + PFG.s.r.nextGaussian() * GROVE_SIZE);
				int treeY = (int)(groveY + PFG.s.r.nextGaussian() * GROVE_SIZE);
				
				while(!isPassable(groveX, groveY)){
					treeX = (int)(groveX + PFG.s.r.nextGaussian() * GROVE_SIZE);
					treeY = (int)(groveY + PFG.s.r.nextGaussian() * GROVE_SIZE);
				}
				
				try {
					actors.add(treeType.getConstructor(int.class, int.class, Level.class).newInstance(treeX, treeY, this));
				} catch (Exception e){
					PlayN.log().error("Tree creation error", e);
				}
			}
		}
		
		PlayN.log().debug(actors.toString());
		
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
