import javax.json.JsonArray;

public class Card {
	private String name;
	private Rarity rarity;
	private String cost;
	private int level;
	private JsonArray levels;
	private String type;

	public Card() {
	}
	
	public Card(String name) {
		this.name = name;
	}
	
	public Card(String name, Rarity rarity, String cost, JsonArray levels, String type) {
		this.rarity = rarity;
		this.cost = cost;
		this.level = 1;
		this.levels = levels;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public JsonArray getLevels() {
		return this.levels;
	}
	
	public void setLevels(JsonArray levels) {
		this.levels = levels;
	}

	public int getLevel() {
		return this.level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}