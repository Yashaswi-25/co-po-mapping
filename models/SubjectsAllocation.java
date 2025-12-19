package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  beans.*;


public class SubjectsAllocation {
	private int subId,semester,courseId;
	private String subjectName,dt,staffName;
	private String branchname,courseName,userid,username;
	List<SubjectsAllocation> lstsub;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	 
	 
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setLstsub(List<SubjectsAllocation> lstsub) {
		this.lstsub = lstsub;
	}
	public List<SubjectsAllocation> getLstsub() {
		return lstsub;
	} 
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
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
	public SubjectsAllocation()
	{
		
	}
	public SubjectsAllocation(ResultSet rs)
	{
		try
		{
		courseId=rs.getInt("subId");
		subjectName=rs.getString("subjectName");
		branchname=rs.getString("staffUserid").toString().trim();
		staffName=rs.getString("staffName");
		  
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in constr"+e.getMessage());
		}
	}
	public void getSubjectsAllocationReport()
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getSubjectsAllocation(?,?,?)}"); 
 	        csmt.setString(1, branchname);
 	       csmt.setInt(2, courseId);
 	      csmt.setInt(3, semester);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<SubjectsAllocation>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsub.add(new SubjectsAllocation(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public void getSubjectsAllocationReportStaff(String uid)
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getSubjectsAllottedStaff(?)}"); 
 	        csmt.setString(1, uid);
 	       
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<SubjectsAllocation>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	        SubjectsAllocation obj1=new SubjectsAllocation();
 	        obj1.setCourseName(rs.getString("courseName").trim());
 	       obj1.setSemester(rs.getInt("semester"));
 	      obj1.setSubId(rs.getInt("subId"));
 	      obj1.setBranchname(rs.getString("branch").trim());
 	     obj1.setSubjectName(rs.getString("subjectName").trim());
 	     obj1.setDt(rs.getString("dt"));
 	        lstsub.add(obj1); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public boolean allocateSubject() {
	
	Connection con;
	CallableStatement csmt;
	GetConnection gc = new GetConnection();
	String sts="";
	
	try {
		con=gc.getConnection();
		 Date dt1=new Date();
		 String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
		 csmt=con.prepareCall("{call insertSubjectAllocation(?,?,?,?,?,?)}");
		 csmt.setInt(1, subId);
		  csmt.setString(2, staffName);
	        csmt.setString(3, dt);
	        csmt.setInt(4, courseId);
	        csmt.setInt(5, semester);
	        csmt.setString(6, branchname);
	        System.out.println("data="+subId+" "+branchname+" "+semester+" "+courseId+" "+staffName);
	         
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
 	 
}
