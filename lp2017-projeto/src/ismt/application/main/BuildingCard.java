package ismt.application.main;
import javax.json.JsonArray;

public class BuildingCard extends Card {
	private String deploy_time;
	private String lifetime;
	
	public BuildingCard() {
		// TODO Auto-generated constructor stub
	}

	public BuildingCard(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public BuildingCard(String name, Rarity rarity, String cost, JsonArray levels, String type, String image) {
		super(name, rarity, cost, levels, type, image);
		// TODO Auto-generated constructor stub
	}

	public String getDeploy_time() {
		return deploy_time;
	}

	public void setDeploy_time(String deploy_time) {
		this.deploy_time = deploy_time;
	}

	public String getLifetime() {
		return lifetime;
	}

	public void setLifetime(String lifetime) {
		this.lifetime = lifetime;
	}

}
