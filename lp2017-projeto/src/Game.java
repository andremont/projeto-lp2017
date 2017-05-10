import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Game {
	long gameID;
	ArrayList<Player> players;
	Date time; 
	String result = "Draw";
	
	public Game() {
	
	}
	
	public Game(ArrayList<Player> players) {
		this.time = new Date();
		this.gameID = new Random().nextInt() + this.time.getTime();
	}
	
	public void setResult(String result)
	{
		this.result =  result;
	}
	
	public String getResult()
	{
		return this.result;
	}

}
