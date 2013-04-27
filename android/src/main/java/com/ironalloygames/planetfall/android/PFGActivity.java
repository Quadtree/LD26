package com.ironalloygames.planetfall.android;

import playn.android.GameActivity;
import playn.core.PlayN;

import com.ironalloygames.planetfall.core.PFG;

public class PFGActivity extends GameActivity {

  @Override
  public void main(){
    PlayN.run(new PFG());
  }
}
