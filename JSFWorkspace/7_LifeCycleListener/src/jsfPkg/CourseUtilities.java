package jsfPkg;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class CourseUtilities implements Serializable {
	private static final long serialVersionUID = 1L;
	private Course c = null;
	private Course cOrig = null;
	private String msg = null;
	private ArrayList<Course> cData = null;
	private ArrayList<SelectItem> dData = null;
	
	public CourseUtilities() {
		super();
		System.out.println("CourseUtilities in constructor");
		c = new Course();
		retrieveCourses();
		retrieveDepartments();
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
				Course crs = new Course();
				crs.setC_id(rs.getString("c_id"));
				crs.setC_callid(rs.getString("c_callid"));
				crs.setC_credits(rs.getString("c_credits"));
				crs.setC_name(rs.getString("c_name"));
				crs.setD_id(rs.getString("d_id"));
				crs.setD_name(rs.getString("d_name"));
				// I added the following code to allow alternating row colors
				// and suppressing duplicate department names in the view
				// by checking these values
				if(last_dept.equals(rs.getString("d_name"))) {
					crs.setDeptChanged(false);
				} else {
					crs.setDeptChanged(true);
					last_dept = rs.getString("d_name");
					if(row_class.equals("row1")) {
						row_class = "row2";
					} else {
						row_class = "row1";
					}
				}
				crs.setBgColorClass(row_class);
				this.cData.add(crs);
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
		MySQL.open();
		try {
			String sqlQuery = "";
			sqlQuery = "INSERT INTO nw_course (c_callid, c_name, c_credits, d_id)VALUES(?,?,?,?);";
			PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
			pstmt.setString(1, c.getC_callid());
			pstmt.setString(2, c.getC_name());
			pstmt.setString(3, c.getC_credits());
			pstmt.setString(4, c.getD_id());
			// execute the query
			pstmt.execute();
			this.msg = "Added " + c.getC_callid();	
		} catch (Exception e) {
			this.msg = "Insert failed<br />\n" + e.getMessage();
		} 
		c.clear();
		retrieveCourses();		
		MySQL.close();
		return "addcourse";
	}
	public String deleteCourse() {
		MySQL.open();
		try {
			String sqlQuery = "";
			sqlQuery = "DELETE FROM nw_course WHERE c_id = ?";
			PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
			pstmt.setString(1, c.getC_id());
			pstmt.execute();				
			this.msg = "Deleted " + c.getC_callid();			         
		} catch (Exception e) {
			this.msg = "Delete failed<br />\n" + e.getMessage();
		} 
		c.clear();
		retrieveCourses();
		MySQL.close();
		return "editcourse";
	}
	public String updateCourse() {
		MySQL.open();
		try {
			//create the query statement
			String sqlQuery = "";
			sqlQuery = "UPDATE nw_course SET c_callid=?, c_name=?, c_credits=?, d_id=? WHERE c_id=?;";
			c.setEditable(false);
			PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
			pstmt.setString(1, c.getC_callid());
			pstmt.setString(2, c.getC_name());
			pstmt.setString(3, c.getC_credits());
			pstmt.setString(4, c.getD_id());
			pstmt.setString(5, c.getC_id());
			pstmt.execute();
			this.msg = "Updated " + c.getC_callid();
		} catch (Exception e) {
			this.msg = "Update failed<br />\n" + e.getMessage();
		} 
		c.clear();
		retrieveCourses();
		MySQL.close();
		return "editcourse";	
	}	
	public void setEditableTrue() {
		cOrig = c.clone();
		for(Course cdo : cData) {
			if(c.getC_id().equals(cdo.getC_id())){
				cdo.setEditable(true);
			} else {
				cdo.setEditable(false);
			}				
		}
	}
	public void setEditableFalse() {
		for(Course crs : cData) {
			if(crs.isEditable()) {
				// copy original property values from cOrig back to crs
				crs.setC_id(cOrig.getC_id());
				crs.setC_callid(cOrig.getC_callid());
				crs.setC_credits(cOrig.getC_credits());
				crs.setC_name(cOrig.getC_name());
				crs.setD_id(cOrig.getD_id());
			}
			crs.setEditable(false);
		}
		c.clear();
	}
	public Course getC() {
		return c;
	}
	public void setC(Course c) {
		this.c = c;
	}
	public ArrayList<Course> getcData() {
		return cData;
	}	
	public String getMsg() {
		String m = msg;
		msg = "";
		return m;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<SelectItem> getdData() {
		return dData;
	}
}
