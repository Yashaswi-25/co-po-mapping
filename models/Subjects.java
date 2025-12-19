package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import  beans.*;


public class Subjects {
	private int subId,cnt,semester,courseId;
	private String subjectName;
	private String branchname,subtype,courseName;
	List<Subjects> lstsub;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	 
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public List<Subjects> getLstsub() {
		return lstsub;
	}
	public void setLstsub(List<Subjects> lstsub) {
		this.lstsub = lstsub;
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
	
	public String getSubtype() {
		return subtype;
	}
	public void setSubtype(String subtype) {
		this.subtype = subtype;
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
	public Subjects()
	{
		
	}
	public Subjects(ResultSet rs)
	{
		try
		{
		subId=rs.getInt("subId");
		subjectName=rs.getString("subName");
		branchname=rs.getString("branch").toString().trim();
		semester=rs.getInt("semester");
		courseName=rs.getString("course").toString().trim();
		subtype=rs.getString("sub_type").toString().trim();
		 try {
			 cnt=rs.getInt("cnt");
		 }
		 catch (Exception e) {
			// TODO: handle exception
		}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in constr"+e.getMessage());
		}
	}
	public void getSubjects()
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getSubjects(?,?,?)}"); 
 	        csmt.setString(1, branchname);
 	       csmt.setInt(2, courseId);
 	      csmt.setInt(3, semester);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<Subjects>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsub.add(new Subjects(rs)); 
 	        }
 	       try{con.close();}catch(Exception ex){}
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public void getAllottedSubjectsStud(String userid2,String br,String sem)
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getAllottedSubjectsStud(?,?,?)}"); 
 	        csmt.setString(1, br);
 	        System.out.println(br);
 	       System.out.println(userid2);
 	       csmt.setString(2, userid2);
 	       csmt.setInt(3, Integer.parseInt(sem));
 	      
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<Subjects>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsub.add(new Subjects(rs)); 
 	        }
 	       try{con.close();}catch(Exception ex){}
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public void getAllottedSubjectsStud1(String userid2,String br,String sem)
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getAllottedSubjectsStud1(?,?,?)}"); 
 	        csmt.setString(1, br);
 	        System.out.println(br);
 	       System.out.println(userid2);
 	       csmt.setString(2, userid2);
 	       csmt.setInt(3, Integer.parseInt(sem));
 	      
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<Subjects>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsub.add(new Subjects(rs)); 
 	        }
 	       try{con.close();}catch(Exception ex){}
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public void getAllottedSubjects(String userid)
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getAllottedSubjects(?,?,?,?)}"); 
 	        csmt.setString(1, branchname);
 	       csmt.setInt(2, courseId);
 	      csmt.setInt(3, semester);
 	     csmt.setString(4,userid );
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<Subjects>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsub.add(new Subjects(rs)); 
 	        }
 	       try{con.close();}catch(Exception ex){}
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public boolean registerSubject() {
	
	Connection con;
	CallableStatement csmt;
	GetConnection gc = new GetConnection();
	String sts="";
	
	try {
		con=gc.getConnection();
		 
		 csmt=con.prepareCall("{call insertSubjects(?,?,?,?,?)}");
		 csmt.setString(1, subjectName);
		  csmt.setString(2, branchname);
	        csmt.setInt(3, semester);
	        csmt.setInt(4, courseId);
	        csmt.setString(5, subtype);
	        System.out.println("data="+subjectName+" "+branchname+" "+semester+" "+courseId);
	         
	         int n=csmt.executeUpdate(); 
	         try{con.close();}catch(Exception ex){}
	        if(n>0)
	        {
	           
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
