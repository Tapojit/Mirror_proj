package edu.mtholyoke.cs.comsc243.kinectUDP;

import java.io.IOException;

import processing.core.PApplet;
import processing.core.PVector;

public class KinectMirrorProj extends PApplet {
	
	KinectBodyDataProvider kinectReader;

	public void settings() {
		size(600,600);//, P2D);
	}
	
	
	float theta = (float) 0.0;  // Start angle at 0
	int colSwatch=0;
	int spiralCount=1;
	boolean neg=false;
	public void setup(){

		/*
		 * use this code to run your PApplet from data recorded by UPDRecorder 
		 *
		try {
			kinectReader = new KinectBodyDataProvider("recordedData.kinect", 5);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
		 */
		try {
			kinectReader = new KinectBodyDataProvider("test.kinect", 5);
		} catch (IOException e) {
			System.out.println("Unable to creat e kinect producer");
		}
//		kinectReader = new KinectBodyDataProvider(8008);
		kinectReader.start();

	}
	public void draw(){
		//makes the window 2x2
		this.scale(width/2.0f, -height/2.0f);

		//make positive y up and center of window 0,0
		translate(1,-1);
		noStroke();



		background(200,200,200);

		// leave trails instead of clearing background \ 
		//noStroke();
		//fill(0,0,0, 10);
		//rect(-1,-1, 2, 2); //draw transparent rect of the window

		KinectBodyData bodyData = kinectReader.getMostRecentData();
		Body person = bodyData.getPerson(0);
		if(person != null){
			PVector head = person.getJoint(Body.HEAD);
			PVector spine = person.getJoint(Body.SPINE_SHOULDER);
			PVector spineBase = person.getJoint(Body.SPINE_BASE);
			PVector shoulderLeft = person.getJoint(Body.SHOULDER_LEFT);
			PVector shoulderRight = person.getJoint(Body.SHOULDER_RIGHT);
			PVector footLeft = person.getJoint(Body.FOOT_LEFT);
			PVector footRight = person.getJoint(Body.FOOT_RIGHT);
			PVector handLeft = person.getJoint(Body.HAND_LEFT);
			PVector handRight = person.getJoint(Body.HAND_RIGHT);


//			fill(255,255,255);
//			noStroke();
//			drawIfValid(head);
//			drawIfValid(spine);
//			drawIfValid(spineBase);
//			drawIfValid(shoulderLeft);
//			drawIfValid(shoulderRight);
//			drawIfValid(footLeft);
//			drawIfValid(footRight);
//			drawIfValid(handLeft);
//			drawIfValid(handRight);

//			if( (shoulderLeft != null) &&
//					(shoulderRight != null) &&
//					(handLeft != null) &&
//					(handRight != null) ) {
//				stroke(255,0,0, 100);
//				noFill();
//				strokeWeight(.05f); // because of scale weight needs to be much thinner
//				curve(
//						handLeft.x, handLeft.y, 
//						shoulderLeft.x, shoulderLeft.y, 
//						shoulderRight.x, shoulderRight.y,
//						handRight.x, handRight.y
//						);
//
//
//			}
	
			plotSine(handLeft,shoulderLeft,false);
			plotSine(handLeft,shoulderLeft,true);
			plotSine(shoulderLeft,shoulderRight,false);
			plotSine(shoulderLeft,shoulderRight,true);
			plotSine(shoulderRight,handRight,false);
			plotSine(shoulderRight,handRight,true);
			plotSine(spineBase, spine,false);
			plotSine(spineBase,spine,true);
			plotSine(footRight,spineBase,false);
			plotSine(footRight,spineBase,true);
			plotSine(footLeft,spineBase,false);
			plotSine(footLeft,spineBase,true);
			spiralHead(head);

		}
	}

	/**
	 * Draws an ellipse in the x,y position of the vector (it ignores z).
	 * Will do nothing is vec is null.  This is handy because get joint 
	 * will return null if the joint isn't tracked. 
	 * @param vec
	 */
	public void spiralHead(PVector head){
		if(spiralCount==200){
			neg=true;
		}
		else if(spiralCount==0){
			neg=false;
			spiralCount+=1;
		}
		if(neg==true){
			spiralCount-=1;
		}
		else{
			spiralCount+=1;
		}
		
		translate(head.x,head.y);
		for(int i=0;i<spiralCount;i++){
			rotate((float) 0.1);
			fill(i,i+50,i+45);
			noStroke();
			ellipse((float)(i*0.001),(float)0,(float)0.02,(float)0.02);
		}
	}

	public float gradient(PVector vec1, PVector vec2){
		float grad=0;
		if(vec1!=null&&vec2!=null){
			grad=((float) vec1.y-vec2.y)/((float) vec1.x-vec2.x);
			
		}
		return grad;

	}

	public void plotSine(PVector vec1, PVector vec2,boolean inv){
		if(colSwatch==255){
			System.out.println("yes");
			colSwatch=0;
		}
		colSwatch+=0.5;
		theta+=0.02;
		float[] yS=new float[16];
		if(vec1!=null && vec2!=null){
			float diam=dist(vec1.x,vec1.y,vec2.x,vec2.y)/16;
			float rotateAngle=atan(gradient(vec1,vec2));
			if(vec1.x>vec2.x && gradient(vec1,vec2)<0){
				rotateAngle=PI-abs(rotateAngle);
			}
//			System.out.println(gradient(vec1,vec2));
//			System.out.println("RA"+rotateAngle);
//			System.out.println("x1 "+vec1.x+"x2 "+vec2.x+"sub "+(vec1.x-vec2.x));
//			System.out.println("y1 "+vec1.y+"y2 "+vec2.y+"sub "+(vec1.y-vec2.y));
			if (vec1.x==vec2.x && vec1.y>vec2.y){
				rotateAngle=PI/2;
			}
			float period=dist(vec1.x,vec1.y,vec2.x,vec2.y)/2;
			float dx=(TWO_PI/period)*diam;
			float x=theta;
			
			for(int i=0;i<yS.length;i++){
				yS[i]=(sin(x)/100)*5;
				if(inv){
					yS[i]=-yS[i];
				}
				
				x+=dx;
				noStroke();
				fill((int)(colSwatch+(i*0.5)));
				pushMatrix();
				translate(vec1.x,vec1.y);
				rotate(rotateAngle);
				ellipse(i*diam,yS[i],diam,diam);
				popMatrix();
			}
			
		}
		
		
	}
	public PVector midPt(PVector vec1, PVector vec2){
		float X=(vec1.x+vec2.x)/2;
		float Y=(vec1.y+vec2.y)/2;
		return new PVector(X,Y); 
		
	}
	public void drawIfValid(PVector vec) {
		if(vec != null) {
			ellipse(vec.x, vec.y, .1f,.1f);
		}

	}

	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(KinectRenderDemo.class.getName());
	}

}
