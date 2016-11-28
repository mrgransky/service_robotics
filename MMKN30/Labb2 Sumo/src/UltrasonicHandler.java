
import java.util.ArrayList;

import lejos.nxt.*;




public class UltrasonicHandler extends Thread{
 
	UltrasonicSensor usSensor;
	DataExchange dataExchange;
	int treshold;
	
	
	public UltrasonicHandler(DataExchange de) {
	
		usSensor = new UltrasonicSensor(SensorPort.S2);
		dataExchange = de;
		treshold = 290;
		
	}
	
	public void run(){
		while(true){			
		int theDistans = usSensor.getDistance();
		LCD.drawString("US DIST: "+ String.valueOf(theDistans), 0, 6);
		if (theDistans != 255 && theDistans < 600){
			dataExchange.obstacle = true;
		}else{
			dataExchange.obstacle = false;
		}
	
		}
		
	}

	
}
