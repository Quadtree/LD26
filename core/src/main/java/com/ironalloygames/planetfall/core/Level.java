package com.ironalloygames.planetfall.core;

import java.util.ArrayList;

import playn.core.Color;

public class Level {
	public ArrayList<Actor> actors = new ArrayList<Actor>();
	
	enum GroundType {
		GRASS1,
		GRASS2,
		GRASS3,
		GRASS4,
		GRASS5,
		GRASS6,
		ROCK,
		WALL
	}
	
	protected GroundType[][] map;
	
	public Level(){
		
	}
	
	public void render(){
		
		String chars = ".,'`:;*#";
		
		for(int x=0;x<map.length;x++){
			if(Math.abs(PFG.s.pc.x - x) > 30) continue;
			
			for(int y=0;y<map[0].length;++y){
				if(Math.abs(PFG.s.pc.y - y) > 30) continue;
				
				
				int color = 0;
				
				if(map[x][y] == GroundType.WALL)
					color = Color.rgb(190, 90, 0);
				else if(map[x][y] == GroundType.ROCK)
					color = Color.rgb(100, 100, 100);
				else
					color = Color.rgb(0, 255, 0);
				
				
				PFG.s.setCharAtReal(x, y, chars.charAt(map[x][y].ordinal()), color);
			}
		}
		
		for(Actor a : actors){
			a.render();
		}
	}
	
	public void update(){
		for(Actor a : actors){
			a.update();
		}
	}
	
	public boolean isPassable(int x, int y){
		if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return map[x][y].ordinal() <= GroundType.GRASS6.ordinal();
	}
	
	public boolean hasLOS(int sx, int sy, int ex, int ey){
		float curX = sx, curY = sy;
		float dist = (float)Math.sqrt(Math.pow(sx - ex, 2) + Math.pow(sy - ey, 2));
		
		//if(dist > 7) return false;
		
		float mx = (ex - sx) / dist / 2;
		float my = (ey - sy) / dist / 2;
		
		while(Math.abs(curX - ex) > 2 || Math.abs(curY - ey) > 2){
			curX += mx;
			curY += my;
			
			if(!isPassable((int)curX, (int)curY)) return false;
		}
		
		return true;
	}
	
	public String getDesc(int x, int y){
		if(!hasLOS(PFG.s.pc.x, PFG.s.pc.y, x, y)) return "???";
		
		return "!";
	}
}
