import java.io.DataInput;

import lejos.nxt.*;


public class LineCar {

	public static void main(String[] args) {
		
		DataExchange exchange = new DataExchange();	
		MotorHandler mh = new MotorHandler(exchange);
		LightSensorHandler lh = new LightSensorHandler(exchange);
		DistansHandler dh = new DistansHandler(exchange);	
		mh.start();
		lh.start();
		dh.start();
		
			
		
		}
	

}
