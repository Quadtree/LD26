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
		String noun[] = {"Systems", "Weaponry", "Solutions", "Construction", "Force", "Intelligence", "Destruction", "Devastation", "Decimation", "Projection"};
		String adj[] = {"Allied", "Intelligent", "Forward", "United", "Advanced", "Express", "Vorpal"};
		String app[] = {"ltd.", "pty.", "inc.", "gmbh.", "corp."};
		
		while(true){
			name = "";
			
			if(PFG.s.r.nextInt(3) == 0){
				Person founder = new Person();
				name += founder.lastName + " ";
			}
			
			if(PFG.s.r.nextInt(4) != 0){
				while(PFG.s.r.nextBoolean()){
					name += mutateWord(adj[PFG.s.r.nextInt(adj.length)]) + " ";
				}
			} else {
				do {
					name += (char)((int)('A') + PFG.s.r.nextInt(26));
				} while(PFG.s.r.nextInt(3) != 0);
				name += " ";
			}
			
			name += mutateWord(noun[PFG.s.r.nextInt(noun.length)]);
			
			do {
				name += " " + app[PFG.s.r.nextInt(app.length)];
			} while(PFG.s.r.nextBoolean());
			
			HashSet<String> words = new HashSet<String>();
			
			boolean dup = false;
			
			for(String s : name.split(" ")){
				if(words.contains(s))
					dup = true;
				else
					words.add(s);
			}
			
			if(!dup && name.length() > 5 && name.length() < 25 && name.split(" ").length >= 3) break;
		}
	}
	
	protected String mutateWord(String word){
		if(PFG.s.r.nextBoolean()){
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
