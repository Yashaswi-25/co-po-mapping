package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  beans.*;


public class Units {
	private int subId,unitId;
	private String unitName,subjectName,topics;
	private String userid;
	List<Units> lstunits;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	 
	 
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	public List<Units> getLstunits() {
		return lstunits;
	}
	public void setLstunits(List<Units> lstunits) {
		this.lstunits = lstunits;
	}
	public int getSubId() {
		return subId;
	}
	public void setSubId(int subId) {
		this.subId = subId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Units()
	{
		
	}
	public Units(ResultSet rs)
	{
		try
		{
		subId=rs.getInt("subId");
		unitId=rs.getInt("unitId");
		subjectName=rs.getString("subjectName");
		unitName=rs.getString("unitName").toString().trim();
		userid=rs.getString("userid");
		topics=rs.getString("topics");
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in constr"+e.getMessage());
		}
	}
	public void getUnits()
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getUnits(?)}"); 
 	        
 	       csmt.setInt(1, subId);
 	       
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstunits=new ArrayList<Units>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstunits.add(new Units(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public boolean insertUnits() {
	
	Connection con;
	CallableStatement csmt;
	GetConnection gc = new GetConnection();
	String sts="";
	
	try {
		con=gc.getConnection();
		 Date dt1=new Date();
		 String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
		 csmt=con.prepareCall("{call insertUnits(?,?,?,?,?)}");
		 csmt.setString(1, subjectName);
		  csmt.setString(2, userid);
	        csmt.setString(3,unitName);
	        csmt.setString(4,topics);
	        csmt.setString(5,"0");
	         System.out.println("data="+subjectName+" "+userid+" "+unitName );
	         
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
