package com.ironalloygames.planetfall.core.info;

import com.ironalloygames.planetfall.core.PFG;

public class Person {
	static String[] syl = {"la", "xi", "ala", "ama", "anna", "wa", "in", "is", "ik", "ix", "qu", "wa", "ul", "op", "ein", "eal", "var", "woh", "ko", "go", "sa", "pi", "ba", "jul", "lo",
		"dle", "li", "xie", "ser", "ki", "boe"};
	
	public enum NameGender {
		MALE,
		FEMALE
	}
	
	public String firstName;
	public String lastName;
	public NameGender gender;
	
	public Person(){
		if(PFG.s.r.nextBoolean()){
			gender = NameGender.MALE;
		} else {
			gender = NameGender.FEMALE;
		}
		
		String firstName = "";
		
		while(true){
			firstName = "";
			
			while(PFG.s.r.nextBoolean()){
				firstName += syl[PFG.s.r.nextInt(syl.length)];
			}
			
			if(firstName.length() < 4 || firstName.length() > 9) continue;
			
			if((firstName.endsWith("a") || firstName.endsWith("e") || firstName.endsWith("ei") || firstName.endsWith("ki") || firstName.endsWith("qi") || firstName.endsWith("xi")) == (gender == NameGender.FEMALE)){
				break;
			}
		}
		
		String lastName;
		
		while(true){
			lastName = "";
			
			while(PFG.s.r.nextBoolean()){
				lastName += syl[PFG.s.r.nextInt(syl.length)];
			}
			
			if(lastName.length() < 6 || lastName.length() > 12) continue;
			
			break;
		}
		
		this.firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
		this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
	}
	
	public String getHisHer(){
		if(gender == NameGender.MALE)
			return "his";
		else
			return "her";
	}
	
	public String getHimHer(){
		if(gender == NameGender.MALE)
			return "him";
		else
			return "her";
	}
	
	public String getHeShe(){
		if(gender == NameGender.MALE)
			return "he";
		else
			return "she";
	}
	
	public String getManWoman(){
		if(gender == NameGender.MALE)
			return "man";
		else
			return "woman";
	}
	
	public String genderSpecific(String male, String female){
		if(gender == NameGender.MALE)
			return male;
		else
			return female;
	}
}
