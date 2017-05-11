package ismt.application.main;
import java.util.ArrayList;

public class Player {
	private String name;
	private String level;
	private ArrayList<Card> deck;
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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public ArrayList<Card> getDeck() {
		return deck;
	}

	public void setDeck(ArrayList<Card> deck) {
		this.deck = deck;
	}
	
	public ArrayList<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(ArrayList<Tower> towers) {
		this.towers = towers;
	}
}