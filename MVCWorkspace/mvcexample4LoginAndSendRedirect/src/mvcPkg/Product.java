package mvcPkg;
import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private long prod_id;
	private String prod_desc;
	private String escProd_desc;
	private float prod_cost;
	private float prod_price;
	private String formattedCost;
	private String formattedPrice;
	private String error_msg;
	
	public long getProd_id() {
		return prod_id;
	}
	public void setProd_id(long prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prod_desc) {
		this.prod_desc = prod_desc;
	}	
	public String getEscProd_desc() {
		return escProd_desc;
	}
	public void setEscProd_desc(String escProd_desc) {
		this.escProd_desc = escProd_desc;
	}
	public float getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(float prod_cost) {
		this.prod_cost = prod_cost;
	}
	public float getProd_price() {
		return prod_price;
	}
	public void setProd_price(float prod_price) {
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
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
}
