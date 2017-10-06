package edu.mtholyoke.cs.comsc243.graphicsDemos.curves;

import java.awt.Point;
import java.util.ArrayList;

import processing.core.PApplet;

public class BezVertextDemo extends ProcessingApp {
	
	ArrayList<DraggablePoint>  points = new ArrayList<DraggablePoint>();


	public void settings(){
		size(300,300, P2D);
	}

	public void setup() {
		int pointCnt = 13;
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
		stroke(100);
		for(int i = 0; i < points.size()-3; i += 3) {
		line(points.get(i).x, points.get(i).y, points.get(i+1).x, points.get(i+1).y);
		line(points.get(i+3).x, points.get(i+3).y, points.get(i+2).x, points.get(i+2).y);
		}

		stroke(0);
		fill(100,100, 255, 100);
		beginShape();
		vertex(points.get(0).x, points.get(0).y);
		for(int i = 1; i < points.size(); i += 3) {
			bezierVertex(
					points.get(i).x, points.get(i).y, 
					points.get(i+1).x, points.get(i+1).y,
					points.get(i+2).x, points.get(i+2).y);
			
		}

		endShape();
		

		
	}
	public static void main(String[] args) {
		PApplet.main(BezVertextDemo.class.getName());

	}



}
