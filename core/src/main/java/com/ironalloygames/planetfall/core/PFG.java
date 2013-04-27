package com.ironalloygames.planetfall.core;

import static playn.core.PlayN.*;
import playn.core.CanvasImage;
import playn.core.Color;
import playn.core.Font;
import playn.core.Font.Style;
import playn.core.Game;
import playn.core.Image;
import playn.core.ImageLayer;
import playn.core.TextFormat;

public class PFG extends Game.Default {
	CanvasImage img;
	ImageLayer imgLayer;
	
	Font font;
	
	int screenTileWidth;
	int screenTileHeight;
	
	char[][] renderBuffer;
	int[][] renderBufferColor;
	
	public PFG() {
		super(33); // call update every 33ms (30 times per second)
	}

	@Override
	public void init() {
		screenTileWidth = graphics().width() / 11;
		screenTileHeight = graphics().height() / 14;
		
		img = graphics().createImage(graphics().width(), graphics().height());
		imgLayer = graphics().createImageLayer(img);
		graphics().rootLayer().add(imgLayer);
		
		font = graphics().createFont("Mono", Style.BOLD, 20);
	}

	@Override
	public void update(int delta) {
		//img.canvas().setFillColor(Color.rgb(255, 255, 255));
		img.canvas().clear();
		
		img.canvas().setStrokeColor(Color.rgb(255, 0, 0));
		img.canvas().setFillColor(Color.rgb(255, 0, 0));
		//img.canvas().drawLine(50, 50, 150, 150);
		
		img.canvas().fillText(graphics().layoutText("ABCDEFG", new TextFormat().withFont(font)), 0, 0);
	}
	
	public void setCharAtReal(int x, int y, char text, int color){
		
	}
	
	public void setTextAt(int x, int y, String text, int color){
		
	}

	@Override
	public void paint(float alpha) {
	}
}
