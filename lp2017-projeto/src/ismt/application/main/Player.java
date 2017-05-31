package ismt.application.main;
import java.util.ArrayList;

public class Player {
	private String name;
	private String password;
	private String level;
	private String points;
	private int money;
	private ArrayList<Card> deck;
	private ArrayList<Card> allCards;
	private ArrayList<Tower> towers;
	
	public Player() {
		
	}
	
	public Player(String name, String level, ArrayList<Card> deck, ArrayList<Tower> towers) {
		//TODO Criar jogador com parâmetros e baralho aleatório
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	public ArrayList<Card> getAllCards() {
		return allCards;
	}

	public void setAllCards(ArrayList<Card> allCards) {
		this.allCards = allCards;
	}
	
	public ArrayList<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}

}