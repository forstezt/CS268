package jsfPkg;

import java.sql.*;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class StudentUtilities {
	private String msg = null;
	private ArrayList<Student> sData = null;
	
	public StudentUtilities() {
		super();
		retrieveStudents();
	}

	public void retrieveStudents() {
		this.msg = null;
		this.sData = new ArrayList<Student>();
		MySQL.open();
		try {
			String sqlQuery = "SELECT s_id, s_last, s_first, s_mi, s_address, s_city, st_state, s_zip, s_phone, s_class, s_dob, s_pin, nw_student.f_id, f_last, f_first " +
					          "FROM nw_student INNER JOIN nw_faculty " +
					          "ON nw_student.f_id = nw_faculty.f_id " +
					          "ORDER BY s_last, s_first";	
			Statement stmt = MySQL.cn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			while(rs.next()) {
				Student s = new Student();
				s.setS_id(rs.getLong("s_id"));
				s.setS_last(rs.getString("s_last"));
				s.setS_first(rs.getString("s_first"));
				s.setS_mi(rs.getString("s_mi"));
				s.setS_address(rs.getString("s_address"));
				s.setS_city(rs.getString("s_city"));
				s.setSt_state(rs.getString("st_state"));
				s.setS_zip(rs.getString("s_zip"));
				s.setS_phone(rs.getString("s_phone"));
				s.setS_class(rs.getString("s_class"));
				s.setS_dob(rs.getString("s_dob"));
				s.setS_pin(rs.getString("s_pin"));
				s.setF_id(rs.getLong("f_id"));
				s.setF_last(rs.getString("f_last"));
				s.setF_first(rs.getString("f_first"));
				this.sData.add(s);
			}
		} catch (SQLException e) {
			this.msg = e.getMessage();			
		} finally {
			MySQL.close();
		}
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public ArrayList<Student> getsData() {
		return sData;
	}
}