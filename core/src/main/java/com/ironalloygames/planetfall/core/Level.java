package com.ironalloygames.planetfall.core;

import java.util.ArrayList;
import java.util.Collections;

import com.ironalloygames.planetfall.core.dialog.DeadEnding;

import playn.core.Color;

public class Level {
	public ArrayList<Actor> actors = new ArrayList<Actor>();
	
	public enum GroundType {
		GRASS1,
		GRASS2,
		GRASS3,
		GRASS4,
		GRASS5,
		GRASS6,
		ROCK,
		WALL,
		WATER,
		DRY_POOL,
		SHIP_WALL,
		SHIP_FLOOR,
		SHIP_DOOR,
		DESK
	}
	
	public GroundType[][] map;
	
	public Level(){
		
	}
	
	public void render(){
		
		String chars = ".,'`:;*#@@X-==";
		
		for(int x=0;x<map.length;x++){
			if(Math.abs(PFG.s.pc.x - x) > 30) continue;
			
			for(int y=0;y<map[0].length;++y){
				if(Math.abs(PFG.s.pc.y - y) > 30) continue;
				
				
				int color = 0;
				
				if(map[x][y] == GroundType.WALL || map[x][y] == GroundType.DESK)
					color = Color.rgb(190, 90, 0);
				else if(map[x][y] == GroundType.ROCK)
					color = Color.rgb(100, 100, 100);
				else if(map[x][y] == GroundType.WATER)
					color = Color.rgb(0, 160, 255);
				else if(map[x][y] == GroundType.DRY_POOL)
					color = Color.rgb(190, 90, 0);
				else if(map[x][y] == GroundType.SHIP_FLOOR)
					color = Color.rgb(160, 160, 190);
				else if(map[x][y] == GroundType.SHIP_WALL)
					color = Color.rgb(100, 100, 140);
				else if(map[x][y] == GroundType.SHIP_DOOR)
					color = Color.rgb(90, 90, 120);
				else
					color = Color.rgb(0, 255, 0);
				
				
				PFG.s.setCharAtReal(x, y, chars.charAt(map[x][y].ordinal()), color);
			}
		}
		
		Collections.sort(actors);
		
		for(Actor a : actors){
			a.render();
		}
	}
	
	public void update(){
		Collections.sort(actors);
		
		for(int i=0;i<actors.size();++i){
			if(actors.get(i).hp > 0)
				actors.get(i).update();
			else{
				if(actors.get(i) instanceof PC){
					PFG.s.curDialog = new DeadEnding();
				}
				actors.get(i).destroyed();
				actors.remove(i--);
			}
		}
	}
	
	public boolean isPassable(int x, int y){
		if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return map[x][y].ordinal() <= GroundType.GRASS6.ordinal() || map[x][y] == GroundType.SHIP_FLOOR;
	}
	
	public boolean isLOSable(int x, int y){
		if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) return false;
		return map[x][y].ordinal() <= GroundType.GRASS6.ordinal() || map[x][y] == GroundType.WATER || map[x][y] == GroundType.DRY_POOL || map[x][y] == GroundType.SHIP_FLOOR || map[x][y] == GroundType.DESK;
	}
	
	public boolean hasLOS(int sx, int sy, int ex, int ey){
		return hasLOS(sx,sy,ex,ey,false);
	}
	
	public boolean hasLOS(int sx, int sy, int ex, int ey, boolean darkvision){
		float curX = sx, curY = sy;
		float dist = (float)Math.sqrt(Math.pow(sx - ex, 2) + Math.pow(sy - ey, 2));
		
		if(!darkvision){
			if(PFG.s.getHour().equals("Night") && dist > 7) return false;
			if(PFG.s.getHour().equals("Dusk") && dist > 15) return false;
			if(PFG.s.getHour().equals("Dawn") && dist > 15) return false;
		}
		
		float mx = (ex - sx) / dist / 4;
		float my = (ey - sy) / dist / 4;
		
		while(Math.abs(curX - ex) > 1 || Math.abs(curY - ey) > 1){
			curX += mx;
			curY += my;
			
			if(!isLOSable((int)(curX + 0.5f), (int)(curY + 0.5f))) return false;
		}
		
		return true;
	}
	
	public String getDesc(int x, int y){
		if(!hasLOS(PFG.s.pc.x, PFG.s.pc.y, x, y)) return "???";
		if(x < 0 || y < 0 || x >= map.length || y >= map[0].length) return "???";
		
		Actor topActor = null;
		
		for(Actor a : actors){
			if(a.x == x && a.y == y) topActor = a;
		}
		
		if(topActor != null){
			return topActor.getLongDesc();
		} else {
			if(map[x][y] == GroundType.WALL)
				return "A high dirt cliff. Weird terrain on this planet";
			else if(map[x][y] == GroundType.ROCK)
				return "A big rock. If only you were a giant";
			else if(map[x][y] == GroundType.WATER)
				return "A pool of stagnant water";
			else if(map[x][y] == GroundType.DRY_POOL)
				return "Bah, it looks like this pool has evaporated";
			else if(map[x][y] == GroundType.SHIP_FLOOR)
				return "Gunmetal starship floor";
			else if(map[x][y] == GroundType.SHIP_WALL)
				return "Tough starship bulkhead";
			else if(map[x][y] == GroundType.SHIP_DOOR)
				return "Heavy starship door, currently closed";
			else if(map[x][y] == GroundType.DESK)
				return "Elegant mahogany desk";
			else
				return "Grassy meadow";
		}
	}
	
	public float ambientTemp = 292;
}
