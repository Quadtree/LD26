package com.ironalloygames.planetfall.java;

import playn.core.PlayN;
import playn.java.JavaPlatform;

import com.ironalloygames.planetfall.core.PFG;

public class PFGJava {

  public static void main(String[] args) {
    JavaPlatform.Config config = new JavaPlatform.Config();
    // use config to customize the Java platform, if needed
    JavaPlatform platform = JavaPlatform.register(config);
    
    platform.graphics().registerFont("Droid Sans Mono", "DroidSansMono.ttf");
    
    platform.graphics().setSize(800, 600);
    
    PlayN.run(new PFG());
  }
}
