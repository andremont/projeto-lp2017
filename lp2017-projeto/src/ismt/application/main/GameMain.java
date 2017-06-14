package ismt.application.main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


public class GameMain {
	public final static int MANA_MAX = 10;
	private Timer timer;
	private int tmpJogo;
	private int mana;
	private int manaTime;
	private ArrayList<Card> deck;
	private Card nextCard;
	private Card[] cardsInGame;
	Player player = new Player();

	
	public GameMain(){
		tmpJogo = 180;
		mana = 5;
		manaTime = 6; //aumenta uma mana a cada 6 segundos
	}
	
	public void timer() {
		tmpJogo --; 

	    int minutes = tmpJogo / 60;
	    int seconds = tmpJogo % 60;
	    System.out.println(minutes + ":" + seconds);
	    
	    if (tmpJogo==0){
	    	System.out.println("Jogo acabou");
	    	timer.cancel();	   
	    }
	    
	}
	
	public void manaRegen() {

		if (mana == MANA_MAX){
			return;
		}
		
		manaTime--;
		
		if (manaTime==0){
			mana++;
			manaTime = 6;
			System.out.println("Mana = " + mana);
			if (mana == MANA_MAX){
				System.out.println("Mana máxima");
			}
		}	
		
	}
	

	private void simulatePlay() {
		
		if (Math.random()>0.8){
			System.out.println("Qual carta a jogar de 1 a 4?");
			Scanner sc = new Scanner(System.in);
			int i = sc.nextInt() - 1;
			playCard(i);
		}
		
	}	

	public void playCard(int cardIndex){
		
		Card played = cardsInGame[cardIndex];
		
		//temos mana ?
		int neededMana = Integer.parseInt(played.getCost());
		
		if (mana<neededMana){
			System.out.println("Não possui Mana suficiente");
			return;
		}
		
		mana -= neededMana;		
		cardsInGame[cardIndex] = deck.remove(0);		
		deck.add(played);		
		System.out.println("Carta jogada: " + played.getName());				
		nextCard = deck.get(0); 
		
		showCards();
		
	}

	public void start() {
		
		player = Utils.GetUser("root");
		deck = player.getDeck();
		
		timer = new Timer();
	
		timer.schedule(new TimerTask() {
										@Override
										public void run() {
											timer();
											manaRegen();
											simulatePlay();
										} 
										}, 1000, 1000);
		
		cardsInGame = new Card[4];
		for (int i = 0; i < 4; i++) {
			cardsInGame[i] = deck.remove(0);			
		}	
		
		nextCard = deck.get(0); 	
		
		
		showCards();
	}

	private void showCards() {
		System.out.println("Cartas em jogo: ");
		for (int i = 0; i < cardsInGame.length; i++) {
			System.out.println((i+1)+"-" + cardsInGame[i].getName() + "(" + cardsInGame[i].getCost() + ")");
		}
		System.out.println("");
		System.out.println("Next card = " + nextCard.getName() + "(" + nextCard.getCost()+")");
	}
	
}
