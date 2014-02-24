package strutsPkg;

import java.sql.*;

public class MySQL {
	public Connection cn;

	public String open() {
		String ret = null;
		try {
			if (cn == null || cn.isClosed()) {
				Class.forName("com.mysql.jdbc.Driver");
				cn = DriverManager.getConnection("jdbc:mysql://dario.cs.uwec.edu/STUDENT", "STUDENT", "$5333");
			}
		} catch (Exception e) {
			ret = e.getMessage();
		}
		return ret;
	}
	public void close() {
		if (cn == null) return;
		try { 
			cn.close(); 
		} catch(Exception e) {
		};
		cn = null;
	}
}
