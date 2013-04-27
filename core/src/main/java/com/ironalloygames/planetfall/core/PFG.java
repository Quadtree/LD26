package com.ironalloygames.planetfall.core;

import static playn.core.PlayN.*;
import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.PlayN;
import playn.core.TextFormat;

public class PFG extends Game.Default {
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
		
		img = graphics().createImage(graphics().width(), graphics().height());
		imgLayer = graphics().createImageLayer(img);
		graphics().rootLayer().add(imgLayer);
		
		font = graphics().createFont("Mono", Style.BOLD, 20);
	}
	
	int lastSecond = 0;
	int framesThisSecond = 0;
	int fps = 0;

	@Override
	public void update(int delta) {
		//img.canvas().setFillColor(Color.rgb(255, 255, 255));
		img.canvas().clear();
		
		//img.canvas().setStrokeColor(Color.rgb(255, 0, 0));
		//img.canvas().setFillColor(Color.rgb(255, 0, 0));
		//img.canvas().drawLine(50, 50, 150, 150);
		
		//img.canvas().fillText(graphics().layoutText("ABCDEFG", new TextFormat().withFont(font)), 0, 0);
		
		setTextAt(10,20, "Test", Color.rgb(0, 255, 0));
		setTextAt(0,0, "FPS: " + fps, Color.rgb(0, 255, 0));
		
		if(renderBufferChanged){
			render();
			renderBufferChanged = false;
		}
		
		framesThisSecond++;
		
		if(PlayN.tick() / 1000 != lastSecond){
			lastSecond = PlayN.tick() / 1000;
			fps = framesThisSecond;
			framesThisSecond = 0;
		}
	}
	
	private void render(){
		for(int x=0;x<screenTileWidth;++x){
			for(int y=0;y<screenTileHeight;++y){
				img.canvas().setFillColor(renderBufferColor[x][y]);
				img.canvas().fillText(graphics().layoutText("" + renderBuffer[x][y], new TextFormat().withFont(font)), x*CHAR_WIDTH, y*CHAR_HEIGHT);
			}
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
}
