package labb4;
import java.io.DataInput;

import lejos.nxt.*;

public class DistansHandler extends Thread {
	
	
	//TYPE 1 = FRONT, TYPE 2 = SIDE
	public UltrasonicSensor us;
	public int distance;
	public DataExchange dataExchange;
	int type;
	public DistansHandler(DataExchange de, int theType, SensorPort port){
		type = theType;
		us = new UltrasonicSensor(port);
		dataExchange = de;
	}
	
	public void run(){
		while(true){
			if(type == 1){			
			dataExchange.obstacleDistanceFront = us.getDistance();
			} else if(type == 2){	
			dataExchange.obstacleDistanceRight = us.getDistance();
			} else if(type == 3){
		    dataExchange.obstacleDistanceLeft = us.getDistance();
			}
			
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		}
		
	}

}
