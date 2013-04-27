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
	
	public void update(){
		
		String chars = ".,'`:;%#";
		
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;++y){
				PFG.s.setCharAtReal(x, y, chars.charAt(map[x][y].ordinal()), Color.rgb(0, 255, 0));
			}
		}
		
		for(Actor a : actors){
			a.update();
		}
	}
}
