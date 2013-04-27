package com.ironalloygames.planetfall.core;

import static playn.core.PlayN.*;

import java.util.HashMap;

import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.ImmediateLayer.Renderer;
import playn.core.PlayN;
import playn.core.Surface;
import playn.core.TextFormat;

public class PFG extends Game.Default implements Renderer {
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
	
	public PFG() {
		super(33); // call update every 33ms (30 times per second)
	}

	@Override
	public void init() {
		screenTileWidth = graphics().width() / CHAR_WIDTH;
		screenTileHeight = graphics().height() / CHAR_HEIGHT;
		
		renderBuffer = new char[screenTileWidth][screenTileHeight];
		renderBufferColor = new int[screenTileWidth][screenTileHeight];
		
		graphics().rootLayer().add(graphics().createImmediateLayer(this));
		
		font = graphics().createFont("Mono", Style.BOLD, 20);
	}
	
	int lastSecond = 0;
	int framesThisSecond = 0;
	int fps = 0;

	@Override
	public void update(int delta) {
		setTextAt(0,0,"FPS " + fps, Color.rgb(0, 0, 255));
		
		framesThisSecond++;
		
		if(PlayN.tick() / 1000 != lastSecond){
			lastSecond = PlayN.tick() / 1000;
			fps = framesThisSecond;
			framesThisSecond = 0;
		}
	}
	
	public void setCharAtReal(int x, int y, char text, int color){
		renderBufferChanged = true;
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
					CanvasImage ci = graphics().createImage(CHAR_WIDTH, CHAR_HEIGHT);
					ci.canvas().setFillColor(Color.rgb(255, 255, 255));
					ci.canvas().fillText(graphics().layoutText("" + renderBuffer[x][y], new TextFormat().withFont(font)), 0, 0);
					glyphs.put(renderBuffer[x][y], ci);
				}
				
				surface.setTint(renderBufferColor[x][y]);
				surface.drawImage(glyphs.get(renderBuffer[x][y]), x*CHAR_WIDTH, y*CHAR_HEIGHT);
			}
		}
	}
}
