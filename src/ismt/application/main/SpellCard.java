package ismt.application.main;
import javax.json.JsonArray;

public class SpellCard extends Card {
	private String radius;
	private String duration;
    
	public SpellCard() {
		// TODO Auto-generated constructor stub
	}

	public SpellCard(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public SpellCard(String name, Rarity rarity, String cost, JsonArray levels, String type) {
		super(name, rarity, cost, levels, type);
		// TODO Auto-generated constructor stub
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

}
