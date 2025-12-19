package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import  beans.GetConnection;
import beans.Mail;

public class Staff {
	
	private String userid;
	private String usernm;
	private String pswd;
	private String usertype;
	private String userstatus;
	private String emailid;
	private String mobileno;
	private String gender;
	private String dob;
	private String branch;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsernm() {
		return usernm;
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

		pst.setString(1,userid.trim());
		pst.setString(2,usernm.trim());
		pst.setString(3,pswd.trim());
		pst.setString(4,usertype.trim());
		pst.setString(5,userstatus);
		pst.setString(6,branch.trim());
		

		int x=pst.executeUpdate();
		
		if(x>0) {
			
			pst=con.prepareStatement("insert into staffpersonal values(?,?,?,?,?,?,?,?,?,?)");
			
			pst.setString(1,userid.trim());
			pst.setString(2,usernm.trim());
			pst.setString(3,usertype.trim());
			pst.setString(4, branch.trim());
			pst.setString(5,mobileno.trim());
			pst.setString(6,emailid.trim());
			pst.setString(7, dob);
			pst.setString(8, gender);
			pst.setString(9, userstatus);
			pst.setString(10, "common.png");

			y=pst.executeUpdate();
			Mail mail=new Mail();
			String msg="Dear "+usernm+", your userid is "+userid+" and password is "+pswd;
			try
			{
				if(mail.sendMail(msg, emailid, "Login Credentials")){}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
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
public List<Staff> getStaffList(){
	
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
		
		rs=st.executeQuery("select * from staffpersonal");
		
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
public List<Staff> getStaffList1(String br){
	
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
		
		rs=st.executeQuery("select * from staffpersonal where branch='"+br+"'  and usertype='staff'");
		
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
