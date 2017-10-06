package edu.mtholyoke.cs.comsc243.graphicsDemos.curves;

import java.util.ArrayList;

import processing.core.PApplet;

public class BezDemo extends ProcessingApp {

	ArrayList<DraggablePoint>  points = new ArrayList<DraggablePoint>();


	public void settings(){
		size(300,300, P2D);
	}

	public void setup() {
		points.add(new DraggablePoint(10, 10, this));
		points.add(new DraggablePoint(100, 10, this));
		points.add(new DraggablePoint(290, 100, this));
		points.add(new DraggablePoint(290, 290, this));
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
		line(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y);
		line(points.get(2).x, points.get(2).y, points.get(3).x, points.get(3).y);
		stroke(0);
		fill(100,100, 255);
		bezier(
				points.get(0).x, points.get(0).y,
				points.get(1).x, points.get(1).y,
				points.get(2).x, points.get(2).y,
				points.get(3).x, points.get(3).y
				);



	}
	public static void main(String[] args) {
		PApplet.main(BezDemo.class.getName());

	}



}
