package com.ironalloygames.planetfall.core.info;

import com.ironalloygames.planetfall.core.PFG;

public class Weapon {
	public String name;
	
	public Weapon(){
		while(true){
			name = "";
			
			do {
				name += (char)((int)'A' + PFG.s.r.nextInt(26));
			} while(PFG.s.r.nextBoolean());
			
			name += "-";
			
			int modelNumber = 0;
			
			do {
				modelNumber += PFG.s.r.nextInt(10) * Math.pow(10, PFG.s.r.nextInt(5));
			} while(PFG.s.r.nextBoolean());
			
			name += modelNumber;
			
			break;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
