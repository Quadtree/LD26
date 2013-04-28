package com.ironalloygames.planetfall.core;

import java.util.ArrayList;

import playn.core.Color;
import playn.core.PlayN;

public class Actor {
	public int x, y;
	
	public Level curLevel;
	
	public float temperature = 292;
	
	public void update(){
		if(curLevel == null) PlayN.log().warn("no curlevel " + this);
		
		if(temperature > getIgnitionPoint()){
			if(temperature < getBurnTemperature()){
				temperature += 10;
			}
		}
		
		if(temperature > 350){
			for(Actor a : curLevel.actors){
				int dist = Math.abs(a.x - x) + Math.abs(a.y - y);
				if(dist < 8){
					a.temperature += Math.max(0, (temperature - a.temperature) / 80 / (dist+1) * a.getHeatGainMultiplier());
				}
			}
		}
		
		temperature = ((temperature - curLevel.ambientTemp) * 0.98f) + curLevel.ambientTemp;
	}
	public void render(){}
	public String getDesc() {
		return " " + (int)(temperature - 273) + (char)0xB0 + "C" + (temperature > getIgnitionPoint() ? " ON FIRE!" : "");
	}
	public float getIgnitionPoint(){ return 1000000; }
	public float getBurnTemperature(){ return 0; }
	
	protected int fixCol(int col){
		if(temperature > getIgnitionPoint()){
			int r = 128 + PFG.s.r.nextInt(128);
			col = Color.rgb(r, r/2, 0);
		}
		return col;
	}
	
	public float getHeatGainMultiplier(){ return 1; }
	
	public boolean isPickupable(){ return true; }
	
	public ArrayList<Actor> inventory = new ArrayList<Actor>();
	
	public String getName(){
		return this.getClass().getSimpleName().replaceAll("([a-z])([A-Z])", "$1 $2");
	}
	
	public boolean isCraftable(){
		return false;
	}
	
	public boolean isUsableInDirection(){
		return false;
	}
	
	public boolean isUsableOnSelf(){
		return false;
	}
	
	public enum UseDirection {
		EAST,
		SOUTH,
		WEST,
		NORTH
	}
	
	public void useInDirection(float dir, Actor user){
	}
}
