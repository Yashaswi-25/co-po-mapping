package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import  beans.*;


public class SubTopics {
	private int subId,unitId,subTopicId;
	private String unitName,subjectName,subTopicName;
	private String userid;
	List<SubTopics> lstsubTopics;
	Connection con;
	CallableStatement csmt;
	ResultSet rs;
	 
	  
	public int getSubTopicId() {
		return subTopicId;
	}
	public void setSubTopicId(int subTopicId) {
		this.subTopicId = subTopicId;
	}
	public String getSubTopicName() {
		return subTopicName;
	}
	public void setSubTopicName(String subTopicName) {
		this.subTopicName = subTopicName;
	}
	 
	public List<SubTopics> getLstsubTopics() {
		return lstsubTopics;
	}
	public void setLstsubTopics(List<SubTopics> lstsubTopics) {
		this.lstsubTopics = lstsubTopics;
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
	public SubTopics()
	{
		
	}
	public SubTopics(ResultSet rs)
	{
		try
		{
			try
			{

				subTopicName=rs.getString("subTopic");
			}
			catch (Exception e1) {
				// TODO: handle exception
			}
		subTopicId=rs.getInt("topicId");
		unitId=rs.getInt("unitId");
		subTopicName=rs.getString("topicName");
		unitName=rs.getString("unitName").toString().trim();
		userid=rs.getString("userid");
		  
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("err in constr"+e.getMessage());
		}
	}
	public void getSubTopics()
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getSubTopics(?)}"); 
 	        System.out.println("unitid="+unitId);
 	       csmt.setInt(1, unitId);
 	       
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsubTopics=new ArrayList<SubTopics>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsubTopics.add(new SubTopics(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public void getSubTopics2(int sesId)
 	{
 	    try
 	    {
 	    	GetConnection obj = new GetConnection();
 	         
 	        con=obj.getConnection() ;
 	        csmt=con.prepareCall("{call getSubTopics2(?)}"); 
 	        
 	       csmt.setInt(1, sesId);
 	       
 	         csmt.execute();
 	         rs=csmt.getResultSet();
 	           lstsubTopics=new ArrayList<SubTopics>();          
 	        while(rs.next())
 	        { System.out.println(rs.toString());
 	       lstsubTopics.add(new SubTopics(rs)); 
 	        }
 	    }
 	       
 	     
 	    catch(Exception ex)
 	    {
 	        System.out.println("err="+ex.getMessage());
 	         
 	    }
 	}
	public boolean insertSubTopics() {
	
	Connection con;
	CallableStatement csmt;
	GetConnection gc = new GetConnection();
	String sts="";
	
	try {
		con=gc.getConnection();
		 Date dt1=new Date();
		 String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
		 csmt=con.prepareCall("{call insertTopics(?,?,?,?)}");
		 csmt.setString(1, subTopicName);
		  csmt.setInt(2, unitId);
	        csmt.setString(3,userid);
	        csmt.setString(4,dt);
	         System.out.println("data="+subId+" "+userid+" "+unitName );
	         
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
	public boolean insertSesSubTopics(String sesId,String sestitle) {
		
		Connection con;
		CallableStatement csmt;
		GetConnection gc = new GetConnection();
		String sts="";
		
		try {
			con=gc.getConnection();
			 Date dt1=new Date();
			 String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
			 csmt=con.prepareCall("{call insertSesTopics(?,?,?,?)}");
			 csmt.setInt(1, subTopicId);
			  csmt.setString(2, userid);
		        csmt.setInt(3,Integer.parseInt(sesId));
		        csmt.setString(4,sestitle);
		         System.out.println("data="+sestitle+" "+userid+" "+subTopicId );
		         
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
		 
public boolean insertDiff(String sesId1,String topicid1,String examid1) {
		
		Connection con;
		CallableStatement csmt;
		GetConnection gc = new GetConnection();
		String sts="";
		
		try {
			con=gc.getConnection();
			 Date dt1=new Date();
			 String dt=dt1.getDate()+"/"+(dt1.getMonth()+1)+"/"+(dt1.getYear()+1900);
			 csmt=con.prepareCall("{call insertDifficultTopic(?,?,?,?)}");
			 csmt.setInt(1, Integer.parseInt(topicid1));
			  csmt.setString(2, "NA");
		        csmt.setInt(3,Integer.parseInt(examid1));
		        csmt.setInt(4,Integer.parseInt(sesId1));
		          
		         
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
