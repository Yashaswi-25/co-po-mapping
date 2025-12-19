package models;
import java.sql.*;
import java.util.List;
import java.util.Vector;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import beans.GetConnection;
 
public class CheckUser {

	private String userid;
	private String pswd;
		
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
	public String checkUser(HttpServletRequest request) {
		
		Connection con;
		ResultSet rs;
		String typ,st="";
		GetConnection gc = new GetConnection();
		
		
		try {
			
			
			con=gc.getConnection();
			PreparedStatement pst;
			pst=con.prepareStatement("select * from users where userid=? and pswd=? and userstatus='active' ");
			pst.setString(1,userid );
			pst.setString(2, pswd);
				
			rs=pst.executeQuery();
			
			if(rs.next()) {
								
				
				HttpSession sess=request.getSession(true);
				sess.setAttribute("userid",userid);
				sess.setAttribute("usertype", rs.getString("usertype"));
				sess.setAttribute("branch", rs.getString("branch"));
				System.out.println("branch="+rs.getString("branch"));
				
				typ=rs.getString("usertype");
				sess.setAttribute("photo", getPhoto(userid, typ));
				System.out.println("type="+typ);
				LoginTracker lt=new LoginTracker();
				lt.recordLogin(userid, typ);
				if(typ.equals("staff"))
					st="staff";
				else if(typ.equals("branchadmin"))
					st="branchadmin";
				else if(typ.equals("student"))
				{
					JavaFuns jf=new JavaFuns();
					Vector v=jf.getValue("select semester,course,branch,emailid,usernm from studentpersonal where userid='"+userid.trim()+"'", 5);
					sess.setAttribute("sem",v.elementAt(0).toString().trim());
					sess.setAttribute("branch",v.elementAt(2).toString().trim());
					sess.setAttribute("course",v.elementAt(1).toString().trim());
					sess.setAttribute("email",v.elementAt(3).toString().trim());
					sess.setAttribute("username",v.elementAt(4).toString().trim());
					st="student";
				}
				else if(typ.equals("admin"))
					st="admin";
				else
					st=typ;
			}
			else
				st="LoginFailure.jsp";
			 try{con.close();}catch(Exception ex){}
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return(st);	
	}
public String getPhoto(String userid,String utype) {
		String photo="common.png";
		Connection con;
		ResultSet rs;
		String typ,st="";
		GetConnection gc = new GetConnection();
		
		
		try {
			
			
			con=gc.getConnection();
			PreparedStatement pst;
			String qr="";
			if(utype.equals("student"))
			{
				qr="select photo from studentpersonal where userid='"+userid+"'";
			}
			else
				qr="select photo from staffpersonal where userid='"+userid+"'";

			pst=con.prepareStatement(qr);
			 
			rs=pst.executeQuery();
			
			if(rs.next()) { 
				photo=rs.getString("photo");
				 
			}
			 try{con.close();}catch(Exception ex){}
		}
		
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return(photo);	
	}
}
