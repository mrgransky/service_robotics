import java.io.DataInput;

import lejos.nxt.*;

public class DistansHandler extends Thread {
	
	public UltrasonicSensor us;
	public int distance;
	public DataExchange dataExchange;
	
	public DistansHandler(DataExchange de){
		us = new UltrasonicSensor(SensorPort.S3);
		dataExchange = de;
	}
	
	public void run(){
		
		while(true){
			dataExchange.obstacleDistance = us.getDistance();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		}
		
	}

}
