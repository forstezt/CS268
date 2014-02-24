package strutsPkg;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class ClearLogin implements SessionAware {
	private SessionMap<String, Object> session = null;
	public String clearLogin() {
		session.remove("cust_id");
		return "success";
	}
	@SuppressWarnings("unchecked")
	public void setSession(Map<String, Object> arg0) {
		this.session = (SessionMap)arg0;
	}
}
