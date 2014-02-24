package jsfPkg;

public class Course {
	private long c_id;
	private String c_callid;
	private int c_credits;
	private String c_name;
	private long d_id;
	private String d_name;
	private String bgColorClass;
	private Boolean deptChanged;
	
	public long getC_id() {
		return c_id;
	}
	public void setC_id(long cId) {
		c_id = cId;
	}
	public String getC_callid() {
		return c_callid;
	}
	public void setC_callid(String cCallid) {
		c_callid = cCallid;
	}
	public int getC_credits() {
		return c_credits;
	}
	public void setC_credits(int cCredits) {
		c_credits = cCredits;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String cName) {
		c_name = cName;
	}
	public long getD_id() {
		return d_id;
	}
	public void setD_id(long dId) {
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
}
