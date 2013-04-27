package com.ironalloygames.planetfall.core;

import static playn.core.PlayN.*;

import java.util.HashMap;
import java.util.Random;

import com.ironalloygames.planetfall.core.info.Empire;
import com.ironalloygames.planetfall.core.info.Person;
import com.ironalloygames.planetfall.core.info.Person.NameGender;
import com.ironalloygames.planetfall.core.info.Ship;
import com.ironalloygames.planetfall.dialog.Dialog;
import com.ironalloygames.planetfall.dialog.StartCinematic;

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
import playn.core.Mouse.ButtonEvent;
import playn.core.Mouse.MotionEvent;
import playn.core.Mouse.WheelEvent;
import playn.core.PlayN;
import playn.core.Surface;
import playn.core.TextFormat;

public class PFG extends Game.Default implements Renderer, Listener, playn.core.Mouse.Listener {
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
	
	public PlanetLevel planetLevel;
	public PodLevel podLevel;
	public RecruitingOfficeLevel officeLevel;
	
	public Level currentLevel;
	
	public Random r = new Random();
	
	public PC pc;
	
	int tick = 0;
	
	public Dialog curDialog;
	
	public Empire alliedEmpire;
	public Empire enemyEmpire;
	
	public PFG() {
		super(33); // call update every 33ms (30 times per second)
	}
	
	public Recruiter rec;

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
		podLevel = new PodLevel();
		officeLevel = new RecruitingOfficeLevel();
		
		rec = new Recruiter();
		rec.x = 8;
		rec.y = 3;
		officeLevel.actors.add(rec);
		
		currentLevel = podLevel;
		
		alliedEmpire = new Empire();
		enemyEmpire = new Empire();
		
		pc = new PC();
		pc.x = r.nextInt(PlanetLevel.MAP_WIDTH);
		pc.y = r.nextInt(PlanetLevel.MAP_WIDTH);
		
		while(!podLevel.isPassable(pc.x, pc.y)){
			pc.x = r.nextInt(PlanetLevel.MAP_WIDTH);
			pc.y = r.nextInt(PlanetLevel.MAP_WIDTH);
		}
		
		pc.curLevel = currentLevel;
		
		currentLevel.actors.add(pc);
		
		keyboard().setListener(this);
		mouse().setListener(this);
		
		for(int i=0;i<10;++i){
			Ship s = new Ship();
			Empire e = new Empire();
			Person p = new Person();
			log().debug("The " + s.className + " " + s.name + " of the " + e.name + " under " + p.firstName + " " + p.lastName);
		}
		
		curDialog = new StartCinematic();
	}
	
	int lastSecond = 0;
	int framesThisSecond = 0;
	int fps = 0;

	@Override
	public void update(int delta) {
		camX = pc.x;
		camY = pc.y;
		
		while(!pc.canAct() && curDialog == null){
			currentLevel.update();
			tick++;
		}
		
		for(int x=0;x<renderBuffer.length;++x){
			for(int y=0;y<renderBuffer[0].length;++y){
				renderBufferColor[x][y] = 0;
			}
		}
		
		currentLevel.render();
		
		if(movX != 0 || movY != 0) pc.move(movX, movY);
		
		setTextAt(0,0, "FPS " + fps, Color.rgb(255, 255, 255));
		
		framesThisSecond++;
		
		if(PlayN.tick() / 1000 != lastSecond){
			lastSecond = PlayN.tick() / 1000;
			fps = framesThisSecond;
			framesThisSecond = 0;
		}
		
		setTextAt(0,1, "T" + tick, Color.rgb(255, 255, 255));
		
		setCharAtReal(mouseRealTileX, mouseRealTileY, '\0', Color.rgb(255, 255, 255));
		
		setTextAt(8,0, currentLevel.getDesc(mouseRealTileX, mouseRealTileY), Color.rgb(255, 255, 255));
		
		if(curDialog != null){
			curDialog.render();
			if(!curDialog.active) curDialog = null;
		}
	}
	
	public void setCharAtReal(int x, int y, char text, int color){
		
		if(Math.abs(x - camX) > 30 || Math.abs(y - camY) > 30) return;
		if(!currentLevel.hasLOS(pc.x, pc.y, x, y)) return;
		
		int screenTileX = x - camX + screenTileWidth / 2;
		int screenTileY = y - camY + screenTileHeight / 2;
		
		if(screenTileX >= 0 && screenTileY >= 0 && screenTileX < screenTileWidth && screenTileY < screenTileHeight){
			if(text != '\0') renderBuffer[screenTileX][screenTileY] = text;
			renderBufferColor[screenTileX][screenTileY] = color;
			renderBufferChanged = true;
		}
	}
	
	public void setTextAt(int x, int y, String text, int color){
		int cx = x;
		int cy = y;
		
		for(int i=0;i<text.length();++i){
			renderBuffer[cx][cy] = text.charAt(i);
			renderBufferColor[cx][cy] = color;
			
			cx++;
			if(cx >= screenTileWidth){
				cx = x;
				cy++;
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
		
		if(event.key() == Key.K1 && curDialog != null) curDialog.pick(1);
		if(event.key() == Key.K2 && curDialog != null) curDialog.pick(2);
		if(event.key() == Key.K3 && curDialog != null) curDialog.pick(3);
		if(event.key() == Key.K4 && curDialog != null) curDialog.pick(4);
		if(event.key() == Key.K5 && curDialog != null) curDialog.pick(5);
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
	
	public int mouseRealTileX, mouseRealTileY;

	@Override
	public void onMouseDown(ButtonEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseUp(ButtonEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMove(MotionEvent event) {
		mouseRealTileX = (int)event.x() / CHAR_WIDTH - screenTileWidth / 2 + camX;
		mouseRealTileY = (int)event.y() / CHAR_HEIGHT - screenTileHeight / 2 + camY;
		
		//log().debug(mouseRealTileX + " " + mouseRealTileY);
		//log().debug(pc.x + " " + pc.y);
		
		//renderBufferColor[msotX][msotY] = Color.rgb(255, 255, 255);
		
		
	}

	@Override
	public void onMouseWheelScroll(WheelEvent event) {
		// TODO Auto-generated method stub
		
	}
}
