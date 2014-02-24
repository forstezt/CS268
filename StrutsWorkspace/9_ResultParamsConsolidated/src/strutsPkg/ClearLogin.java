package strutsPkg;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class ClearLogin implements SessionAware {
	private SessionMap<String, Object> session = null;
	private String msg; // A redirectAction param in struts.xml sets this value
	
	public String execute() {
		return "success";
	}
	
	// this class does double duty in that it clears the session object used
	// to indicate a user is logged in - and also allows (if needed) the msg
	// value to be assigned from a struts.xml param indicating an invalid login attempt
	public String clearLogin() {
		session.remove("cust_id");
		return "success";
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setSession(Map<String, Object> arg0) {
		this.session = (SessionMap)arg0;
	}
}
