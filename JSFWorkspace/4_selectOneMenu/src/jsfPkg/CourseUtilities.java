package jsfPkg;

import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

@ManagedBean
public class CourseUtilities {
	private Long c_id = null;
	private String c_callid = null;
	private Integer c_credits = null;
	private String c_name = null;
	private Long d_id = null;
	private String d_name = null;
	private String msg = null;
	private ArrayList<Course> cData = null;
	private ArrayList<SelectItem> dData = null;
	
	public CourseUtilities() {
		super();
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
		} 
	}
	public void retrieveDepartments() {
		this.dData = new ArrayList<SelectItem>();
		MySQL.open();
		try {
			//create the query statement			
			String sqlQuery = "SELECT d_id, d_name " +
					          "FROM nw_department " +
					          "ORDER BY d_name";	
			Statement stmt = MySQL.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				SelectItem d = new SelectItem(rs.getString("d_id"), rs.getString("d_name"));				
				this.dData.add(d);
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();			
		} finally {
			MySQL.close();
		}	
	}	
	public String insertCourse() {
		return runQuery("insert");
	}
	public String runQuery(String action) {
		String ret = "displaycourses";
		MySQL.open();
		try {
			//create the query statement
			String sqlQuery = "";
			if(action.equals("insert")) {
				sqlQuery = "INSERT INTO nw_course (c_callid, c_name, c_credits, d_id)VALUES(?,?,?,?);";
				PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
				pstmt.setString(1, c_callid);
				pstmt.setString(2, c_name);
				pstmt.setInt(3, c_credits);
				pstmt.setLong(4, d_id);
				// execute the query
				pstmt.execute();
				this.msg = "Added " + c_callid;
				ret = "addcourse";
			} else if(action.equals("delete")) {
				sqlQuery = "DELETE FROM nw_course WHERE c_id = ?";
				PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
				pstmt.setLong(1, c_id);
				pstmt.execute();
				this.msg = "Deleted " + this.c_callid;			         
			} else if(action.equals("update")) {
				sqlQuery = "UPDATE nw_course SET c_callid=?, c_name=?, " +
				                  "c_credits=?, d_id=? WHERE c_id=?;";
				PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
				pstmt.setString(1, c_callid);
				pstmt.setString(2, c_name);
				pstmt.setInt(3, c_credits);
				pstmt.setLong(4, d_id);
				pstmt.setLong(5, c_id);
				pstmt.execute();
				this.msg = "Updated " + this.c_callid;			         
			}
			c_id = null;
			c_callid = "";
			c_credits = null;
			c_name = "";
			d_id = null;
			d_name = "";			
		} catch (Exception e) {
			this.msg = action + " failed<br />\n" + e.getMessage();			
		} 
		return ret;
	}	
	public Long getC_id() {
		return c_id;
	}
	public void setC_id(Long cId) {
		c_id = cId;
	}
	public String getC_callid() {
		return c_callid;
	}
	public void setC_callid(String cCallid) {
		c_callid = cCallid;
	}
	public Integer getC_credits() {
		return c_credits;
	}
	public void setC_credits(Integer cCredits) {
		c_credits = cCredits;
	}
	public String getC_name() {
		return c_name;
	}
	public void setC_name(String cName) {
		c_name = cName;
	}
	public Long getD_id() {
		return d_id;
	}
	public void setD_id(Long dId) {
		d_id = dId;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String dName) {
		d_name = dName;
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
		retrieveCourses();
		return cData;
	}	
	public ArrayList<SelectItem> getdData() {
		retrieveDepartments();
		return dData;
	}		
}
