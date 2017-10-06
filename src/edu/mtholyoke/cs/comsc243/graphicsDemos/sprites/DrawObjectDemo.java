package edu.mtholyoke.cs.comsc243.graphicsDemos.sprites;

import java.util.ArrayList;

import processing.core.PApplet;

public class DrawObjectDemo extends PApplet {
	ArrayList<DrawObject> pobjects = new ArrayList<DrawObject>();

	public void settings(){
		size(300,300, P2D);
	}

	public void setup(){
		for(int i = 0; i< 100; i++) {
			pobjects.add(new Ball(this, 50, 5, 15));
		}
		for(int i = 0; i< 100; i++) {
			pobjects.add(new Box(this, 50, 5, 15));
		}
	}


	public void draw(){
		
		
		background(200);

		for(DrawObject obj : pobjects) {
			obj.update();
			obj.draw();
		}


	}

	public static void main(String[] args) {
		PApplet.main(DrawObjectDemo.class.getName());
	}

}
