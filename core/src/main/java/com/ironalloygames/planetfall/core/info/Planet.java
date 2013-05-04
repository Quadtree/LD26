package com.ironalloygames.planetfall.core.info;

import java.util.HashMap;
import java.util.Map.Entry;

import com.ironalloygames.planetfall.core.PFG;

public class Planet {
	public String name;
	
	public Planet(){
		Person p = new Person();
		
		name = p.lastName + " ";
		
		HashMap<Integer, String> numeral = new HashMap<Integer, String>();
		numeral.put(100, "C");
		numeral.put(90, "XC");
		numeral.put(50, "L");
		numeral.put(40, "XL");
		numeral.put(10, "X");
		numeral.put(9, "IX");
		numeral.put(5, "V");
		numeral.put(4, "IV");
		numeral.put(1, "I");
		
		int planetNumber = (int) Math.round(Math.abs(PFG.s.r.nextGaussian() * 2 + 3));
		
		while(planetNumber > 0){
			
		}
	}
}
