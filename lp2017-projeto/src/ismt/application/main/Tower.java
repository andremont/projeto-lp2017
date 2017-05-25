package ismt.application.main;

public class Tower{
	private String name;
	private int level;
	private int hitPoints;
	private int damagePoints;
	private int damagePerSecond;

	public Tower() {
	}
	
	public Tower(String name, int level, int hitPoints, int damagePoints, int damagePerSecond) {
		this.name = name;
		this.level = level;
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