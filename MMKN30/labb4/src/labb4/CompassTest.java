package labb4;

import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.CompassHTSensor;

public class CompassTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		CompassHTSensor compass = new CompassHTSensor(SensorPort.S2);
		compass.resetCartesianZero();
		System.out.println("RESET VALUE");
		System.out.println(compass.getDegreesCartesian());
		Sound.beep();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("NEW VALUE");
		System.out.println(compass.getDegreesCartesian());
		Sound.beep();
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
