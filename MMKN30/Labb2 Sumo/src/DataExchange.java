
import java.util.ArrayList;


public class DataExchange {
	
	public int leftTacho;
	public boolean leftIsBlack;
	public boolean rightIsBlack;
	public boolean obstacle;
	public boolean isAtCorner;
	public int state;
	//1 = ATTACK
	//2 = SEEK
	//3 = BlackLeft
	//4 = BlackRight
	//5 = Corner
	
	public ArrayList<String> lsList;
	public ArrayList<String> irList;
	public int[] irValues;
	public boolean foundTarget;

}


