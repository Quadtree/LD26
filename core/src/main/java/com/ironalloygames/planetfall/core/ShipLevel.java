package com.ironalloygames.planetfall.core;

import playn.core.Color;

import com.ironalloygames.planetfall.core.Level.GroundType;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;
import com.ironalloygames.planetfall.core.item.FireExtinguisher;
import com.ironalloygames.planetfall.core.item.FusionLancePistol;
import com.ironalloygames.planetfall.core.item.FusionTorch;
import com.ironalloygames.planetfall.core.item.StarshipFuel;

public class ShipLevel extends Level {
	public ShipLevel(){
		map = new GroundType[48][48];
		
		for(int x=0;x<48;++x){
			for(int y=0;y<48;++y){
				double dist = Math.sqrt(Math.pow(x - 8, 2) + Math.pow(y - 24, 2));
				
				map[x][y] = GroundType.SHIP_WALL;
				
				if(x < 13){
					if(dist > 3){
						if(x != 8 && y != 24){
							map[x][y] = GroundType.SHIP_WALL;
						} else {
							map[x][y] = GroundType.SHIP_DOOR;
						}
						
					} else {
						map[x][y] = GroundType.SHIP_FLOOR;
					}
				} else if (x < 23){
					if(y > 10 && y < 40){
						if(y != 20 && y != 30){
							if(x < 21 || y % 2 == 0)
								map[x][y] = GroundType.SHIP_FLOOR;
							else
								map[x][y] = GroundType.SHIP_WALL;
						} else {
							if(x != 17){
								map[x][y] = GroundType.SHIP_WALL;
							} else {
								map[x][y] = GroundType.SHIP_DOOR;
							}
						}
					}
				} else {
					map[x][y] = GroundType.SHIP_WALL;
				}
			}
		}
		
		actors.add(new FireExtinguisher(13, 25, this));
		actors.add(new FusionTorch(14, 15, this));
		actors.add(new FusionLancePistol(14, 35, this));
	}
	
	public void cataclysm(){
		
		for(int x=21;x<23;++x){
			for(int y=10;y<=40;++y){
				map[x][y] = GroundType.SHIP_FLOOR;
				PFG.s.vfx.add(new VisualEffect(0, 25, x, y, Color.rgb(128, 220, 255), '#'));
				PFG.s.vfx.add(new VisualEffect(25, 25, x, y, Color.rgb(64, 110, 125), '#'));
			}
		}	
		
		for(int i=0;i<18;++i){
			int x = PFG.s.r.nextInt(4) + 19;
			int y = PFG.s.r.nextInt(10) + 20;
			
			StarshipFuel sf = new StarshipFuel(x,y, this);
			sf.temperature = 300 + PFG.s.r.nextInt(350);
			
			actors.add(sf);
		}
	}
}
