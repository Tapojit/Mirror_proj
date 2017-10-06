package edu.mtholyoke.cs.comsc243.graphicsDemos.sprites;

import processing.core.PApplet;

public abstract class DrawObject {
	protected PApplet applet;	
	
	
	public DrawObject(PApplet applet) {
		this.applet  = applet;

	}
	
	public abstract void draw();
	public abstract void update();

}
