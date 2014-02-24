package jsfPkg;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
	public static Connection cn;

	public static String open() {
		String ret = "success";
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

	public static void close() {
		if (cn == null) return;
		try { cn.close(); } catch(Exception e){};
		cn = null;
	}
}
