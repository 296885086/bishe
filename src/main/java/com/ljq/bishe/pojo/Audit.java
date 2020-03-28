package com.ljq.bishe.pojo;

public class Audit {
    private String auditid;
    private String stuid;
    private String workid;
    private String coursename;
    private String courseclass;
    private String commitdate;
    private String commitbody;
    private String replybody;
    private String state;
    private String workname;
    private String score;
    public String getAuditid() {
        return auditid;
    }

    public void setAuditid(String auditid) {
        this.auditid = auditid;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getCommitdate() {
        return commitdate;
    }

    public void setCommitdate(String commitdate) {
        this.commitdate = commitdate;
    }

    public String getCommitbody() {
        return commitbody;
    }

    public void setCommitbody(String commitbody) {
        this.commitbody = commitbody;
    }

    public String getReplybody() {
        return replybody;
    }

    public void setReplybody(String replybody) {
        this.replybody = replybody;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCourseclass() {
        return courseclass;
    }

    public void setCourseclass(String courseclass) {
        this.courseclass = courseclass;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getWorkid() {
        return workid;
    }

    public void setWorkid(String workid) {
        this.workid = workid;
    }
}
