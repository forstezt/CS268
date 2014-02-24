package jsfPkg;

public class Course {
	private String c_id;
	private String c_callid;
	private String c_name;
	private String c_credits;
	private String d_id;
	private String d_name;
	private boolean editable;
	private boolean modified;
	
	public Course() {
		this("","","","","", "");
	}	
	
	public Course(String c_id, String c_callid, String c_name,
			      String c_credits, String d_id, String d_name) {
		super();
		this.c_id = c_id;
		this.c_callid = c_callid;
		this.c_name = c_name;
		this.c_credits = c_credits;
		this.d_id = d_id;
		this.d_name = d_name;
		this.editable = false;
		this.modified = false;
	}
	
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String c_id) {
		this.c_id = c_id;
	}
	public String getC_callid() {
		return c_callid;
	}
	public void setC_callid(String c_callid) {
		this.c_callid = c_callid;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	public String getC_credits() {
		return c_credits;
	}
	public void setC_credits(String c_credits) {
		this.c_credits = c_credits;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.modified = true;
		this.editable = editable;
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}
}
