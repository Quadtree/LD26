package com.ironalloygames.planetfall.core.info;

import java.util.HashSet;

import playn.core.PlayN;

import com.ironalloygames.planetfall.core.PFG;

public class Corporation {
	protected static Corporation[] corps;
	
	public String name;
	
	public static Corporation get(){
		if(corps == null){
			corps = new Corporation[4];
			
			for(int i=0;i<corps.length;++i){
				corps[i] = new Corporation();
				PlayN.log().debug(corps[i].name);
			}
		}
		
		return corps[PFG.s.r.nextInt(corps.length)];
	}
	
	protected Corporation(){
		String noun[] = {"Systems", "Weaponry", "Solutions", "Construction", "Force", "Intelligence", "Destruction", "Devastation",
				"Decimation", "Projection", "Alliance", "Cell", "Planet", "Extraction", "Mining", "Foundry", "Security", "Defense", "Mercenaries"};
		String adj[] = {"Allied", "Intelligent", "Forward", "United", "Advanced", "Express", "Vorpal", "Awesome", "Economical", "Rapid", 
				"Scalar", "Vector", "Quaternion", "Mathematical", "Star", "Moon", "Sun", "Solar", "Planetary", "Futuristic", "Golden", "Silver", "Platinum", "Stellar"};
		String app[] = {"ltd.", "pty.", "inc.", "gmbh.", "corp."};
		
		while(true){
			name = "";
			
			if(PFG.s.r.nextInt(3) == 0){
				Person founder = new Person();
				name += founder.lastName + " ";
			}
			
			if(PFG.s.r.nextInt(4) != 0){
				while(PFG.s.r.nextBoolean()){
					name += portmanteau(adj) + " ";
				}
			} else {
				do {
					name += (char)((int)('A') + PFG.s.r.nextInt(26));
				} while(PFG.s.r.nextInt(3) != 0);
				name += " ";
			}
			
			name += portmanteau(noun);
			
			do {
				name += " " + app[PFG.s.r.nextInt(app.length)];
			} while(PFG.s.r.nextBoolean());
			
			HashSet<String> words = new HashSet<String>();
			
			boolean dup = false;
			
			for(String s : name.split(" ")){
				if(s.length() <= 3) dup = true;
				if(words.contains(s))
					dup = true;
				else
					words.add(s);
			}
			
			if(!dup && name.length() > 5 && name.length() < 25 && name.split(" ").length >= 3) break;
		}
	}
	
	protected String portmanteau(String[] poss){
		if(PFG.s.r.nextBoolean()){
			return mutateWord(poss[PFG.s.r.nextInt(poss.length)]);
		} else {
			String word1 = poss[PFG.s.r.nextInt(poss.length)];
			String word2 = poss[PFG.s.r.nextInt(poss.length)];
			
			word1 = word1.substring(0, PFG.s.r.nextInt(word1.length() - 1) + 1);
			word2 = word2.substring(PFG.s.r.nextInt(word2.length() - 1), word2.length());
			
			return word1 + word2.toLowerCase();
		}
	}
	
	protected String mutateWord(String word){
		if(PFG.s.r.nextInt(4) == 0){
			int cutPoint = 0;
			boolean foundVowel = false;
			
			for(int i=word.length() - 1;i >= 0;i--){
				if(word.toUpperCase().charAt(i) == 'A' || word.toUpperCase().charAt(i) == 'E' || word.toUpperCase().charAt(i) == 'I' || word.toUpperCase().charAt(i) == 'O' || word.toUpperCase().charAt(i) == 'U'){
					if(!foundVowel){
						cutPoint = Math.max(1, i);
						break;
					}
				}
			}
			
			String stub = word.substring(0, cutPoint);
			
			String[] newEndings = {"ic", "auric", "tera", "olic", "itium", "ium", "asic", "kolic", "ica", "era", "orum", "arum", "ii"};
			
			word = stub + newEndings[PFG.s.r.nextInt(newEndings.length)];
			
			//word = word.replaceAll("(a-z)(\\1)", "$1");
		}
		
		return word;
	}
}
