import javax.json.JsonArray;

public class TroopCard extends Card{
	private int hitPoints;
	private int damagePoints;
	private int damagePerSecond;

	public TroopCard(String name, Rarity rarity, String cost, JsonArray levels, String type) {
		super(name, rarity, cost, levels, type);
		// TODO Auto-generated constructor stub
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