import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.navigation.RotateMoveController;


public class Behavior extends Thread {

	MotorHandler motorHandler;
	DataExchange dataExchange;
	// 1 = left , 2 = Right
	int wayOfSeek = 1;

	public Behavior(DataExchange de){
		dataExchange = de;
		motorHandler = new MotorHandler();
	}

	public void run(){
		//motorHandler.startMiddle();
		motorHandler.doNormalSpeed();
		while(true){
			if(dataExchange.isAtCorner){
				//ADJUST TACOCOUNT
				//motorHandler.backwardDistance(-500);
				motorHandler.resetTacoCount();
				while(motorHandler.motorLeft.getTachoCount() > -1200){
					motorHandler.backward();
				}
				motorHandler.resetTacoCount();
				while(motorHandler.motorLeft.getTachoCount() < 600 && !dataExchange.leftIsBlack && !dataExchange.rightIsBlack){
					motorHandler.turnRight();
				}
			} 
			else if(dataExchange.leftIsBlack && dataExchange.rightIsBlack && !dataExchange.isAtCorner){
				//GO BACKWARD?
				motorHandler.resetTacoCount();
				while(motorHandler.motorLeft.getTachoCount() > -1200){
					motorHandler.backward();
				}
				motorHandler.resetTacoCount();
				while(motorHandler.motorLeft.getTachoCount() < 600 && !dataExchange.leftIsBlack && !dataExchange.rightIsBlack){
					motorHandler.turnRight();
				}
				//motorHandler.motorMiddle.stop();
			}
			else if(dataExchange.leftIsBlack){
				wayOfSeek = 2;
				motorHandler.turnRight();
				Thread.yield();
				//motorHandler.motorMiddle.stop();
			} 
			else if(dataExchange.rightIsBlack){
				wayOfSeek = 1;
				motorHandler.turnLeft();
				Thread.yield();
			} 
			else if(dataExchange.obstacle){
				motorHandler.attack();
			}else{
				//SEEK
				if(wayOfSeek == 1){					
					seekLeft();
				} 
				else{
					seekRight();	
				}
			}
		}

			
		}
	private void seekRight() {
		motorHandler.resetTacoCount();
		while(motorHandler.motorRight.getTachoCount() < 450 && !dataExchange.obstacle && !dataExchange.leftIsBlack && !dataExchange.rightIsBlack && !dataExchange.isAtCorner){
			motorHandler.turnRight();
			Thread.yield();
		}
		motorHandler.resetTacoCount();
		while(motorHandler.motorRight.getTachoCount() < 1500 && !dataExchange.obstacle && !dataExchange.leftIsBlack && !dataExchange.rightIsBlack && !dataExchange.isAtCorner){					
			motorHandler.forward();
			Thread.yield();
		}
		
	}
	private void seekLeft(){
		motorHandler.resetTacoCount();
		while(motorHandler.motorLeft.getTachoCount() < 450 && !dataExchange.obstacle && !dataExchange.leftIsBlack && !dataExchange.rightIsBlack && !dataExchange.isAtCorner){
			motorHandler.turnLeft();
			yield();
		}
		motorHandler.resetTacoCount();
		while(motorHandler.motorLeft.getTachoCount() < 1500 && !dataExchange.obstacle && !dataExchange.leftIsBlack && !dataExchange.rightIsBlack && !dataExchange.isAtCorner){					
			motorHandler.forward();
			yield();
		}

	}
}
