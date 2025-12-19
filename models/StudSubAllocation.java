package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  beans.*;


public class StudSubAllocation {
	private String userid,username,course,branch,subject,dt,pref1,pref2,pref3;
	 private int sem;
	List<StudSubAllocation> lstsub;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	  
	public String getPref1() {
		return pref1;
	}
	public void setPref1(String pref1) {
		this.pref1 = pref1;
	}
	public String getPref2() {
		return pref2;
	}
	public void setPref2(String pref2) {
		this.pref2 = pref2;
	}
	public String getPref3() {
		return pref3;
	}
	public void setPref3(String pref3) {
		this.pref3 = pref3;
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
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDt() {
		return dt;
	}
	public void setDt(String dt) {
		this.dt = dt;
	}
	public int getSem() {
		return sem;
	}
	public void setSem(int sem) {
		this.sem = sem;
	}
	public List<StudSubAllocation> getLstsub() {
		return lstsub;
	}
	public void setLstsub(List<StudSubAllocation> lstsub) {
		this.lstsub = lstsub;
	}
	public StudSubAllocation()
	{
		
	}
	public StudSubAllocation(ResultSet rs)
	{
		try
		{
		sem=rs.getInt("sem");
		subject=rs.getString("subjectName");
		userid=rs.getString("userid").toString().trim();
		username=rs.getString("username").toString().trim();
		branch=rs.getString("branch");
		course=rs.getString("course").toString().trim();		  
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
 	        csmt=con.prepareCall("{call getSubjectsAllocationStud(?,?,?)}"); 
 	        csmt.setString(1,course);
 	       csmt.setString(2, branch);
 	      csmt.setInt(3, sem);
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsub=new ArrayList<StudSubAllocation>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsub.add(new StudSubAllocation(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
 
	public boolean allocateSubject(String email) {
	
	Connection con;
	CallableStatement csmt;
	GetConnection gc = new GetConnection();
	String sts="";
	
	try {
		con=gc.getConnection();
		 Date dt1=new Date();
		 String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
		 csmt=con.prepareCall("{call insertStudSubAllocation(?,?,?,?,?,?,?,?,?,?)}");
		 csmt.setString(1, userid);
		  csmt.setString(2, username);
	        csmt.setString(3, subject);
	        csmt.setString(4, branch);
	        csmt.setString(5, course);
	        csmt.setInt(6, sem);
	        csmt.setString(7, dt);
	        csmt.setString(8, pref1);
	        csmt.setString(9, pref2);
	        csmt.setString(10, pref3);
	         int n=csmt.executeUpdate(); 
	         Mail mail=new Mail();
	         String msg="Dear Student, Subject '"+subject+"' has been allotted to you as per your preferences.";
	         try
	         {
	        	 mail.sendMail(msg, email,"Subject Allocation");
	         }
	         catch (Exception e) {
				// TODO: handle exception
			}
	        if(n>0)
	        {
	            try{con.close();}catch(Exception ex){}
	          //  JavaFuns jf=new JavaFuns();
	          //  int id=jf.FetchMax("id", "preferences_stud");
	         //   String qr="insert into preferences_stud values(";
	           // qr+=id+",'"+userid+"','"+username+"','"+branch+"',"+sem+",'"+pref1+"','"+pref2+"','"+pref3+"','"+subject+"')
	            
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
