package jsfPkg;

public class Department {
	private String d_id;
	private String d_name;
	private String d_chair;
	private boolean editable;
	private boolean modified;

	public Department() {
		this("", "", "");
	}	
	public Department(String d_id, String d_name) {
		this(d_id, d_name, "");
	}	
	public Department(String d_id, String d_name, String d_chair) {
		super();
		this.d_id = d_id;
		this.d_name = d_name;
		this.d_chair = d_chair;
		this.editable = false;
		this.modified = false;
	}
	public String getD_id() {
		return d_id;
	}
	public void setD_id(String d_id) {
		this.d_id = d_id;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public String getD_chair() {
		return d_chair;
	}
	public void setD_chair(String d_chair) {
		this.d_chair = d_chair;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}	
}
