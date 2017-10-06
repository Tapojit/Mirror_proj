package edu.mtholyoke.cs.comsc243.graphicsDemos.curves;

import processing.core.PApplet;

/**
 * ProcessingApp is a slight extension over PApplet.  
 * 
 * Instead of draw classes extending ProcessingApp should override
 * update() and render().  This encourage calculating and updating the position
 * of all objects before drawing them.  (Which might be needed for some apps).
 * It also adds pMousePressed and mouseJustPressed variables;
 * @author eitan
 *
 */
public abstract class ProcessingApp extends PApplet {
	
		
	/**
	 * pMousePressed is true when the mouse button was pressed in the last frame.
	 */
	public boolean pMousePressed = false;

	/**
	 * mouseJustPressed is true when the mouse was just pressed in this frame
	 * (but was note pressed in the last frame)
	 */
	public boolean mouseJustPressed = false;
	
	public void draw() {
		mouseJustPressed = mousePressed && (! pMousePressed);
		update();
		render();
		pMousePressed = mousePressed;
	}
	
	public abstract void update();
	public abstract void render();
	

}
