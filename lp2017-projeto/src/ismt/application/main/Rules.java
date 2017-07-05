package ismt.application.main;

public class Rules {

	private RulesCategory category;
	private String name;
	private String summary;
	private String strategy;
	private String number;


public RulesCategory getCategory() {
		return category;
	}
	public void setCategory(RulesCategory category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	
	}
	
}


