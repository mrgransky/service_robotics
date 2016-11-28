import lejos.nxt.*;


public class MotorHandler extends Thread {
	
	public DataExchange dataExchange;
	public int currentState;
	public int speed;
	public boolean leftExtra;
	public boolean rightExtra;
	
	public MotorHandler(DataExchange Exchange){
		this.dataExchange = Exchange;
	}
	public void run()
	{	
		speed = 260;
		forward();
		while(true){
			
			if(dataExchange.obstacleDistance < 11){
				LCD.drawInt(dataExchange.obstacleDistance, 4, 2);
				obstacle();
				Sound.buzz();
			}
			else if(dataExchange.leftIsBlack && dataExchange. middleIsBlack){
				turnLeftOneStill();
			}
			else if(dataExchange.leftIsBlack){
				turnLeft();
			
			}
			else if(dataExchange.rightIsBlack && dataExchange.middleIsBlack){
				turnRightOneStill();
			}
			
			else if(dataExchange.rightIsBlack){
				 turnRight();	
			}
			else{
				forward();
			}
			
			Thread.yield();
		}
	}
	
	public void forward()
	{
		Motor.A.backward();
		Motor.B.backward();
		Motor.B.setSpeed(speed);
		Motor.A.setSpeed(speed);
		dataExchange.currentState = 0;
	}
	
	
	public void turnLeftOneStill(){
		{			
		Motor.B.stop();
		Motor.A.setSpeed(200);
		Motor.A.backward();
		}
		
	}
	public void turnRightOneStill(){			
		Motor.A.stop();
		Motor.B.setSpeed(200);
		Motor.B.backward();
		
	}
	
	public void turnLeft()
	{
		{
			Motor.B.setSpeed(500);
			Motor.B.rotate(15);
			Motor.B.setSpeed(200);
		}
		
	}
	
	public void turnRight()
	{
		Motor.A.setSpeed(500);
		Motor.A.rotate(15);
		Motor.A.setSpeed(200);
	}
	
	public void obstacle() {
		Motor.A.setSpeed(150);
		Motor.B.setSpeed(150);
		Motor.A.rotate(-120, true);
		Motor.B.rotate(120, false);		
		Motor.A.setSpeed(210);
		Motor.B.setSpeed(430);
		Motor.A.backward();
		Motor.B.backward();
		while(!dataExchange.leftIsBlack){
			Thread.yield();
		}
		turnLeft();
		
	}
	
	public void compensateLeft(){
		leftExtra = true;
	}
	public void compensateRight(){		
		rightExtra = true;
	}
	
}