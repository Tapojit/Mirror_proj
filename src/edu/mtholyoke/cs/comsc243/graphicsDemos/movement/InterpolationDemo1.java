package edu.mtholyoke.cs.comsc243.graphicsDemos.movement;

import processing.core.PApplet;

public class InterpolationDemo1 extends PApplet {
	float t = 0;

	float x1 = 10;
	float y1 = 10;

	float x2 = 290;
	float y2 = 10;

	float x3 = 290;
	float y3 = 290;

	public void settings(){
		size(300,300);
	}

	public void setup(){
	}

	public void draw(){
		background(200);

		strokeWeight(3);
		stroke(255,0,0);
		line(x1, y1, x2, y2);

		float interpX1 = lerp(x1, x2, t);		
		float interpY1 = lerp(y1, y2, t);		
		stroke(1);
		fill(255,0,0);
		ellipse(interpX1,interpY1, 10,10);

		
		strokeWeight(3);
		stroke(0,255,0);
		line(x2, y2, x3, y3);
		
		float interpX2 = lerp(x2, x3, t);		
		float interpY2 = lerp(y2, y3, t);		
		stroke(1);
		fill(0,255,0);
		ellipse(interpX2,interpY2, 10,10);


		
		strokeWeight(3);
		stroke(0,0, 255);
		line(interpX1, interpY1, interpX2, interpY2);		
		 
		
		
		stroke(1);
		float interpX3 = lerp(interpX1, interpX2, t);		
		float interpY3 = lerp(interpY1, interpY2, t);		
		fill(0, 0, 255);
		ellipse(interpX3,interpY3, 10,10);
		 

		t += .005;
		t %= 1.0;
	}

	public static void main(String[] args) {
		PApplet.main(InterpolationDemo1.class.getName());
	}

}
