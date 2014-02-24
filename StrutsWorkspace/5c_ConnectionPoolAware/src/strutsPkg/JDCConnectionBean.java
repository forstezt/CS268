package strutsPkg;

import java.sql.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JDCConnectionBean {

	  public static InitialContext ctx;
	  public String theuser, password; 
	  public String	creditcard, emailaddress;
	  public double balance;

	  //Static class instantiation
	  static {
	        try {
				ctx = new InitialContext();
			} catch (NamingException e1) {
				e1.printStackTrace();
			}
		  
	        try{
		        new JDCConnectionDriver("com.mysql.jdbc.Driver", 
		        		                "jdbc:mysql://dario.cs.uwec.edu/STUDENT",
										"STUDENT", "$5333");
	        } catch(Exception e) {}
	  }

	  public Connection getConnection() throws SQLException{
	        return DriverManager.getConnection("jdbc:jdc:jdcpool");
	  }
}
