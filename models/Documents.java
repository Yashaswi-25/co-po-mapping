package models;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.web.multipart.MultipartFile;

import beans.GetConnection;  
public class Documents {
	Connection con;
    CallableStatement csmt;
    ResultSet rs;
private String docpath,sub,sem,branch,sessTitle,category,topic,docDesc,doctitle,dt,tm,userid,docData;
private int docId,sessId,topicId;
private List<Documents> lstdocs;
private MultipartFile file;

 
public String getSessTitle() {
	return sessTitle;
}

public void setSessTitle(String sessTitle) {
	this.sessTitle = sessTitle;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public int getSessId() {
	return sessId;
}

public void setSessId(int sessId) {
	this.sessId = sessId;
}

public int getTopicId() {
	return topicId;
}

public void setTopicId(int topicId) {
	this.topicId = topicId;
}

public ResultSet getRs() {
	return rs;
}

public void setRs(ResultSet rs) {
	this.rs = rs;
}

public String getDocDesc() {
	return docDesc;
} 
 

public String getSub() {
	return sub;
}

public void setSub(String sub) {
	this.sub = sub;
}

public String getSem() {
	return sem;
}

public void setSem(String sem) {
	this.sem = sem;
}

public String getTopic() {
	return topic;
}

public void setTopic(String topic) {
	this.topic = topic;
}

public void setDocDesc(String docDesc) {
	this.docDesc = docDesc;
}

public String getDoctitle() {
	return doctitle;
}

public void setDoctitle(String doctitle) {
	this.doctitle = doctitle;
}

public String getDt() {
	return dt;
}

public void setDt(String dt) {
	this.dt = dt;
}

public String getTm() {
	return tm;
}

public void setTm(String tm) {
	this.tm = tm;
}

public String getDocData() {
	return docData;
}

public void setDocData(String docData) {
	this.docData = docData;
}

public String getUserid() {
	return userid;
}

public void setUserid(String userid) {
	this.userid = userid;
}

public MultipartFile getFile() {
	return file;
}

public void setFile(MultipartFile file) {
	this.file = file;
}
 
public String getDocpath() {
	return docpath;
}

public void setDocpath(String docpath) {
	this.docpath = docpath;
}

 
  
public String getBranch() {
	return branch;
}

public void setBranch(String branch) {
	this.branch = branch;
}

public int getDocId() {
	return docId;
}

public void setDocId(int docId) {
	this.docId = docId;
}

public List<Documents> getLstdocs() {
	return lstdocs;
}

public void setLstdocs(List<Documents> lstdocs) {
	this.lstdocs = lstdocs;
}

public void getId()
{
    try
    {
         GetConnection obj=new  GetConnection();
        con=obj.getConnection();
        csmt=con.prepareCall("{call getMaxIdDocuments()}");
       
         csmt.execute();
         rs=csmt.getResultSet();
                    
        boolean auth=false;
        while(rs.next())
        { System.out.println("true");
            auth=true;
            
            docId=rs.getInt("mxid");
            if(docId==0)
            	docId=1001;
            else
            	docId+=1;
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}

public Documents()
{
	
}
public Documents(ResultSet rs)
{
	try
	{
		docpath=rs.getString("docPath").toString().trim();
		docId=rs.getInt("docId");
		docDesc=rs.getString("docDesc");
		doctitle=rs.getString("title");
		userid=rs.getString("userid");
		dt=rs.getString("dt");
		tm=rs.getString("tm");
		sub=rs.getString("subName");
		sem=String.valueOf(rs.getInt("sem"));
		sessTitle=rs.getString("branch");
		 
	}
	catch (Exception e) {
		// TODO: handle exception
		System.out.println("err="+e.getMessage());
	}
}
  /*
public void getImagesList(int hobbyId)
{
    try
    {
         DBConnector obj=new  DBConnector();
        con=obj.connect();
        csmt=con.prepareCall("{call getHobbyImages(?)}");
        lsthobby=new ArrayList<HobbyImages>();
        csmt.setInt(1, hobbyId);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lsthobby.add(new HobbyImages(rs));
              
        }
    }
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}  */

public void getSharedDocuments(String uid)
{
    try
    {
    	GetConnection obj=new  GetConnection();
        con=obj.getConnection();
        csmt=con.prepareCall("{call getsharedDoc(?)}");
        lstdocs=new ArrayList<Documents>();
         csmt.setString(1, uid);
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstdocs.add(new Documents(rs)); 
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getDocs()
{
    try
    {
    	GetConnection obj=new  GetConnection();
        con=obj.getConnection();
        csmt=con.prepareCall("{call getDocs1(?,?,?)}");
        lstdocs=new ArrayList<Documents>();
         csmt.setInt(1, Integer.parseInt(sem));
         csmt.setString(2,(branch));
         csmt.setString(3,(sub));
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstdocs.add(new Documents(rs)); 
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public void getDocs1(String userid)
{
    try
    {
    	GetConnection obj=new  GetConnection();
        con=obj.getConnection();
        csmt=con.prepareCall("{call getDocs(?)}");
        lstdocs=new ArrayList<Documents>();
         csmt.setString(1, userid); 
         csmt.execute();
         rs=csmt.getResultSet();
                     
        while(rs.next())
        { System.out.println("true");
        lstdocs.add(new Documents(rs)); 
        }
    } 
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
         
    }
}
public boolean registration()
{
    try
    { 
    	GetConnection obj=new  GetConnection();
        con=obj.getConnection();
        java.util.Date d=new java.util.Date();
        String dt1=(d.getDate()+"/"+(d.getMonth()+1)+"/"+(d.getYear()+1900));
        String tm1=d.getHours()+":"+d.getMinutes();
        csmt=con.prepareCall("{call insertDocuments(?,?,?,?,?,?,?,?,?,?,?)}");
       
        csmt.setInt(1, docId);
        csmt.setString(2, doctitle);
        csmt.setString(3, docDesc);
        csmt.setString(4, userid);
        csmt.setString(5, docpath);
        csmt.setString(6, dt1);
        csmt.setString(7, tm1);
        csmt.setString(8, category);
        csmt.setString(9, branch); 
        csmt.setString(10, sub); 
        csmt.setInt(11, Integer.parseInt(sem)); 
       
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
       
     
    catch(Exception ex)
    {
        System.out.println("err="+ex.getMessage());
        return false;
    }
}
}
