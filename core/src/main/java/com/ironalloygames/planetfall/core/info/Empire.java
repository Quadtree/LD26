package com.ironalloygames.planetfall.core.info;

import com.ironalloygames.planetfall.core.PFG;

public class Empire {
	static String[] empireTypes = {"Alliance", "Empire", "Federation", "People", "Nation", "Denizens", "Citizens"};
	static String[] adj = {"Free", "Undaunted", "Merciless", "Imperial", "Empyrian", "United", "Allied", "Unshakable", "Fearless", "Peaceful"};
	static String[] ofs = {"Worlds", "Humans", "People", "Allies", "Freedom", "Peace", "Tyranny", "Devastation", "Slaughter", "Carnage", "Killers"};
	
	public String name;
	
	public Empire(){
		name = "";
		
		while(PFG.s.r.nextBoolean()){
			name += adj[PFG.s.r.nextInt(adj.length)] + " ";
		}
		
		name += empireTypes[PFG.s.r.nextInt(empireTypes.length)];
		
		if(PFG.s.r.nextBoolean() || name.length() < 10){
			name += " of " + ofs[PFG.s.r.nextInt(ofs.length)];
		}
	}
}
