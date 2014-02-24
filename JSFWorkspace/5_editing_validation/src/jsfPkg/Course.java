package jsfPkg;

import java.io.Serializable;

public class Course implements Serializable {
	private static final long serialVersionUID = 1L;
	private String c_id;
	private String c_callid;
	private String c_credits;
	private String c_name;
	private String d_id;
	private String d_name;
	private String bgColorClass;
	private Boolean deptChanged;
	private boolean editable;

	public void clear() {
		c_id = null;
		c_callid = null;
		c_credits = null;
		c_name = null;
		d_id = null;
		d_name = null;
		bgColorClass = null;
		deptChanged = null;
	}
	public String getClear() {
		clear();
		return "addcourse.jsf";
	}
	public Course clone() {
		Course c       = new Course();
		c.c_id         = new String(this.c_id);
		c.c_callid     = new String(this.c_callid);
		c.c_credits    = new String(this.c_credits);
		c.c_name       = new String(this.c_name);
		c.d_id         = new String(this.d_id);
		c.bgColorClass = new String(this.bgColorClass);
		c.deptChanged  = this.deptChanged;
		return c;
	}
	public String getC_id() {
		return c_id;
	}
	public void setC_id(String cId) {
		c_id = cId;
	}
	public String getC_callid() {
		return c_callid;
	}
	public void setC_callid(String cCallid) {
		c_callid = cCallid;
	}
	public String getC_credits() {
		return c_credits;
	}
	public void setC_credits(String cCredits) {
		c_credits = cCredits;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String cName) {
		c_name = cName;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String dId) {
		d_id = dId;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String dName) {
		d_name = dName;
	}
	public String getBgColorClass() {
		return bgColorClass;
	}
	public void setBgColorClass(String bgColorClass) {
		this.bgColorClass = bgColorClass;
	}
	public Boolean getDeptChanged() {
		return deptChanged;
	}
	public void setDeptChanged(Boolean deptChanged) {
		this.deptChanged = deptChanged;
	}
	public void toggleEditable() {
		this.editable = !this.editable;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
