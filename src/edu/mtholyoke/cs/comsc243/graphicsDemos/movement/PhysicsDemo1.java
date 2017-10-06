package edu.mtholyoke.cs.comsc243.graphicsDemos.movement;

import processing.core.PApplet;
import processing.opengl.PGraphics2D;
public class PhysicsDemo1 extends PApplet {
	//position
	float x=100;
	float y=0;
	
	//velocity
	float dx=.05f;
	float dy=0f;
	
	//force
	float fx = 0;
	float fy = 0.05f;
	
	//assumes mass = 1
	
	

	public void settings(){
		size(300,300, P2D);
	//	size(300,300, FX2D);
	}

	public void setup(){
	}

	public void draw(){
		background(200);
		ellipse(x,y, 10.0f,10.0f);
		
		//position = old position + velocity
		x+=dx;
		y+=dy;
		
		// change in velocity = force * time / mass
		// assume time = 1.  Mass = 1
		dx+=fx;
		dy+=fy;
		
		//bounce off floor
		if(y + 10 > height) {
			if(dy > 0) {
				dy = -dy * 0.9f;
			}
		}

		
	}

	public static void main(String[] args) {
		PApplet.main(PhysicsDemo1.class.getName());
	}

}
