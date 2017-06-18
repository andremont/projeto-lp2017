package ismt.application.main;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameMain {
	public final static int MANA_MAX = 10;
	private Timer timer;
	private int gameTime;
	private int mana;
	private int manaTime;
	private ArrayList<Card> deck;
	private Card nextCard;
	private Card[] cardsInGame;
	
	Player player = new Player();
	Random random = new Random();

	public GameMain(){
		gameTime = 180;
		mana = 5;
		manaTime = 6; //aumenta uma mana a cada 6 segundos
	}
	
	public void timeCountdown() {
		gameTime --; 

	    int minutes = gameTime / 60;
	    int seconds = gameTime % 60;
	    System.out.println(minutes + ":" + seconds);
	    
	    if (gameTime==0){
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
			int rd = random.nextInt(3);
			playCard(rd);
		}
		
	}	

	public void playCard(int cardIndex){
		
		Card played = cardsInGame[cardIndex];
		
		//temos mana ?
		int neededMana = Integer.parseInt(played.getCost());
		
		if (mana<neededMana){
			System.out.println("Não possui Mana suficiente para " + played.getName());
			return;
		}
		
		mana -= neededMana;		
		cardsInGame[cardIndex] = deck.remove(0);		
		deck.add(played); //adiciona a carta removida novamente ao deck		
		System.out.println("Carta jogada: " + played.getName());	
		
		int rd = random.nextInt(3);
		nextCard = deck.get(rd); 
		
		showCards();
		
	}

	public void start() {
		
		deck = Main.getPlayerLogged().getDeck();
		
		timer = new Timer();
	
		timer.schedule(new TimerTask() {
										@Override
										public void run() {
											timeCountdown();
											manaRegen();
											simulatePlay();
										} 
										}, 1000, 1000);
		
		cardsInGame = new Card[4];
		int rd = random.nextInt(3);
		for (int i = 0; i < 4; i++) {
			cardsInGame[i] = deck.remove(rd); //remove as cartas no deck que estao em jogo
		}	
		
		nextCard = deck.get(0); 	
		
	
		showCards();
	}

	private void showCards() {
		System.out.println("");
		System.out.println("Cartas em jogo: ");
		for (int i = 0; i < cardsInGame.length; i++) {
			System.out.println((i+1)+"-" + cardsInGame[i].getName() + "(" + cardsInGame[i].getCost() + ")");
		}
		System.out.println("");
		System.out.println("Next card = " + nextCard.getName() + "(" + nextCard.getCost()+")");
	}
	
}
