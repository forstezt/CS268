package MVCPkg;
import java.io.Serializable;
import java.sql.*;

import javax.servlet.http.HttpServletRequest;
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
	private Connection cn = null;
	
	public long   getProd_id()          { return prod_id; }
	public String getProd_desc()        { return prod_desc; }
	public float  getProd_cost()        { return prod_cost; }
	public float  getProd_price()       { return prod_price; }
	public String getError_msg()        { return error_msg; }
	public String getFormattedCost()    { return formattedCost; }
	public String getFormattedPrice()   { return formattedPrice; }
	public ArrayList<Product> getData() { return data; }
	
	public void runInsert(HttpServletRequest request) {
		this.error_msg = "Success";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
					                         "STUDENT", "$5333");
		    PreparedStatement pstmt = 
		    	cn.prepareStatement("INSERT INTO candy_product (prod_desc, prod_cost, prod_price) " + 
                                    "VALUES (?, ?, ?)");
			pstmt.setString(1, request.getParameter("prod_desc"));
			pstmt.setString(2, request.getParameter("prod_cost"));
			pstmt.setString(3, request.getParameter("prod_price"));
			pstmt.execute(); 			
		} catch (Exception e) {
			this.error_msg = e.getMessage();			
		}
		retrieveProductInformation(request, cn);
	}
	public void retrieveProductInformation(HttpServletRequest request, Connection cn) {
		this.data = new ArrayList<Product>();
		NumberFormat f = NumberFormat.getCurrencyInstance();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			if(cn == null || cn.isClosed()) {
				cn=DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", 
						                       "STUDENT", "$5333");
			}
			String query = "SELECT prod_id, prod_desc, prod_cost, prod_price " +
			               "FROM candy_product ORDER BY prod_desc";			               	
			Statement stmt = cn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				Product p = new Product();
				p.prod_id = rs.getLong("prod_id");
				p.prod_desc = rs.getString("prod_desc");
				p.prod_cost = rs.getFloat("prod_cost");
				p.prod_price = rs.getFloat("prod_price");
				p.formattedCost = f.format(p.prod_cost);
				p.formattedPrice = f.format(p.prod_price);
				this.data.add(p);
			}
		} catch (Exception e) {
			this.error_msg = e.getMessage();			
		}	
	}
}
