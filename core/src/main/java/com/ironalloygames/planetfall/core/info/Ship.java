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
	
	public Ship(){
		name = "";
		boolean adjIn = false;
		
		if(PFG.s.r.nextInt(2) == 0){ name += adj[PFG.s.r.nextInt(adj.length)] + " "; adjIn = true; }
		name += noun[PFG.s.r.nextInt(noun.length)];
		if(PFG.s.r.nextInt(3) != 0 || !adjIn) name += verber[PFG.s.r.nextInt(verber.length)];
		
		className = "";
		
		if(PFG.s.r.nextInt(2) == 0) className += classAdj[PFG.s.r.nextInt(classAdj.length)] + " ";
		className += classNames[PFG.s.r.nextInt(classNames.length)];
	}
}
