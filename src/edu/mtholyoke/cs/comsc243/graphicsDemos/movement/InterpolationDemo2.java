package edu.mtholyoke.cs.comsc243.graphicsDemos.movement;

import processing.core.PApplet;

public class InterpolationDemo2 extends PApplet {
	float t = 0;
	

	public void settings(){
		size(300,300);
	}

	public void setup(){
	}

	public void draw(){
		background(200);
		
		float x = lerp(0, 300, t);
		float y = lerp(150, 250, t);
		
		stroke(0);
		line(0, 150, 300, 250);
		fill(255,0,0);
		noStroke();
		ellipse(x,y, 20, 20);
		t += .01;
		t %= 1.0;
	}

	public static void main(String[] args) {
		PApplet.main(InterpolationDemo2.class.getName());
	}

}
