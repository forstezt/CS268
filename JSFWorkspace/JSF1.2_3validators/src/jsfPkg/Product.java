package jsfPkg;
import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String prod_id;
	private String prod_desc;
	private String prod_cost;
	private String prod_price;
	private String formattedCost;
	private String formattedPrice;
	private boolean editable;
	private boolean modified;
	
	public Product() {
		this("", "", "", "", "");
	}
	
	public Product(String prod_id, String prod_desc, String prod_price, 
			       String formattedCost, String formattedPrice) {
		super();
		this.prod_id = prod_id;
		this.prod_desc = prod_desc;
		this.prod_price = prod_price;
		this.formattedCost = formattedCost;
		this.formattedPrice = formattedPrice;
		this.editable = false;
		this.modified = false;
	}	
	
	public String getProd_id() {
		return prod_id;
	}
	public void setProd_id(String prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}
	public String getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(String prod_cost) {
		this.prod_cost = prod_cost;
	}
	public String getProd_price() {
		return prod_price;
	}
	public void setProd_price(String prod_price) {
		this.prod_price = prod_price;
	}
	public String getFormattedCost() {
		return formattedCost;
	}
	public void setFormattedCost(String formattedCost) {
		this.formattedCost = formattedCost;
	}
	public String getFormattedPrice() {
		return formattedPrice;
	}
	public void setFormattedPrice(String formattedPrice) {
		this.formattedPrice = formattedPrice;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
		if(this.editable) {
			this.modified = true;
		}
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified(boolean modified) {
		this.modified = modified;
	}	
}
