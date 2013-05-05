package com.ironalloygames.planetfall.core.level;

import playn.core.Color;

import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.PFG.VisualEffect;
import com.ironalloygames.planetfall.core.item.FireExtinguisher;
import com.ironalloygames.planetfall.core.item.FusionLancePistol;
import com.ironalloygames.planetfall.core.item.FusionTorch;
import com.ironalloygames.planetfall.core.item.StarshipFuel;
import com.ironalloygames.planetfall.core.level.Level.GroundType;

public class TorpedoRoom extends Level {
	
	float[][] pressure = new float[96][48];
	
	public TorpedoRoom(){
		map = new GroundType[96][48];
		
		for(int x=0;x<96;++x){
			for(int y=0;y<48;++y){
				if(y >= 24){
					if(y == 24 || x < 30 || x > 45 || y > 36){
						map[x][y] = GroundType.SHIP_WALL;
					} else {
						map[x][y] = GroundType.SHIP_FLOOR;
						pressure[x][y] = 1;
					}
				} else {
					map[x][y] = GroundType.VOID;
				}
			}
		}
		
		for(int i=0;i<10;++i){
			int sx = PFG.s.r.nextInt(14) + 15+15;
			int sy = PFG.s.r.nextInt(11) + 25;
			
			map[sx][sy] = GroundType.MACHINERY;
			map[sx+1][sy] = GroundType.MACHINERY;
		}
	}
	
	public void cataclysm(){
		
		for(int i=0;i<3;++i){
			map[19+15+i][24] = GroundType.VOID;
		}
	}

	@Override
	public String getDesc(int x, int y) {
		if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) return "???";
		
		return super.getDesc(x, y) + " Pressure: " + (int)(pressure[x][y] * 100) + "%";
	}
}
