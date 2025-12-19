package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import  beans.*;


public class Courses {
	private int courseId,semNo;
	private String courseName;
	private String branchname;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	List<Courses> lstCourse;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getBranchname() {
		return branchname;
	} 
	public void setBranchname(String branchname) {
		this.branchname = branchname;
	}
	
	public int getSemNo() {
		return semNo;
	}
	public void setSemNo(int semNo) {
		this.semNo = semNo;
	}
	public List<Courses> getLstCourse() {
		return lstCourse;
	}
	public void setLstCourse(List<Courses> lstCourse) {
		this.lstCourse = lstCourse;
	}
	public Courses()
	{
		
	}
	public Courses(ResultSet rs)
	{
		try
		{
		courseId=rs.getInt("courseId");
		semNo=rs.getInt("noOfSemesters");
		branchname=rs.getString("branch").toString().trim();
		courseName=rs.getString("courseName").toString().trim();
		 
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}
	public boolean registerCourse() {
	
	Connection con;
	CallableStatement csmt;
	GetConnection gc = new GetConnection();
	String sts="";
	
	try {
		con=gc.getConnection();
		 
		 csmt=con.prepareCall("{call insertCourses(?,?,?)}");
	        csmt.setString(1, courseName);
	        csmt.setString(2, branchname);
	        csmt.setInt(3, semNo);
	         
	         int n=csmt.executeUpdate(); 
	        if(n>0)
	        {
	            try{con.close();}catch(Exception ex){}
	            System.out.println("true");
	            return true;
	        }
	        else
	            return false; 
	}
	catch(Exception ex) {
		System.out.println("err="+ex.getMessage());
		ex.printStackTrace();
		return false; 
	}
	 
	}
	public void getCourses()
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getCourses(?)}"); 
 	        csmt.setString(1, branchname);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstCourse=new ArrayList<Courses>();          
 	        while(rs.next())
 	        { System.out.println("true");
 	        	lstCourse.add(new Courses(rs)); 
 	        }
 	       try{con.close();}catch(Exception ex){}
 	    } 
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public void getSemesters()
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getSemNo(?)}"); 
 	        csmt.setInt(1, courseId);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	                     
 	        while(rs.next())
 	        { System.out.println("true");
 	        	semNo=rs.getInt("noOfSemesters"); 
 	        }
 	       try{con.close();}catch(Exception ex){}
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
}
