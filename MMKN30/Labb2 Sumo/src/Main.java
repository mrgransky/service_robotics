
public class Main {

	public static void main(String[] args) {
		DataExchange dataExchange = new DataExchange();
		LightSensorHandler lh = new LightSensorHandler(dataExchange);
		UltrasonicHandler uh = new UltrasonicHandler(dataExchange); 
		InfraredHandler fh = new InfraredHandler(dataExchange);
		Behavior bh = new Behavior(dataExchange);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lh.start();
		uh.start();
		fh.start();
		bh.start();
		
	}

}
