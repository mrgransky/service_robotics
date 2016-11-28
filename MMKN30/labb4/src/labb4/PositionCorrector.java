package labb4;

import lejos.nxt.addon.CompassHTSensor;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;

public class PositionCorrector {
	
	PoseProvider posep;
	DataExchange de;
	MotorHandler mh;
	public PositionCorrector(PoseProvider poseProvider, DataExchange dataExchange, MotorHandler motorHandler){
		posep = poseProvider;
		de = dataExchange;
		mh = motorHandler;
	}

	public void correctposition(){
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		int x = (int) posep.getPose().getX();
		int y = (int) posep.getPose().getY();
		//LEFT SIDE
		if(x < -30 && de.obstacleDistanceLeft != 255){
			x = -40 + de.obstacleDistanceLeft;		
		}
		//TOP SIDE
		if(y > 150 && de.obstacleDistanceFront != 255){
		 y = 200 - de.obstacleDistanceFront; 
		}
		//RIGHT SIDE
		if(x > 150 && de.obstacleDistanceRight != 255){
		x = 200 - de.obstacleDistanceRight;	
		}
		
		
		//SETTING NEW POSITION TO the CAR;
		
		System.out.println("SETTING THIS X Y VALUES");
		System.out.println(x + " "+ " " + y);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		posep.setPose(new Pose(x, y, posep.getPose().getHeading()));
		
}
	}

