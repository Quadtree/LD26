package com.ironalloygames.planetfall.core.info;

import com.ironalloygames.planetfall.core.PFG;

public class Weapon {
	public String name;
	
	public Weapon(){
		while(true){
			name = "";
			
			int c = (int)Math.ceil(Math.abs(PFG.s.r.nextGaussian() * 2));
			
			for(int i=0;i<c;++i){
				name += (char)((int)'A' + PFG.s.r.nextInt(26));
			}
			
			name += "-";
			
			int modelNumber = 0;
			
			c = (int)Math.ceil(Math.abs(PFG.s.r.nextGaussian()));
			
			for(int i=0;i<c;++i){
				modelNumber += (PFG.s.r.nextInt(9)+1) * Math.pow(10, Math.abs(PFG.s.r.nextGaussian()) * 2);
			}
			
			name += modelNumber;
			
			c = (int)Math.ceil(Math.abs(PFG.s.r.nextGaussian())) - 1;
			
			for(int i=0;i<c;++i){
				name += (char)((int)'A' + (int)Math.abs(PFG.s.r.nextGaussian() * 2));
			}
			
			break;
		}
	}

	@Override
	public String toString() {
		return name;
	}
}
