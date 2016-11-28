package labb4;

import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Pose;

public class PositionFixer {


	public PositionFixer(){
	}
	
	public Pose adjustPose(Pose oldPose){
		int x = (int) oldPose.getX();
		int y = (int) oldPose.getY();
		int heading = (int) oldPose.getHeading();
		
		if(x <= 20){
			x = 21;
		}
		
		if(x >= 180){
			x = 179;
		}
		
		if(y >= 180){
			y = 179;
		}
		
		if(y <= -20){
			y = -19;
		}
		heading = 0;
		
		return new Pose(x, y, heading);
	}
	
	public void turnToStartPosition(DifferentialPilot robot, PoseProvider posep){
		while(posep.getPose().getHeading() > 5 || posep.getPose().getHeading() < -5 ){
			robot.rotate(9);
		}
	}
}
