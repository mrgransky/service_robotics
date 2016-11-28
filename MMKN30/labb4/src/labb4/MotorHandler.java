package labb4;
import lejos.nxt.*;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.navigation.DestinationUnreachableException;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Waypoint;
import lejos.robotics.pathfinding.AstarSearchAlgorithm;
import lejos.robotics.pathfinding.FourWayGridMesh;
import lejos.robotics.pathfinding.NodePathFinder;
import lejos.robotics.pathfinding.Path;
import lejos.robotics.pathfinding.PathFinder;


public class MotorHandler {
	
	Navigator nav;
	FourWayGridMesh grid;
	PositionFixer fixer; 
	PathFinder pf;
	PoseProvider posep;
	
	public MotorHandler(DifferentialPilot robo, PoseProvider pose, LineMap lMap){
		nav = new Navigator(robo, pose);
		posep = pose;
		
		//SET GRID
		FourWayGridMesh grid = new FourWayGridMesh(lMap, 20, 9);
		
		// Use A* search:
		AstarSearchAlgorithm alg = new AstarSearchAlgorithm();
		
		// Give the A* search alg and grid to the PathFinder:
		pf = new NodePathFinder(alg, grid);
		
		fixer = new PositionFixer();

		
	}
	
	public void goToWayPoint(Waypoint wp){
		Path path = null;
		Sound.beep();
		try {
			path = pf.findRoute(fixer.adjustPose(posep.getPose()), wp);
		} catch (DestinationUnreachableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Planning path A");
		
		nav.followPath(path);
		nav.waitForStop();
	}
	
	public void goToNoMap(Waypoint wp){
		nav.goTo((float) wp.getX(),(float) wp.getY());
		nav.waitForStop();
	}

}
