package JSFpkg;

public class WhatsYourName {
	private String name;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String showName() {
		this.name = this.name.toUpperCase();
		return "showName";
	}	
}
