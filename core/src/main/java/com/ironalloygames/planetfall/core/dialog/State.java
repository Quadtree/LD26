package com.ironalloygames.planetfall.core.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ironalloygames.planetfall.core.Actor;
import com.ironalloygames.planetfall.core.PFG;
import com.ironalloygames.planetfall.core.item.FusionLancePistol;

public class State {
	public static class Transition {
		public String newState;

		public Transition(String newState) {
			super();
			this.newState = newState;
		}
	}
	
	public interface ChangeListener {
		public void stateChanged(State s);
	}
	
	public interface ChangeConditional {
		public boolean allowChange(State s);
	}
	
	public static class NoRepeat implements ChangeConditional {
		@Override
		public boolean allowChange(State s) {
			return !s.visited;
		}
	}
	
	public static class PlayerIsArmed implements ChangeConditional {
		@Override
		public boolean allowChange(State s) {
			for(Actor a : PFG.s.pc.inventory){
				if(a instanceof FusionLancePistol) return true;
			}
			return false;
		}
	}
	
	public static class End implements ChangeListener {

		@Override
		public void stateChanged(State s) {
			PFG.s.curDialog = null;
		}
	}
	
	protected String prompt;
	
	protected ArrayList<Transition> options = new ArrayList<Transition>();
	
	protected boolean visited;
	
	protected String text;
	
	public List<Transition> getOptions(){
		return Collections.unmodifiableList(options);
	}
	
	protected ArrayList<ChangeListener> changeListener = new ArrayList<ChangeListener>();
	protected ArrayList<ChangeConditional> changeConditionals = new ArrayList<ChangeConditional>();
	
	public boolean isChangeAllowed(){
		for(ChangeConditional cc : changeConditionals){
			if(!cc.allowChange(this)) return false;
		}
		return true;
	}
	
	public void changedTo(){
		for(ChangeListener cc : changeListener){
			cc.stateChanged(this);
		}
		visited = true;
	}
	
	public State add(String target){
		options.add(new Transition(target));
		return this;
	}
	
	public State add(ChangeConditional cc){
		changeConditionals.add(cc);
		return this;
	}
	
	public State add(ChangeListener cc){
		changeListener.add(cc);
		return this;
	}

	public State(String text, String prompt) {
		super();
		this.text = text;
		this.prompt = prompt;
		this.changeConditionals.add(new NoRepeat());
	}
}
