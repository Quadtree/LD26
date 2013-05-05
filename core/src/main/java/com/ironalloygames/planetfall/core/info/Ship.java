package com.ironalloygames.planetfall.core.info;

import com.ironalloygames.planetfall.core.PFG;

public class Ship {
	static String[] verber = {"breaker", "driver", "devastator", "verber", "crusher", "dancer", "sword", "man", "warrior", "slayer"};
	static String[] noun = {"Noun", "War", "Planet", "Star", "Creep", "Asteroid", "Thunder", "Shadow", "Moon"};
	static String[] adj = {"Dauntless", "Valiant", "Astral", "Furious", "Shadowy", "Dark", "Black", "White", "Red"};
	
	public String name;
	public String className;
	
	static String[] classNames = {"Gunboat", "Sloop", "Corvette", "Cruiser", "Destroyer", "Battlecruiser", "Battleship", "Dreadnought"};
	static String[] classAdj = {"Super", "Heavy", "Light", "Mega", "Mini", "Advanced", "Obsolete"};
	
	public int mass;
	
	public int getCrew(){
		return (int)(Math.pow(mass, 0.55));
	}
	
	public long getCost(){
		return (long)(Math.pow(mass, 0.9) * 80000);
	}
	
	public Ship(){
		name = "";
		boolean adjIn = false;
		
		if(PFG.s.r.nextInt(2) == 0){ name += adj[PFG.s.r.nextInt(adj.length)] + " "; adjIn = true; }
		name += noun[PFG.s.r.nextInt(noun.length)];
		if(PFG.s.r.nextInt(3) != 0 || !adjIn) name += verber[PFG.s.r.nextInt(verber.length)];
		
		className = "";
		
		int classAdjId = -1;
		int classNameId = 0;
		
		if(PFG.s.r.nextInt(2) == 0){
			classAdjId = PFG.s.r.nextInt(classAdj.length);
			className += classAdj[classAdjId] + " ";
		}
		
		classNameId = PFG.s.r.nextInt(classNames.length);
		
		className += classNames[classNameId];
		
		
		int baseMass[] = {8000, 5000, 3000, 45000, 12000, 100000, 280000, 400000};
		double massModifier[] = {2, 1.6, 0.7, 2.5, 0.4, 0.95, 1.1};
		
		mass = baseMass[classNameId];
		
		if(classAdjId != -1) mass *= massModifier[classAdjId];
	}
}
