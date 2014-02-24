package jsfPkg;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQL {
	public static Connection cn;

	public static String open() {
		if (cn != null) return "success";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://stef.cs.uwec.edu/STUDENT", "STUDENT", "S5333");
		} catch (Exception e) {
			return e.getMessage();
		}
		return "success";
	}

	public static void close() {
		if (cn == null) return;
		try { cn.close(); } catch(Exception e){};
		cn = null;
	}
}
