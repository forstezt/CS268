package jsfPkg;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class CourseUtilities {
	private String msg;
	private ArrayList<Course> cData;
	
	public CourseUtilities() {
		super();
		retrieveCourses();
	}
	
	public String getMsg() {
		String m = msg;
		msg = "";
		return m;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<Course> getcData() {
		return cData;
	}

	public void retrieveCourses() {
		this.cData = new ArrayList<Course>();
		MySQL.open();
		try {
			String sqlQuery = "SELECT c_id, c_callid, c_name, c_credits, nw_course.d_id, d_name " +
					          "FROM nw_course INNER JOIN nw_department " +
					          "ON nw_course.d_id = nw_department.d_id " +
					          "ORDER BY d_name, c_callid";	
			Statement stmt = MySQL.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			String last_dept = "";
			String row_class = "row2";
			while(rs.next()) {
				Course c = new Course();
				c.setC_id(rs.getLong("c_id"));
				c.setC_callid(rs.getString("c_callid"));
				c.setC_credits(rs.getInt("c_credits"));
				c.setC_name(rs.getString("c_name"));
				c.setD_id(rs.getLong("d_id"));
				c.setD_name(rs.getString("d_name"));
				// I added the following code to allow alternating row colors
				// and suppressing duplicate department names in the view
				// by checking these values
				if(last_dept.equals(rs.getString("d_name"))) {
					c.setDeptChanged(false);
				} else {
					c.setDeptChanged(true);
					last_dept = rs.getString("d_name");
					if(row_class.equals("row1")) {
						row_class = "row2";
					} else {
						row_class = "row1";
					}
				}
				c.setBgColorClass(row_class);
				this.cData.add(c);
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();			
		} finally {
			MySQL.close();			
		}
	}
}
