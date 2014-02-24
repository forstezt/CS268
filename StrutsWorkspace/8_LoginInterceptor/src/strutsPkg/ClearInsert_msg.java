package strutsPkg;

import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

public class ClearInsert_msg implements SessionAware {
	private SessionMap<String, Object> session = null;
	public String clearInsert_msg() {
		session.remove("insert_msg");
		return "success";
	}
	@SuppressWarnings("unchecked")
	public void setSession(Map<String, Object> arg0) {
		this.session = (SessionMap)arg0;
	}
}
