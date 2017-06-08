package ismt.application.main;

public class CardTroopLevel extends CardLevel{
	private int level;
	private int hitPoints;
	private int damagePoints;
	private int damagePerSecond;
	
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
	
	@Override
	public
	int getCrownTowerDamage() {
		return 0;
	}
	@Override
	void setCrownTowerDamage(int crownTowerDamage) {
		
	}

}
