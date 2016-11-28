import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;


public class LightSensorHandler extends Thread {
	
	public LightSensor leftLightSensor;
	public LightSensor middleLightSensor;
	public LightSensor rightLightSensor;
	public DataExchange dataExchange;
	public int middleValue;
	
	public LightSensorHandler(DataExchange Exchange){
		
		leftLightSensor = new LightSensor(SensorPort.S4);
		rightLightSensor = new LightSensor(SensorPort.S1);
		middleLightSensor = new LightSensor(SensorPort.S2);
		dataExchange = Exchange;
	}
	
	public void run()
	{
		leftLightSensor.setFloodlight(true);
		rightLightSensor.setFloodlight(true);	
		int middleValue;
		int leftValue;
		int rightValue;
		while(true){
			leftValue = leftLightSensor.readValue();
			rightValue = rightLightSensor.readValue();
			middleValue = middleLightSensor.readValue();
			dataExchange.lightSensorLeft = leftValue;
			dataExchange.lightSensorRight = rightValue;
			dataExchange.ligtSensorMiddle = middleValue;
			
			LCD.drawInt(leftValue, 0, 1);
			LCD.drawInt(rightValue, 1, 3);
			if(leftValue < 43){
				dataExchange.leftIsBlack = true;
			} else{
			 dataExchange.leftIsBlack = false;
			}
			if(rightValue < 43){
				dataExchange.rightIsBlack = true;
			} else{
			 dataExchange.rightIsBlack = false;
			}
			if(middleValue < 43){
				dataExchange.middleIsBlack = true;	
			} else{
					dataExchange.middleIsBlack = false;		
			}
			
			
			Thread.yield();
		}
	}
	
}

