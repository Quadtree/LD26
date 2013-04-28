package com.ironalloygames.planetfall.core;

import java.lang.reflect.InvocationTargetException;

import com.ironalloygames.planetfall.core.Level.GroundType;
import com.ironalloygames.planetfall.core.item.AntiSicknessPill;
import com.ironalloygames.planetfall.core.item.Flint;
import com.ironalloygames.planetfall.core.item.FusionLancePistol;
import com.ironalloygames.planetfall.core.item.FusionTorch;
import com.ironalloygames.planetfall.core.item.PowerCell;
import com.ironalloygames.planetfall.core.item.StarshipFuelCan;

import playn.core.PlayN;

public class PlanetLevel extends Level {
	private static final int GROVE_SIZE = 9;
	private static final int PADDING = 10;
	public static final int MAP_WIDTH = 160;
	public static final int MAP_HEIGHT = 160*2;
	
	public int pcLifepodX, pcLifepodY;
	
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
		
		for(int i=0;i<30;++i){
			int groveX = PFG.s.r.nextInt(MAP_WIDTH);
			int groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			
			while(!isPassable(groveX, groveY)){
				groveX = PFG.s.r.nextInt(MAP_WIDTH);
				groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			}
			
			map[groveX][groveY] = GroundType.WATER;
		}
		
		for(int i=0;i<30*15;++i){
			while(true){
				int x = PFG.s.r.nextInt(MAP_WIDTH - PADDING*2) + PADDING;
				int y = PFG.s.r.nextInt(MAP_HEIGHT - PADDING*2) + PADDING;
				
				if(map[x + 1][y] == GroundType.WATER || map[x - 1][y] == GroundType.WATER || map[x][y + 1] == GroundType.WATER || map[x][y - 1] == GroundType.WATER){
					map[x][y] = GroundType.WATER;
					break;
				}
			}
		}
		
		boolean onePlaced;
		
		do {
			onePlaced = false;
			
			for(int x=1;x<map.length - 1;x++){
				for(int y=1;y<map[0].length - 1;++y){
					if(map[x][y] == GroundType.WATER) continue;
					
					int sides = 0;
					if(map[x + 1][y] == GroundType.WATER) sides++;
					if(map[x - 1][y] == GroundType.WATER) sides++;
					if(map[x][y - 1] == GroundType.WATER) sides++;
					if(map[x][y + 1] == GroundType.WATER) sides++;
					
					if(sides >= 3){
						map[x][y] = GroundType.WATER;
						onePlaced = true;
					}
				}
			}
		} while(onePlaced);
		
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;++y){
				if(map[x][y] == GroundType.WALL){
					if(isPassable(x - 1, y) && isPassable(x + 1, y) && isPassable(x, y - 1) && isPassable(x, y + 1)){
						map[x][y] = GroundType.ROCK;
						
						if(PFG.s.r.nextInt(6) == 0){
							actors.add(new Flint(x - 1, y - 1, this));
						}
					}
				}
			}
		}
		
		for(int i=0;i<25;++i){
			int groveX = PFG.s.r.nextInt(MAP_WIDTH);
			int groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			
			while(!isPassable(groveX, groveY)){
				groveX = PFG.s.r.nextInt(MAP_WIDTH);
				groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			}
			
			PlayN.log().debug("Grove placed at " + groveX + " " + groveY);
			
			byte typeToSpawn = -1;
			
			if(PFG.s.r.nextBoolean()){
				typeToSpawn = 1;
			} else {
				if(PFG.s.r.nextBoolean()){
					typeToSpawn = 2;
				} else {
					typeToSpawn = 3;
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
				
				switch(typeToSpawn){
					case 1: actors.add(new Tree(treeX, treeY, this)); break;
					case 2: actors.add(new GreenBerryBush(treeX, treeY, this)); break;
					case 3: actors.add(new YellowBerryBush(treeX, treeY, this)); break;
				}
				
			}
		}
		
		for(int i=0;i<50;++i){
			int groveX = PFG.s.r.nextInt(MAP_WIDTH);
			int groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			
			while(!isPassable(groveX, groveY)){
				groveX = PFG.s.r.nextInt(MAP_WIDTH);
				groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			}
			
			actors.add(new HexapedDeer(groveX, groveY, this));
			
		}
		
		for(int i=0;i<10;++i){
			int groveX = PFG.s.r.nextInt(MAP_WIDTH);
			int groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			
			while(!isPassable(groveX, groveY)){
				groveX = PFG.s.r.nextInt(MAP_WIDTH);
				groveY = PFG.s.r.nextInt(MAP_HEIGHT);
			}
			
			actors.add(new Wolfoid(groveX, groveY, this));
		}
		
		PlayN.log().debug(actors.toString());
		
		PlayN.log().debug("Placing lifepods");
		
		placeLifepod(0,0,0,100000,false);
		
		pcLifepodX = lastLifepodX;
		pcLifepodY = lastLifepodY;
		
		actors.add(new PodInsignia(lastLifepodX, lastLifepodY - 3, this, PFG.s.alliedShip, PFG.s.alliedEmpire));
		
		placeLifepod(pcLifepodX,pcLifepodY,0,5000,true);
		
		actors.add(new PodInsignia(lastLifepodX, lastLifepodY - 3, this, PFG.s.alliedShip, PFG.s.alliedEmpire));
		actors.add(new PodDoor(lastLifepodX, lastLifepodY + 3, this));
		actors.add(new CommOfficer(lastLifepodX, lastLifepodY, this));
		
		placeLifepod(pcLifepodX,pcLifepodY,0,5000,true);
		
		actors.add(new PodInsignia(lastLifepodX, lastLifepodY - 3, this, PFG.s.enemyShip, PFG.s.enemyEmpire));
		actors.add(new PodDoor(lastLifepodX, lastLifepodY + 3, this));
		actors.add(new EnemyDoctor(lastLifepodX, lastLifepodY, this));
		
		for(int i=0;i<6;++i){
			placeLifepod(pcLifepodX,pcLifepodY,0,5000,true);
			
			if(PFG.s.r.nextBoolean()){
				actors.add(new PodInsignia(lastLifepodX, lastLifepodY - 3, this, PFG.s.alliedShip, PFG.s.alliedEmpire));
			} else {
				actors.add(new PodInsignia(lastLifepodX, lastLifepodY - 3, this, PFG.s.enemyShip, PFG.s.enemyEmpire));
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
	
	public int lastLifepodX, lastLifepodY;
	
	public void placeLifepod(int x, int y, int minRange, int maxRange, boolean placeExtraGear){
		while(true){
			lastLifepodX = PFG.s.r.nextInt(MAP_WIDTH - 40) + 20;
			lastLifepodY = PFG.s.r.nextInt(MAP_HEIGHT - 40) + 20;
			
			double dist1 = Math.sqrt(Math.pow(x - lastLifepodX, 2) + Math.pow(y - lastLifepodY, 2));
			
			if(dist1 < minRange || dist1 > maxRange) continue;
			
			boolean passable = true;
			
			for(int tx=lastLifepodX - 5;tx <= lastLifepodX + 5;++tx){
				for(int ty=lastLifepodY - 5;ty <= lastLifepodY + 5;++ty){
					passable = passable && isPassable(tx,ty);
				}
			}
			
			if(passable){
				for(int tx=lastLifepodX - 6;tx <= lastLifepodX + 6;++tx){
					for(int ty=lastLifepodY - 6;ty <= lastLifepodY + 6;++ty){
						double dist = Math.sqrt(Math.pow(tx - lastLifepodX, 2) + Math.pow(ty - lastLifepodY, 2));
						
						//PlayN.log().info("" + dist);
						
						if(dist > 2.2 && dist < 3.2){
							if(tx == lastLifepodX && ty > lastLifepodY){
								map[tx][ty] = GroundType.SHIP_DOOR;
							} else {
								map[tx][ty] = GroundType.SHIP_WALL;
							}
							destroyAllAt(tx,ty);
						} else if(dist < 3){
							map[tx][ty] = GroundType.SHIP_FLOOR;
							destroyAllAt(tx,ty);
						}
					}
				}
				
				PlayN.log().debug("DIST1 " + dist1);
				
				if(placeExtraGear){
					switch(PFG.s.r.nextInt(5)){
						case 0: actors.add(new FusionTorch(lastLifepodX+1, lastLifepodY, this)); break;
						case 1: actors.add(new FusionLancePistol(lastLifepodX+1, lastLifepodY, this)); break;
						case 2: actors.add(new PowerCell(lastLifepodX+1, lastLifepodY, this)); actors.add(new PowerCell(lastLifepodX+1, lastLifepodY, this)); break;
						case 3: actors.add(new StarshipFuelCan(lastLifepodX+1, lastLifepodY, this)); break;
						case 4: actors.add(new AntiSicknessPill(lastLifepodX+1, lastLifepodY, this)); break;
					}
				}
				
				break;
			}
		}
		
		PlayN.log().debug("Lifepod placed at " + lastLifepodX + " " + lastLifepodY);
	}
	
	public void destroyAllAt(int x, int y){
		for(int i=0;i<actors.size();++i){
			if(actors.get(i).x == x && actors.get(i).y == y){
				actors.remove(i--);
			}
		}
	}
}
