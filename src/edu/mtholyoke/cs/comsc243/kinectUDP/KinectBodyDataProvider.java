package edu.mtholyoke.cs.comsc243.kinectUDP;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class KinectBodyDataProvider {
	MsgProvider msgProvider;
	KinectBodyData mostRecentData = new KinectBodyData("");
	ArrayList<Msg> dataDrain = new ArrayList<Msg>();


	//read data from file recorded by UDPRecorded
	public KinectBodyDataProvider(String filename, int loopCnt) throws FileNotFoundException, IOException {
		msgProvider = new UDPPlayer(filename, loopCnt);
	}

	//receive data via UDP
	public KinectBodyDataProvider(int port) {
		msgProvider = new UDPReceiver(port);
	}

	public void start() {
		msgProvider.start();
	}
	public void stop() {
		msgProvider.stop();
	}
	public boolean isRunning() {
		return msgProvider.isRunning();
	}

	public KinectBodyData getMostRecentData() {
		msgProvider.getMsgQueue().drainTo(dataDrain);
		if(dataDrain.size() > 0) {
			String jsonStr = new String(dataDrain.get(dataDrain.size()-1).msg);
			mostRecentData = new KinectBodyData(jsonStr);
		}
		return mostRecentData;
	}


}
