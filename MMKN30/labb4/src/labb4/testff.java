package labb4;

import lejos.geom.Line;

public class testff {

	public static void main(String[] args) {
		
		Line [] lines = new Line[20];
		
		//SQ1
		int j = -30;
		int k = 25;
		for(int i = 0 ; i < 3; i++){			
		lines [0] = new Line(j, k, j+10, k);
		lines [1] = new Line(j+10, k, j+10, k-10);
		lines [2] = new Line(j+10, k-10, j, k-10);
		lines [4] = new Line(j, k-10, j, k);
		}
		System.out.println(lines[0]);
		System.out.println(lines[1]);
		System.out.println(lines[2]);
		System.out.println(lines[3]);
		
		
	}

}
