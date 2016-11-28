

import java.util.ArrayList;

import lejos.nxt.*;
import lejos.nxt.addon.IRSeeker;
import lejos.nxt.addon.IRSeekerV2;

public class InfraredHandler extends Thread {

	IRSeekerV2 irSensor;
	DataExchange dataExchange;
	int treshold;
	int[] irvalues;
	public InfraredHandler(DataExchange de){
	irSensor = new IRSeekerV2(SensorPort.S3, IRSeekerV2.Mode.DC);
	dataExchange = de;
	treshold = 65;
	}
	
	public void run(){
		while(true){			
		irvalues = irSensor.getSensorValues();
		dataExchange.irValues = irvalues; 
		int valueLeft = irvalues[1];
		int valueMiddle = irvalues[2];
		int valueRight = irvalues[3];
//		LCD.drawString("IR LEFT: "+ String.valueOf(valueLeft), 0, 3);
//		LCD.drawString("IR MIDDLE: "+ String.valueOf(valueMiddle), 0, 4);
//		LCD.drawString("IR RIGHT: "+ String.valueOf(valueRight), 0, 5);
		if(valueLeft > treshold || valueRight > treshold || valueMiddle > treshold){
			dataExchange.isAtCorner = true;
		} else{
			dataExchange.isAtCorner = false;
		}
		dataExchange.irList = getIrValues();
		Thread.yield();
		}
	}
	
	public ArrayList<String> getIrValues(){
		ArrayList<String> list = new ArrayList<String>();
		list.add(String.valueOf(irSensor.getSensorValue(1)));
		list.add(String.valueOf(irSensor.getSensorValue(2)));
		list.add(String.valueOf(irSensor.getSensorValue(3)));
		return list;
	}
	
}
