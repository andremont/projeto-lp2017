
public class Card {
	private String name;
	private Rarity rarity;
	private int cost;
	private int level;
	private int hitPoints;
	private int damagePoints;
	private int damagePerSecond;

	public Card() {
	}
	
	public Card(String name, Rarity rarity, int cost, int level, int hitPoints, int damagePoints, int damagePerSecond) {
		this.rarity = rarity;
		this.cost = cost;
		this.level = level;
		this.name = name;
		this.hitPoints = hitPoints;
		this.damagePoints = damagePoints;
		this.damagePerSecond = damagePerSecond;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public int getDamagePoints() {
		return damagePoints;
	}

	public void setDamagePoints(int damagePoints) {
		this.damagePoints = damagePoints;
	}

	public int getDamagePerSecond() {
		return damagePerSecond;
	}

	public void setDamagePerSecond(int damagePerSecond) {
		this.damagePerSecond = damagePerSecond;
	}
}