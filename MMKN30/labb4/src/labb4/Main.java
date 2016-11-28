package labb4;

import java.io.IOException;

import lejos.geom.Line;
import lejos.geom.Rectangle;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.CompassHTSensor;
import lejos.nxt.addon.DSwitch;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;
import lejos.util.PilotProps;

public class Main {

	public static void main(String[] args) throws Exception {
		Thread.sleep(5000);
		DataExchange dataExchange = new DataExchange();
		DistansHandler distansHandlerFront = new DistansHandler(dataExchange, 1, SensorPort.S4);
		DistansHandler distansHandlerRight = new DistansHandler(dataExchange, 2, SensorPort.S1);
		distansHandlerRight.start();
		distansHandlerFront.start();
		// INTIATE THE ROBOT

		PilotProps pp = new PilotProps();
		pp.loadPersistentValues();
		float wheelDiameter = Float.parseFloat(pp.getProperty(
				PilotProps.KEY_WHEELDIAMETER, "6"));
		float trackWidth = Float.parseFloat(pp.getProperty(
				PilotProps.KEY_TRACKWIDTH, "18"));
		RegulatedMotor leftMotor = PilotProps.getMotor(pp.getProperty(
				PilotProps.KEY_LEFTMOTOR, "B"));
		RegulatedMotor rightMotor = PilotProps.getMotor(pp.getProperty(
				PilotProps.KEY_RIGHTMOTOR, "C"));
		boolean reverse = Boolean.parseBoolean(pp.getProperty(
				PilotProps.KEY_REVERSE, "false"));
		DifferentialPilot robot = new DifferentialPilot(wheelDiameter,
				trackWidth, leftMotor, rightMotor, reverse);
		PoseProvider posep = new OdometryPoseProvider(robot);

		// START COLLECT OBSTACLES
		ObstacleCreator oc = new ObstacleCreator(dataExchange, posep);
		oc.start();

		// WAYPOINT
		lejos.geom.Rectangle bounds = new Rectangle(-30, -30, 240, 240);
		Line[] lines = new Line[1];
		lines[0] = new Line(100, 100, 101, 100);

		LineMap myMap = new LineMap(lines, bounds);

		MotorHandler mh = new MotorHandler(robot, posep, myMap);
		System.out.println("LINES CREATED");

		Waypoint A = new Waypoint(-20, -20);
		Waypoint B = new Waypoint(-20, 180);
		Waypoint C = new Waypoint(180, 180);
		Waypoint D = new Waypoint(180, -10);

		Waypoint M1 = new Waypoint(70, 150);
		Waypoint M2 = new Waypoint(160, 90);
		Waypoint M3 = new Waypoint(0, 0);
		Waypoint fake = new Waypoint(160, 0);
		// Use a regular grid of node points. Grid space = 20. Clearance = 15:

		// SET INITAL POSE
		posep.setPose(new Pose(0, 0, 0));
		
		
//		//FIX COMPASS AND INITIATE POSITION CORRECTOR
//		CompassHTSensor compass = new CompassHTSensor(SensorPort.S2);
//		compass.resetCartesianZero();
//		
//		System.out.println("COMPASS CREATED");
//		System.out.println((int) compass.getDegreesCartesian());
		
		
		
		mh.goToNoMap(A);
		
		System.out.println("AT POSITION A");
		
		//positionCorrector.correctposition();
		
		
		dataExchange.currentPath = 1;
		mh.goToNoMap(B);
		//positionCorrector.correctposition();
		
		dataExchange.currentPath = 2;
		mh.goToNoMap(C);
		//positionCorrector.correctposition();
		
		dataExchange.currentPath = 3;
		mh.goToNoMap(D);
		//positionCorrector.correctposition();
		
		dataExchange.currentPath = 4;
		mh.goToNoMap(A);
		//positionCorrector.correctposition();
		
		Line[] line2 = new Line[dataExchange.lineArray.size()];

		for (int i = 0; i < dataExchange.lineArray.size(); i++) {
			line2[i] = dataExchange.lineArray.get(i);
		}
		
		System.out.println("CREATING NEW MAP");
		LineMap map2 = new LineMap(line2, bounds);

		MotorHandler mh2 = new MotorHandler(robot, posep, map2);

		System.out.println("FINNISHED CREATING MH2 AND LINE2");
		
		
		System.out.println("GOING rightMiddle");
		mh2.goToWayPoint(M1);
		//positionCorrector.correctposition();
		
		System.out.println("GOING B");
		mh2.goToWayPoint(M2);
		//positionCorrector.correctposition();
		
		mh2.goToNoMap(fake);
		mh2.goToNoMap(M3);
	}

}
