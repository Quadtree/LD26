package com.ironalloygames.planetfall.core.info;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.ironalloygames.planetfall.core.PFG;

public class Planet {
	public String name;
	public PlanetType type; 
	
	enum PlanetType {
		Garden,
		Lava,
		Ice,
		Machine,
		Waste
	}
	
	class RomanNumeral {
		int val;
		String sym;
		
		public RomanNumeral(int val, String sym) {
			super();
			this.val = val;
			this.sym = sym;
		}
	}
	
	public Planet(){
		type = PlanetType.values()[PFG.s.r.nextInt(PlanetType.values().length)];
		
		Person p = new Person();
		
		name = p.lastName + " ";
		
		ArrayList<RomanNumeral> numeral = new ArrayList<RomanNumeral>();
		numeral.add(new RomanNumeral(100, "C"));
		numeral.add(new RomanNumeral(90, "XC"));
		numeral.add(new RomanNumeral(50, "L"));
		numeral.add(new RomanNumeral(40, "XL"));
		numeral.add(new RomanNumeral(10, "X"));
		numeral.add(new RomanNumeral(9, "IX"));
		numeral.add(new RomanNumeral(5, "V"));
		numeral.add(new RomanNumeral(4, "IV"));
		numeral.add(new RomanNumeral(1, "I"));
		
		int planetNumber = (int) Math.max(Math.round(Math.abs(PFG.s.r.nextGaussian() * 2 + 3)), 1);
		
		while(planetNumber > 0){
			for(RomanNumeral rn : numeral){
				if(rn.val <= planetNumber){
					name += rn.sym;
					planetNumber -= rn.val;
					break;
				}
			}
		}
	}

	@Override
	public String toString() {
		return name;
	}
	
	public String getWistfulDescription(){
		switch(type){
			case Garden: return "beautiful meadows";
			case Lava: return "blasted hellscape";
			case Ice: return "frozen wastelands";
			case Machine: return "choking smog";
			case Waste: return "vast emptyness";
		}
		return "";
	}
}
