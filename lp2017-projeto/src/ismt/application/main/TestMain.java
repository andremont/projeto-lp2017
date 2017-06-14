package ismt.application.main;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public abstract class TestMain extends Application implements GameInterface{

	public static void main(String[] args) {
		
		ArrayList<Card> shopCards = new ArrayList<Card>();
		ArrayList<Card> temp = new ArrayList<Card>();
		ArrayList<Card> playerCards = new ArrayList<Card>();
		Utils util = new Utils();
		Player player = Utils.GetUser("root");
		
		playerCards = player.getAllCards();
		
		randomCardGenerator(shopCards,temp, util);
		buyCard(temp, playerCards, player);
		upgradeCard(player,playerCards);
		
		System.out.println(".---------------------------------------.");
		for(Card elem: playerCards){
			System.out.println(elem);
		}
		
	}

	private static void upgradeCard(Player player,ArrayList<Card> playerCards) {
		System.out.println(".---------------------------------------.");
		Utils.printAllCards(player.getAllCards());
		int quantity;
		
		Card selectedCard = playerCards.get(5);
		
		for(int i = 0; i < playerCards.size(); i++){
			if(selectedCard==playerCards.get(i)){
				quantity = playerCards.get(i).getQuantity();
				if(playerCards.get(i).getRarity() == Rarity.Common){
					if(playerCards.get(i).getLevel() == 1){
						if(quantity>=2){
							player.getAllCards().get(i).setLevel(2);
							player.getAllCards().get(i).setQuantity(quantity-2);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 2){
						if(quantity>=4){
							player.getAllCards().get(i).setLevel(3);
							player.getAllCards().get(i).setQuantity(quantity-4);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 3){
						if(quantity>=10){
							player.getAllCards().get(i).setLevel(4);
							player.getAllCards().get(i).setQuantity(quantity-10);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 4){
						if(quantity>=20){
							player.getAllCards().get(i).setLevel(5);
							player.getAllCards().get(i).setQuantity(quantity-20);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 5){
						if(quantity>=50){
							player.getAllCards().get(i).setLevel(6);
							player.getAllCards().get(i).setQuantity(quantity-50);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 6){
						if(quantity>=100){
							player.getAllCards().get(i).setLevel(7);
							player.getAllCards().get(i).setQuantity(quantity-100);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 7){
						if(quantity>=200){
							player.getAllCards().get(i).setLevel(8);
							player.getAllCards().get(i).setQuantity(quantity-200);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 8){
						if(quantity>=400){
							player.getAllCards().get(i).setLevel(9);
							player.getAllCards().get(i).setQuantity(quantity-400);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 9){
						if(quantity>=800){
							player.getAllCards().get(i).setLevel(10);
							player.getAllCards().get(i).setQuantity(quantity-800);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 10){
						if(quantity>=1000){
							player.getAllCards().get(i).setLevel(11);
							player.getAllCards().get(i).setQuantity(quantity-1000);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 11){
						if(quantity>=2000){
							player.getAllCards().get(i).setLevel(12);
							player.getAllCards().get(i).setQuantity(quantity-2000);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 12){
						if(quantity>=5000){
							player.getAllCards().get(i).setLevel(13);
							player.getAllCards().get(i).setQuantity(quantity-5000);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
				}
				else if(playerCards.get(i).getRarity() == Rarity.Rare){
					if(playerCards.get(i).getLevel() == 1){
						if(quantity>=2){
							player.getAllCards().get(i).setLevel(2);
							player.getAllCards().get(i).setQuantity(quantity-2);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 2){
						if(quantity>=4){
							player.getAllCards().get(i).setLevel(3);
							player.getAllCards().get(i).setQuantity(quantity-4);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 3){
						if(quantity>=10){
							player.getAllCards().get(i).setLevel(4);
							player.getAllCards().get(i).setQuantity(quantity-10);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 4){
						if(quantity>=20){
							player.getAllCards().get(i).setLevel(5);
							player.getAllCards().get(i).setQuantity(quantity-20);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 5){
						if(quantity>=50){
							player.getAllCards().get(i).setLevel(6);
							player.getAllCards().get(i).setQuantity(quantity-50);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 6){
						if(quantity>=100){
							player.getAllCards().get(i).setLevel(7);
							player.getAllCards().get(i).setQuantity(quantity-100);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 7){
						if(quantity>=200){
							player.getAllCards().get(i).setLevel(8);
							player.getAllCards().get(i).setQuantity(quantity-200);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 8){
						if(quantity>=400){
							player.getAllCards().get(i).setLevel(9);
							player.getAllCards().get(i).setQuantity(quantity-400);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 9){
						if(quantity>=800){
							player.getAllCards().get(i).setLevel(10);
							player.getAllCards().get(i).setQuantity(quantity-800);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 10){
						if(quantity>=1000){
							player.getAllCards().get(i).setLevel(11);
							player.getAllCards().get(i).setQuantity(quantity-1000);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
				}
				else if(playerCards.get(i).getRarity() == Rarity.Epic){
					if(playerCards.get(i).getLevel() == 1){
						if(quantity>=2){
							player.getAllCards().get(i).setLevel(2);
							player.getAllCards().get(i).setQuantity(quantity-2);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 2){
						if(quantity>=4){
							player.getAllCards().get(i).setLevel(3);
							player.getAllCards().get(i).setQuantity(quantity-4);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 3){
						if(quantity>=10){
							player.getAllCards().get(i).setLevel(4);
							player.getAllCards().get(i).setQuantity(quantity-10);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 4){
						if(quantity>=20){
							player.getAllCards().get(i).setLevel(5);
							player.getAllCards().get(i).setQuantity(quantity-20);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 5){
						if(quantity>=50){
							player.getAllCards().get(i).setLevel(6);
							player.getAllCards().get(i).setQuantity(quantity-50);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 6){
						if(quantity>=100){
							player.getAllCards().get(i).setLevel(7);
							player.getAllCards().get(i).setQuantity(quantity-100);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 7){
						if(quantity>=200){
							player.getAllCards().get(i).setLevel(8);
							player.getAllCards().get(i).setQuantity(quantity-200);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
				}
				else if(playerCards.get(i).getRarity() == Rarity.Legendary){
					if(playerCards.get(i).getLevel() == 1){
						if(quantity>=2){
							player.getAllCards().get(i).setLevel(2);
							player.getAllCards().get(i).setQuantity(quantity-2);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 2){
						if(quantity>=4){
							player.getAllCards().get(i).setLevel(3);
							player.getAllCards().get(i).setQuantity(quantity-4);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 3){
						if(quantity>=10){
							player.getAllCards().get(i).setLevel(4);
							player.getAllCards().get(i).setQuantity(quantity-10);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
					else if(playerCards.get(i).getLevel() == 4){
						if(quantity>=20){
							player.getAllCards().get(i).setLevel(5);
							player.getAllCards().get(i).setQuantity(quantity-20);
						}else
							System.out.println("You dont have sufficient cards to upgrade!");
					}
				}
			}
				
		}
	}

	private static void buyCard(ArrayList<Card> temp,ArrayList<Card> playerCards, Player player) {
		int commonCard = 2;
		int rareCard = 20;
		int epicCard = 1000;
		int legendaryCard = 10000;
		int contador;
		boolean bo = false;
		
		Scanner scan = new Scanner(System.in);
		contador = scan.nextInt();
		Card selectedCard = temp.get(contador);
		
		if(selectedCard.getRarity() == Rarity.Common && player.getMoney() >= selectedCard.getCoinCost()){
			selectedCard.setCoinCost(commonCard);
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase()))
					bo = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
			}
			if(!bo)
				player.getAllCards().add(selectedCard);
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(commonCard =commonCard+2);
			System.out.println(player.getMoney());
		}
		else if(selectedCard.getRarity() == Rarity.Rare && player.getMoney() >= selectedCard.getCoinCost()){
			selectedCard.setCoinCost(rareCard);
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase()))
					bo = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
			}
			if(!bo)
				player.getAllCards().add(selectedCard);
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(rareCard =rareCard+32);
			System.out.println(player.getMoney());
		
		}
		else if(selectedCard.getRarity() == Rarity.Epic && player.getMoney() >= selectedCard.getCoinCost()){
			selectedCard.setCoinCost(epicCard);
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase()))
					bo = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
			}
			if(!bo)
				player.getAllCards().add(selectedCard);
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(epicCard =epicCard*2);
			System.out.println(player.getMoney());
		}
		else if(selectedCard.getRarity() == Rarity.Legendary && player.getMoney() >= selectedCard.getCoinCost()){
			selectedCard.setCoinCost(legendaryCard);
			for(int cont = 0; cont < playerCards.size(); cont++){
				if(playerCards.get(cont).getName().toLowerCase().equals(selectedCard.getName().toLowerCase()))
					bo = true;
					player.getAllCards().get(contador).setQuantity(player.getAllCards().get(contador).getQuantity()+1);
			}
			if(!bo)
				player.getAllCards().add(selectedCard);
			player.setMoney(player.getMoney()-selectedCard.getCoinCost());
			selectedCard.setCoinCost(legendaryCard =legendaryCard*2);
			System.out.println(player.getMoney());
		}else if(player.getMoney() < selectedCard.getCoinCost())
			System.out.println("Insufficient money!");
		
	}
	
	private static void randomCardGenerator(ArrayList<Card> shopCards,ArrayList<Card> temp, Utils util) {
		Random randomGenerator;
		try {
			shopCards = util.getAllCards();
			//Utils.printAllCards(shopCards);
			System.out.println("---------------------------------------------");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		randomGenerator = new Random();
		
		
		for(int i = 0; i < 6 ;i++){
			int index = randomGenerator.nextInt(shopCards.size());
			Card tempCard = shopCards.get(index);
			temp.add(tempCard);
			System.out.println(tempCard.getName() + " | Cost:" + tempCard.getCost() + " | rarity: " + tempCard.getRarity());
		}
		
	}
	
	
	
	
	
	
}
