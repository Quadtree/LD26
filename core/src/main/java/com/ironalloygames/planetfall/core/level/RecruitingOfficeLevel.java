package com.ironalloygames.planetfall.core.level;

import com.ironalloygames.planetfall.core.level.Level.GroundType;

public class RecruitingOfficeLevel extends Level {
	public RecruitingOfficeLevel(){
		map = new GroundType[16][16];
		
		for(int x=0;x<16;++x){
			for(int y=0;y<16;++y){
				if(x <= 2 || x >= 14 || y <= 2 || y >= 14)
					map[x][y] = GroundType.SHIP_WALL;
				else
					map[x][y] = GroundType.SHIP_FLOOR;
			}
		}
		
		map[9][4] = GroundType.DESK;
		map[7][4] = GroundType.DESK;
		map[8][4] = GroundType.DESK;
	}
}
