package strutsPkg;

public class Msg {
	private String msg; // allows redirectAction param to set this value
	
	// execute is called by default when a method isn't specified for this class in struts.xml.
	// The only reason for creating this class is to provide a backing bean for msg
	// allowing it to be assigned a value in struts.xml and displayed in the form.
	// If you specify a class in a struts.xml action, it must also call a method.
	// So this method will get called but does nothing additional (other than enabling
	// the param msg to be assigned from struts.xml)
	public String execute() {
		return "success";
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
