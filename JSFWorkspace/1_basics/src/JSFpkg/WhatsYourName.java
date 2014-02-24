package JSFpkg;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class WhatsYourName {
	private String name;
	private String msg;

	public WhatsYourName() {
		System.out.println("In constructor");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String showName() {
		if(this.name.equalsIgnoreCase("mike")) {
			msg = "Mike isn't a good name, choose something else :-)";
			return "error";
		}
		this.name = this.name.toUpperCase();
		return "showName";
	}
}
