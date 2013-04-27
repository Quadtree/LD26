package com.ironalloygames.planetfall.core;

import static playn.core.PlayN.*;

import java.util.HashMap;
import java.util.Random;

import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.Font;
import playn.core.Key;
import playn.core.Font.Style;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ImmediateLayer.Renderer;
import playn.core.Keyboard.Event;
import playn.core.Keyboard.Listener;
import playn.core.Keyboard.TypedEvent;
import playn.core.PlayN;
import playn.core.Surface;
import playn.core.TextFormat;

public class PFG extends Game.Default implements Renderer, Listener {
	private static final int CHAR_WIDTH = 13;
	private static final int CHAR_HEIGHT = 21;
	CanvasImage img;
	ImageLayer imgLayer;
	
	Font font;
	
	int screenTileWidth;
	int screenTileHeight;
	
	char[][] renderBuffer;
	int[][] renderBufferColor;
	
	boolean renderBufferChanged = true;
	
	public static PFG s;
	
	int camX, camY;
	
	PlanetLevel planetLevel;
	
	public Random r = new Random();
	
	public PC pc;
	
	int tick = 0;
	
	public PFG() {
		super(33); // call update every 33ms (30 times per second)
	}

	@Override
	public void init() {
		s = this;
		
		screenTileWidth = graphics().width() / CHAR_WIDTH;
		screenTileHeight = graphics().height() / CHAR_HEIGHT;
		
		renderBuffer = new char[screenTileWidth][screenTileHeight];
		renderBufferColor = new int[screenTileWidth][screenTileHeight];
		
		graphics().rootLayer().add(graphics().createImmediateLayer(this));
		
		font = graphics().createFont("Mono", Style.BOLD, 20);
		
		planetLevel = new PlanetLevel();
		
		pc = new PC();
		pc.x = r.nextInt(PlanetLevel.MAP_WIDTH);
		pc.y = r.nextInt(PlanetLevel.MAP_WIDTH);
		
		while(!planetLevel.isPassable(pc.x, pc.y)){
			pc.x = r.nextInt(PlanetLevel.MAP_WIDTH);
			pc.y = r.nextInt(PlanetLevel.MAP_WIDTH);
		}
		
		pc.curLevel = planetLevel;
		
		planetLevel.actors.add(pc);
		
		keyboard().setListener(this);
	}
	
	int lastSecond = 0;
	int framesThisSecond = 0;
	int fps = 0;

	@Override
	public void update(int delta) {
		camX = pc.x;
		camY = pc.y;
		
		while(!pc.canAct()){
			planetLevel.update();
			tick++;
		}
		
		if(movX != 0 || movY != 0) pc.move(movX, movY);
		
		setTextAt(0,0, "FPS " + fps, Color.rgb(255, 255, 255));
		
		framesThisSecond++;
		
		if(PlayN.tick() / 1000 != lastSecond){
			lastSecond = PlayN.tick() / 1000;
			fps = framesThisSecond;
			framesThisSecond = 0;
		}
		
		setTextAt(0,1, "T" + tick, Color.rgb(255, 255, 255));
	}
	
	public void setCharAtReal(int x, int y, char text, int color){
		
		int screenTileX = x - camX + screenTileWidth / 2;
		int screenTileY = y - camY + screenTileHeight / 2;
		
		if(screenTileX >= 0 && screenTileY >= 0 && screenTileX < screenTileWidth && screenTileY < screenTileHeight){
			renderBuffer[screenTileX][screenTileY] = text;
			renderBufferColor[screenTileX][screenTileY] = color;
			renderBufferChanged = true;
		}
	}
	
	public void setTextAt(int x, int y, String text, int color){
		
		for(int i=0;i<text.length();++i){
			if(renderBuffer.length > x+i){
				renderBuffer[x+i][y] = text.charAt(i);
				renderBufferColor[x+i][y] = color;
			}
		}
		
		renderBufferChanged = true;
	}

	@Override
	public void paint(float alpha) {
	}
	
	HashMap<Character, Image> glyphs = new HashMap<Character, Image>();;

	@Override
	public void render(Surface surface) {
		for(int y=0;y<screenTileHeight;++y){
			for(int x=0;x<screenTileWidth;++x){
				if(!glyphs.containsKey(renderBuffer[x][y])){
					CanvasImage ci = graphics().createImage(CHAR_WIDTH+3, CHAR_HEIGHT+3);
					ci.canvas().setFillColor(Color.rgb(255, 255, 255));
					ci.canvas().fillText(graphics().layoutText("" + renderBuffer[x][y], new TextFormat().withFont(font)), 0, 0);
					glyphs.put(renderBuffer[x][y], ci);
				}
				
				surface.setTint(renderBufferColor[x][y]);
				surface.drawImage(glyphs.get(renderBuffer[x][y]), x*CHAR_WIDTH+3, y*CHAR_HEIGHT+3);
			}
		}
	}
	
	int movX, movY;

	@Override
	public void onKeyDown(Event event) {
		if(event.key() == Key.A) movX = -1;
		if(event.key() == Key.D) movX = 1;
		if(event.key() == Key.W) movY = -1;
		if(event.key() == Key.S) movY = 1;
	}

	@Override
	public void onKeyTyped(TypedEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyUp(Event event) {
		if(event.key() == Key.A) movX = 0;
		if(event.key() == Key.D) movX = 0;
		if(event.key() == Key.W) movY = 0;
		if(event.key() == Key.S) movY = 0;
	}
}
