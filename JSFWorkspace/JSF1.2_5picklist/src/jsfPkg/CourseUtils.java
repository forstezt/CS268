package jsfPkg;

import java.sql.*;
import java.util.ArrayList;
import javax.faces.model.SelectItem;

public class CourseUtils {
	private Course c = null;	
	private String msg;
	ArrayList<Course> courseList = null;
	ArrayList<SelectItem> departmentList = null;
	
	public CourseUtils() {
		super();
		System.out.println("CourseUtils in constructor");
		c = new Course();
		retrieveCourses();
	}	
	public String insert() {
		System.out.println("CourseUtils insert");
		String ret = "success";
		MySQL.open();
		try {
			//create the query statement
			String sqlQuery = "INSERT INTO nw_course(c_callid, c_name, c_credits, d_id)" +
			                  "VALUES(?,?,?,?);";
			PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
			pstmt.setString(1, this.c.getC_callid());
			pstmt.setString(2, this.c.getC_name());
			pstmt.setString(3, this.c.getC_credits());
			pstmt.setString(4, this.c.getD_id());
			pstmt.execute();
			this.msg = "Added Course";
		} catch (SQLException e) {
			this.msg = e.getMessage();
			ret = "error";
		}
		retrieveCourses();		
		MySQL.close();
		// clear property values
		c = new Course();
		return ret;
	}
	public String update() {
		MySQL.open();
		String sqlQuery = "UPDATE nw_course " +
		                  "SET c_callid = ?," +
		                      "c_name = ?," +
		                      "c_credits = ?," +
		                      "d_id = ? " +
				          "WHERE c_id = ?;";
		
		try {
			// interate through the arraylist
			for(Course c : courseList) {
				if(c.isModified()) {
					PreparedStatement pstmt = MySQL.cn.prepareStatement(sqlQuery);
					pstmt.setString(1, c.getC_callid());
					pstmt.setString(2, c.getC_name());
					pstmt.setString(3, c.getC_credits());
					pstmt.setString(4, c.getD_id());
					pstmt.setString(5, c.getC_id());
					c.setEditable(false);
					c.setModified(false);
					pstmt.execute(); 
				}
			}				
		    this.msg = "Updated Products";
		} catch (SQLException e) {
			this.msg = e.getMessage();
			return "error";
		}
		retrieveCourses();
	    MySQL.close();
	    return "success";
	}		
	public String delete() {
		System.out.println("CourseUtils delete");
		String ret = "success";
		try {
			MySQL.open();		
		    PreparedStatement pstmt = 
		    	MySQL.cn.prepareStatement("DELETE FROM nw_course WHERE c_id = ?");
		    
			pstmt.setString(1, c.getC_id());
			pstmt.execute(); 
			this.msg = "Deleted Course";
		} catch (Exception e) {
			this.msg = e.getMessage();			
			ret = "error";
		}
		retrieveCourses();
		MySQL.close();		
		return ret;
	}
	public String retrieveCourses() {
		System.out.println("CourseUtils retrieveCourses");
		String ret = "success";
		this.courseList = new ArrayList<Course>();
		MySQL.open();
		try {
			String sqlQuery = "SELECT c_id, c_callid, c_name, c_credits, nw_course.d_id, d_name " +
					          "FROM nw_course INNER JOIN nw_department " +
					          "ON nw_course.d_id = nw_department.d_id " +
					          "ORDER BY d_name, c_callid";	
			Statement stmt = MySQL.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				Course c = new Course(rs.getString("c_id"),
						              rs.getString("c_callid"),
						              rs.getString("c_name"),
						              rs.getString("c_credits"),
						              rs.getString("d_id"),
						              rs.getString("d_name"));
				this.courseList.add(c);
			}
		} catch (Exception e) {
			this.msg = e.getMessage();
			ret = "error";
		} finally {
			MySQL.close();
		}
		ret = retrieveDepartments(ret);
		return ret;
	}	
	public String retrieveDepartments(String ret) {
		System.out.println("CourseUtils retrieveDepartments");
		this.departmentList = new ArrayList<SelectItem>();
		MySQL.open();
		try {
			String sqlQuery = "SELECT d_id, d_name " +
				              "FROM nw_department " +
				              "ORDER BY d_name";	
			Statement stmt = MySQL.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				this.departmentList.add(new SelectItem(rs.getString("d_id"), rs.getString("d_name")));
			}
		} catch (Exception e) {
			this.msg = e.getMessage();
			ret = "error";
		} finally {
			MySQL.close();
		}		
		return ret;
	}		

	public Course getC() {
		return c;
	}
	public void setC(Course c) {
		this.c = c;
	}
	public String getMsg() {
		String m = msg;
		msg = "";
		return m;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	public void setCourseList(ArrayList<Course> courseList) {
		this.courseList = courseList;
	}
	public ArrayList<SelectItem> getDepartmentList() {
		return departmentList;
	}
	public void setDepartmentList(ArrayList<SelectItem> departmentList) {
		this.departmentList = departmentList;
	}	
}
