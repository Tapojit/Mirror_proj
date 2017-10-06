package edu.mtholyoke.cs.comsc243.kinectUDP;

import java.util.concurrent.BlockingQueue;

public interface MsgProvider {
	public void start();
	public void stop();
	public boolean isRunning();
	public BlockingQueue<Msg> getMsgQueue();

}
