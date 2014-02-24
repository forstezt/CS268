package strutsPkg;
import java.io.Serializable;
import java.sql.*;
import java.text.*;
import java.util.*;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private long prod_id;
	private String prod_desc;
	private float prod_cost;
	private float prod_price;
	private String formattedCost;
	private String formattedPrice;
	private String error_msg;
	private ArrayList<Product> data;
	private String msg;

	public long getProd_id() {
		return prod_id;
	}
	public void setProd_id(long prodId) {
		prod_id = prodId;
	}
	public String getProd_desc() {
		return prod_desc;
	}
	public void setProd_desc(String prodDesc) {
		prod_desc = prodDesc;
	}
	public float getProd_cost() {
		return prod_cost;
	}
	public void setProd_cost(float prodCost) {
		prod_cost = prodCost;
	}
	public float getProd_price() {
		return prod_price;
	}
	public void setProd_price(float prodPrice) {
		prod_price = prodPrice;
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
		System.out.println("getError_Msg() obj id is: \t" + this);
		return error_msg;
	}
	public void setError_msg(String errorMsg) {
		error_msg = errorMsg;
	}
	public ArrayList<Product> getData() {
		System.out.println("getData obj() id is:       \t" + this);
		return data;
	}
	public String getMsg() {
		System.out.println("getMsg() obj id is:       \t" + this);
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}	
}
