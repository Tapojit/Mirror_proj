package edu.mtholyoke.cs.comsc243.graphicsDemos.curves;

import processing.core.PApplet;

public class DraggablePoint extends PObject {
	
	
	public float x;
	public float y;
	public float r = 20;

	public int color = 0;
	public int hoverColor = 0;
	public int selectionColor = 0;
	
	boolean isSelected = false;
	boolean isMouseOver = false;

	public DraggablePoint(float x, float y, ProcessingApp ProcessingApp) {
		super(ProcessingApp);
		this.x = x;
		this.y = y;
		color = app.color(255, 150, 150);
		hoverColor = app.color(255, 75, 75);
		selectionColor = app.color(255,0,0);
	}
	

	public boolean isOver(float x, float y) {
		return PApplet.dist(x, y, this.x, this.y) <= r;
	}
	
	public void update() {
		isMouseOver = isOver(app.mouseX, app.mouseY); 

		if(isSelected) {
			if(app.mousePressed) {
				//point dragged dragged
				float dx = app.mouseX - app.pmouseX;
				float dy = app.mouseY - app.pmouseY;
				x += dx;
				y += dy;
			} else {
				//deselect
				isSelected = false;
			} 
		} else {
			if (isMouseOver && app.mouseJustPressed) {
				isSelected = true;
			} else {
				isSelected = false;
			}
		}
	}
	
	public void render() {		
		app.noStroke();
		if(isSelected) {
			app.fill(selectionColor);
		} else if (isMouseOver) {
			app.fill(hoverColor);
		} else {
			app.fill(color);
		}
		app.ellipse(x,y,r,r);
	}


}
