import java.util.ArrayList;

public class Player {
	private String name;
	private String level;
	private ArrayList<Card> deck;

	public Player() {
		// Criar jogador com parâmetros e baralho aleatório
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
}