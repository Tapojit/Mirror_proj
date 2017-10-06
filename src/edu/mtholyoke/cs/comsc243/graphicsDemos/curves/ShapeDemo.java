package edu.mtholyoke.cs.comsc243.graphicsDemos.curves;

import java.util.ArrayList;

import processing.core.PApplet;

public class ShapeDemo extends ProcessingApp {
	
	ArrayList<DraggablePoint>  points = new ArrayList<DraggablePoint>();


	public void settings(){
		size(300,300, P2D);
	}

	public void setup() {
		int pointCnt = 5;
		for(int i = 0; i < pointCnt; i++) {
			float x = lerp(0, width, (float) ((i+.5)/ pointCnt));
			float y = lerp(0, height, (float) ((i+.5)/ pointCnt));
			points.add(new DraggablePoint(x,y, this));
		}
	}
	
	@Override
	public void update() {
		for(DraggablePoint point : points) {
			point.update();
		}
		
	}

	@Override
	public void render() {
		background(255,255,255);
		for(DraggablePoint point : points) {
			point.render();
		}


		
		stroke(0);
		strokeWeight(5);
		fill(100,100, 255);
		beginShape();
		for(DraggablePoint point : points) {
			vertex(point.x, point.y);
		}
		endShape(CLOSE);
		//for a closed shape use endShape(CLOSE);
		
	}
	public static void main(String[] args) {
		PApplet.main(ShapeDemo.class.getName());

	}



}
