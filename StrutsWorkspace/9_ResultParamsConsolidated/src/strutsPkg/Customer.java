package strutsPkg;

public class Customer {
	private long cust_id;
	private String cust_name;
	private String cust_type;
	private String cust_type_desc;
	private String cust_addr;
	private String cust_zip;
	private String cust_phone;
	private String username;
	private String password;
	public long getCust_id() {
		return cust_id;
	}
	public void setCust_id(long custId) {
		cust_id = custId;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String custName) {
		cust_name = custName;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String custType) {
		cust_type = custType;
	}
	public String getCust_type_desc() {
		return cust_type_desc;
	}
	public void setCust_type_desc(String custTypeDesc) {
		cust_type_desc = custTypeDesc;
	}
	public String getCust_addr() {
		return cust_addr;
	}
	public void setCust_addr(String custAddr) {
		cust_addr = custAddr;
	}
	public String getCust_zip() {
		return cust_zip;
	}
	public void setCust_zip(String custZip) {
		cust_zip = custZip;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String custPhone) {
		cust_phone = custPhone;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}	
}
