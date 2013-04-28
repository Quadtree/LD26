package com.ironalloygames.planetfall.core;

import java.util.ArrayList;

import playn.core.Color;
import playn.core.PlayN;

public class Actor implements Comparable<Actor>{
	public int x, y;
	
	public Level curLevel;
	
	public float hp = 1;
	
	public float temperature = 292;
	
	public void update(){
		if(curLevel == null) PlayN.log().warn("no curlevel " + this);
		
		if(temperature > getIgnitionPoint()){
			if(temperature < getBurnTemperature()){
				temperature += 10;
			}
		}
		
		float[] heatTransferRate = {100, 20, 8, 5, 3, 2, 2, 2, 1, 1, 1, 1};
		
		if(temperature > 350){
			for(Actor a : curLevel.actors){
				int dist = Math.abs(a.x - x) + Math.abs(a.y - y);
				if(dist < 8){
					a.temperature += Math.max(0, (temperature - a.temperature) / 3000 * heatTransferRate[dist] * a.getHeatGainMultiplier());
				}
			}
		}
		
		temperature = ((temperature - curLevel.ambientTemp) * 0.995f) + curLevel.ambientTemp;
		
		if(temperature > getHeatDamagePoint()){
			hp -= (temperature - getHeatDamagePoint()) / 200 * fireDamageMultiplier();
		}
	}
	public void render(){}
	public String getDesc() {
		return "";
	}
	public String getLongDesc(){ return getDesc() + " " + (int)(temperature - 273) + (char)0xB0 + "C" + (temperature > getIgnitionPoint() ? " ON FIRE!" : "") + " " + (int)(hp*100) + "%"; }
	public float getIgnitionPoint(){ return 450; }
	public float getBurnTemperature(){ return 550; }
	public float getHeatDamagePoint(){ return 345; }
	
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
	
	public boolean isCraftable(Unit user){
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
	
	public void useInDirection(float dir, Unit user){
	}
	
	public void destroyed(){
		
	}
	
	public int getRenderPriority(){
		return 0;
	}
	@Override
	public int compareTo(Actor o) {
		return this.getRenderPriority() - o.getRenderPriority();
	}
	
	public float fireDamageMultiplier(){ return 1.f / 500.f; }
	
	public void craft(Unit user){
	}
	
	public void useOnSelf(Unit user){
	}
	
	public String getItemDesc(){ return getDesc(); }
}
