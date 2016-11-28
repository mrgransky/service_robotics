import lejos.nxt.*;


public class MotorHandler {

	NXTRegulatedMotor motorLeft;
	NXTRegulatedMotor motorMiddle;
	NXTRegulatedMotor motorRight;
	
	public MotorHandler(){
		motorLeft = Motor.C;
		motorMiddle = Motor.B;
		motorRight = Motor.A;
		motorMiddle.setSpeed(3000);
		motorMiddle.forward();
	}
	public void forward() {
		doNormalSpeed();
		motorRight.forward();
		motorLeft.forward();

	}
	
	public void startMiddle(){
		motorMiddle.setSpeed(2000);
		motorMiddle.forward();
	}
		
	public void attack() {
		//motorMiddle.forward();
		motorLeft.setSpeed(2000);
		motorRight.setSpeed(2000);
		motorRight.forward();
		motorLeft.forward();
		Thread.yield();
		}
	
	public void backward() {
		doNormalSpeed();
		motorLeft.backward();
		motorRight.backward();
		
//		motorLeft.resetTachoCount();
//		doNormalSpeed();
//		while(motorLeft.getTachoCount() > -350)	
//		{
//			motorLeft.backward();
//			motorRight.backward();
//			Thread.yield();
//		}
//		motorLeft.resetTachoCount();
//		while(motorLeft.getTachoCount() > -600){
//			motorLeft.backward();
//			motorRight.forward();
//			Thread.yield();
//		}
		
	}
	public void resetTacoCount() {
		motorLeft.resetTachoCount();
		motorRight.resetTachoCount();
		
	}
	public void backwardDistance(int distance) {
		resetTacoCount();
		doNormalSpeed();
		while(motorLeft.getTachoCount() > distance ){
			motorLeft.backward();
			motorRight.backward();
		}
	}

	public void turnRight() {
		motorLeft.setSpeed(400);
		motorLeft.forward();
		motorRight.setSpeed(100);
		motorRight.backward();;
	}
	
	public void turnLeft(){
		motorRight.setSpeed(400);
		motorRight.forward();
		motorLeft.setSpeed(100);
		motorLeft.backward();
	}

	public void doNormalSpeed(){
		motorLeft.setSpeed(500);
		motorRight.setSpeed(500);
	}

		
}
