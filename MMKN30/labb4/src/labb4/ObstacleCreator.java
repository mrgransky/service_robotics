package labb4;

import java.util.ArrayList;
import java.util.List;

import lejos.geom.Line;
import lejos.robotics.localization.PoseProvider;
import lejos.util.IterableEnumeration;

public class ObstacleCreator extends Thread {

	DataExchange dataExchange;
	PoseProvider posep;
	
	public ObstacleCreator(DataExchange de, PoseProvider pose){
		 dataExchange = de;
		 de.lineArray = new ArrayList<Line>();
		 posep = pose;
	}
	

	public void run(){
		while(true){
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(dataExchange.obstacleDistanceRight < 255){
				if(dataExchange.currentPath == 1){
					int x = (int) (posep.getPose().getX() + dataExchange.obstacleDistanceRight);
					int y = -40;
					addToObstacleList(x, y);
				}
				if(dataExchange.currentPath == 2){
					int x = (int) posep.getPose().getX();
					int y = 180 - dataExchange.obstacleDistanceRight;
					addToObstacleList(x, y);
				}
				if(dataExchange.currentPath == 3){
					int x = 180 - dataExchange.obstacleDistanceRight;
					int y = (int) posep.getPose().getY();
					addToObstacleList(x, y);
				}
				if(dataExchange.currentPath == 4){
					int x = (int) posep.getPose().getX();
					int y = -40 +  dataExchange.obstacleDistanceRight;
					addToObstacleList(x, y);
				}
				
				
			}
			
			
		}
		
	}


	private void addToObstacleList(int x, int y) {
		Line line = new Line(x, y, x+1, y);
		dataExchange.lineArray.add(line);
		dataExchange.obstacleDistanceRight = 255;		
		
	}
}
