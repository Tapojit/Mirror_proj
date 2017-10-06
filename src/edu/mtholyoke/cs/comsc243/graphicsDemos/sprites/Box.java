package edu.mtholyoke.cs.comsc243.graphicsDemos.sprites;

import processing.core.PApplet;

public class Box extends DrawObject {
	
	float x; 
	float y;
	float d; // diameter
	
	float dx;
	float dy;

	public Box(PApplet applet, float height, float minD, float maxD) {
		super(applet);
		x = (float) (Math.random() * applet.width);
		y = (float) (Math.random() * height);
		d = (float) applet.random(minD, maxD);
	}

	public void update() {
		if(y+d >= applet.height) {
			if(dy >= 0) {
				dy *= -.9f;
			}
		}
		dy += .01; //acceleration due to gravity
		y += dy;
		
	}
	
	public void draw() {
		applet.rect(x,y,d,d);
	}

}
