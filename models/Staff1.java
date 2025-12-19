package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import  beans.GetConnection;

public class Staff1 {
	
	private String userid,path;
	private String usernm;
	private String pswd;
	private String usertype;
	private String userstatus;
	private String emailid;
	private String mobileno;
	private String gender;
	private String dob;
	private String branch;
	private MultipartFile file;
	public String getUserid() {
		return userid;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernm() {
		return usernm;
	}
	
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setUsernm(String usernm) {
		this.usernm = usernm;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(String userstatus) {
		this.userstatus = userstatus;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getMobileno() {
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String updateStaff(String userid1)
	{
		GetConnection gc = new GetConnection();
		int y=0;
		
		Connection con;
		String st="";
		try {
		con=gc.getConnection();
		PreparedStatement pst;
		System.out.println("path="+path+" userid="+userid1+" email"+emailid+" mobi-"+mobileno);
		 if(!path.equals("NA"))
		 {
			pst=con.prepareStatement("update staffpersonal set mobileno=? ,emailid=?,photo=?,dob=? where userid=?");
			 
			pst.setString(1,mobileno);
			 
			pst.setString(2, emailid);
			pst.setString(3, path);
			pst.setString(4, dob);
			pst.setString(5, userid1);
		 }
		else
		{
			pst=con.prepareStatement("update staffpersonal set mobileno=?, emailid=?,dob=?  where userid=?");
			 
			pst.setString(1,mobileno);
			 
			pst.setString(2, emailid); 
			pst.setString(3, dob);
			pst.setString(4, userid1);
		}
			y=pst.executeUpdate();
		 
		
		if(y>0)
			st="success";
		else
			st="failure";
		 try{con.close();}catch(Exception ex){}	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return st;
		
		
	}
	public String addNewStaff()
	{
		GetConnection gc = new GetConnection();
		int y=0;
		
		Connection con;
		String st="";
		try {
		con=gc.getConnection();
		PreparedStatement pst;
		
		pst=con.prepareStatement("insert into users values(?,?,?,?,?,?);");

		pst.setString(1,userid);
		pst.setString(2,usernm);
		pst.setString(3,pswd);
		pst.setString(4,usertype);
		pst.setString(5,userstatus);
		pst.setString(6,branch);
		

		int x=pst.executeUpdate();
		
		if(x>0) {
			
			pst=con.prepareStatement("insert into staffpersonal values(?,?,?,?,?,?,?,?,?,?)");
			
			pst.setString(1,userid);
			pst.setString(2,usernm);
			pst.setString(3,usertype);
			pst.setString(4, branch);
			pst.setString(5,mobileno);
			pst.setString(6,emailid);
			pst.setString(7, dob);
			pst.setString(8, gender);
			pst.setString(9, userstatus);
			pst.setString(10, "common.png");

			y=pst.executeUpdate();
		}
		else
			st="failure";
		
		if(y>0)
			st="success";
		else
			st="failure";
		 try{con.close();}catch(Exception ex){}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return st;
	}
public List<Staff> getStaff(String userid1){
		
		GetConnection gc = new GetConnection();
		Connection con;
		ResultSet rs;
		Staff vs;
		List<Staff> lst=new ArrayList<Staff>();
		LoginTracker login=new LoginTracker();
		try {
			
			con=gc.getConnection();
			
			Statement st;
			st=con.createStatement();
			
			rs=st.executeQuery("select * from staffpersonal where userid='"+userid1+"'");
			
			while(rs.next()) {
				
				vs= new Staff();
				vs.setUserid(rs.getString("userid"));
				vs.setUsernm(rs.getString("usernm"));
				vs.setEmailid(rs.getString("emailid"));
				vs.setMobileno(rs.getString("mobileno"));
				vs.setGender(rs.getString("gender"));
				vs.setBranch(rs.getString("branch"));
				vs.setDob(rs.getString("dob"));
			 
				lst.add(vs);
			}
			 try{con.close();}catch(Exception ex){}
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return(lst);
	}
}
