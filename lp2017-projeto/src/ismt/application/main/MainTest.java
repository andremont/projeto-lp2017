package ismt.application.main;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {
	
	
	ArrayList<Card> shopCards = new ArrayList<Card>();

	@BeforeClass
	public static void setUp() throws Exception {
		
		
	}

	@Test
	public void testShopRandomCards() {	
		ArrayList<Card> temp = new ArrayList<Card>();
		assertNotNull(" Supostamente n null xD", temp);
	}

	@Test
	public void testBuyCard() {
		Rarity rarity = Rarity.Epic;
		Card card = new Card("Prince");
		Card card2 = new Card("Golem");
		card.setRarity(rarity);
		card2.setRarity(rarity);
		Player player = new Player();
		ArrayList<Card> cartas = new ArrayList<Card>();
		ArrayList<Card> cartasPlayer = new ArrayList<Card>();
		cartas.add(card);
		cartasPlayer.add(card2);
		player.setMoney(1500);
		int playermoney = 1500;
		
		int money = Main.buyCard(cartas, cartasPlayer, player, 0);
		int temp=0;
		assertEquals(money, playermoney);
		
		
		
			
		
	}

}
