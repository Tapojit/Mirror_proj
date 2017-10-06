package edu.mtholyoke.cs.comsc243.graphicsDemos.curves;

public abstract class PObject {
	protected ProcessingApp app;	
	
	
	public PObject(ProcessingApp app) {
		this.app  = app;

	}
	
	public abstract void render();
	public abstract void update();

}
