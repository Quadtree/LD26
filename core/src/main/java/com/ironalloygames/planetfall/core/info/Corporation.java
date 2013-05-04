package com.ironalloygames.planetfall.core.info;

import com.ironalloygames.planetfall.core.PFG;

public class Corporation {
	protected static Corporation[] corps;
	
	public static Corporation get(){
		if(corps == null){
			corps = new Corporation[4];
			
			for(int i=0;i<corps.length;++i){
				corps[i] = new Corporation();
			}
		}
		
		return corps[PFG.s.r.nextInt(corps.length)];
	}
	
	protected Corporation(){
		String noun[] = {"System", ""
	}
	
	protected String mutateWord(String word){
		if(PFG.s.r.nextBoolean()){
			int cutPoint = 0;
			boolean foundVowel = false;
			
			for(int i=word.length() - 1;i >= 0;i--){
				if(word.toUpperCase().charAt(i) == 'A' || word.toUpperCase().charAt(i) == 'E' || word.toUpperCase().charAt(i) == 'I' || word.toUpperCase().charAt(i) == 'O' || word.toUpperCase().charAt(i) == 'U'){
					if(!foundVowel){
						cutPoint = Math.max(1, i - 1);
						break;
					}
				}
			}
			
			String stub = word.substring(0, cutPoint);
			
			String[] newEndings = {"ic", "auric", "tera", "olic", "itium", "ium", "asic", "kolic", "ius", "era", "orum", "arum"};
			
			return stub + newEndings[PFG.s.r.nextInt(newEndings.length)];
		}
		
		return word;
	}
}
