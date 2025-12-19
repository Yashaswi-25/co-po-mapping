package models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.GetConnection;

public class CourseOutcome {
    
    private int pid;
    private String program;
    private String branch;
    private int semester;
    private String subjectName;
    private String outcome;
    
    Connection con;
    CallableStatement csmt;
    ResultSet rs;
    List<CourseOutcome> lstOutcomes;

    public CourseOutcome() {
    }

    public CourseOutcome(ResultSet rs) {
        try {
            this.pid = rs.getInt("pid");
            this.program = rs.getString("program").trim();
            this.branch = rs.getString("branch").trim();
            this.semester = rs.getInt("sem");
            this.subjectName = rs.getString("subject_name").trim();
            this.outcome = new String(rs.getBytes("outcome")); // Handling BLOB as String
        } catch (Exception e) {
            System.out.println("Error in CourseOutcome constructor: " + e.getMessage());
        }
    }

    // Getter and Setter methods
    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public List<CourseOutcome> getLstOutcomes() {
        return lstOutcomes;
    }

    public void setLstOutcomes(List<CourseOutcome> lstOutcomes) {
        this.lstOutcomes = lstOutcomes;
    }

    // Method to register a new Course Outcome
    public String registerCourseOutcome() {
        PreparedStatement pst;
        GetConnection gc = new GetConnection();
        String status = "";

        try {
            con = gc.getConnection();
            pst = con.prepareStatement("INSERT INTO course_outcomes (program, branch, sem, subject_name, outcome) VALUES (?, ?, ?, ?, ?)");
            
            pst.setString(1, getProgram());
            pst.setString(2, getBranch());
            pst.setInt(3, getSemester());
            pst.setString(4, getSubjectName());
            pst.setBytes(5, getOutcome().getBytes()); // Converting String to BLOB

            int result = pst.executeUpdate();
            try { con.close(); } catch (Exception ex) {}

            if (result > 0)
                status = "Success.jsp";
            else
                status = "Failure.jsp";

        } catch (Exception ex) {
            System.out.println("Error in registerCourseOutcome: " + ex.getMessage());
            ex.printStackTrace();
        }
        return status;
    }

    // Method to fetch all Course Outcomes
    public void getCourseOutcomes() {
        try {
            GetConnection obj = new GetConnection();
            con = obj.getConnection();
            csmt = con.prepareCall("{call getCourseOutcomes()}"); // Assuming stored procedure
            csmt.execute();
            rs = csmt.getResultSet();
            lstOutcomes = new ArrayList<CourseOutcome>();

            while (rs.next()) {
                lstOutcomes.add(new CourseOutcome(rs));
            }
        } catch (Exception ex) {
            System.out.println("Error in getCourseOutcomes: " + ex.getMessage());
        }
    }
}
