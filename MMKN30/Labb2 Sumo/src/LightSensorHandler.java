

import java.util.ArrayList;

import lejos.nxt.*;

public class LightSensorHandler extends Thread {
	LightSensor lightSensorLeft;
	LightSensor lightSensorRight;
	DataExchange dataExchange;
	int treshold;
	
	public LightSensorHandler(DataExchange DataExchange){
		lightSensorLeft = new LightSensor(SensorPort.S4);
		lightSensorRight = new LightSensor(SensorPort.S1);
		dataExchange = DataExchange;
		treshold = 39;
	}
	
	public void run(){
		while(true){
		int valueLeft = lightSensorLeft.readValue();
		int valueRight = lightSensorRight.readValue();
	//	LCD.drawString("LEFT LIGHT: "+ String.valueOf(valueLeft), 0, 1);
	//	LCD.drawString("RIGHT LIGHT: "+ String.valueOf(valueRight), 0, 2);
		
		if(valueLeft < treshold){
			dataExchange.leftIsBlack = true;
		} else{
			dataExchange.leftIsBlack = false;
		}
		
		if(valueRight < treshold){
			dataExchange.rightIsBlack = true;
		} else{
			dataExchange.rightIsBlack = false;
		}
		dataExchange.lsList = getLightValues();
		
		Thread.yield();
		}
	}
	
	public ArrayList<String> getLightValues(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(String.valueOf(lightSensorLeft.readValue()));
		list.add(String.valueOf(lightSensorRight.readValue()));
		
		return list;
	}
	
}
