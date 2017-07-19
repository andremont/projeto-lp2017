package ismt.application.main;


import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;


public abstract class MainTestes extends Application implements GameInterface {


	public static void main(String[] args) throws IOException {		
		ArrayList<Card> deck = new ArrayList<Card>();
		ArrayList<Player> players = new ArrayList<Player>();


		Stats stats= new Stats();
		Stats stats2= new Stats();

		players = Utils.getAllPlayers();
		deck = Utils.getAllCards();

		for(Player cd : players){
			
			
			
			String playerName=(cd.getName());
			
			System.out.println(playerName);
		}
		





				stats= Utils.GetUserRecords("root");
		//		stats2= Utils.GetUserRecords("admin");
		//
		//		//array decks player
		//		ArrayList<Card> deck1 = Utils.GetUser("root").getDeck();
		//		Player 
		//
		//		//ciclo para ver as cartas
		//		System.out.println("root");
		//		for(Card cd : deck1){
		//			System.out.println(cd.getName());
		//		}
		//
		//		if(stats.getVictories()>stats2.getVictories())
		//		{
		//			System.out.println("Player root vitorias: "+stats.getVictories());	
		//		}else
		//
		//			System.out.println("Player admin Vitorias: "+stats2.getVictories());	
		//
		//
	}	

}

