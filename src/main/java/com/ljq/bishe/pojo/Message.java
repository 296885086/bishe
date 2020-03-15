package com.ljq.bishe.pojo;

public class Message {
    private int messageid;
    private String leaveid;
    private String leavename;
    private String messagebody;
    private String leavedate;
    private int totalreply;
    private int praisenumber;
    private String messagetype;
    private String courseid;
    public String getLeaveid() {
        return leaveid;
    }

    public void setLeaveid(String leaveid) {
        this.leaveid = leaveid;
    }

    public String getMessagebody() {
        return messagebody;
    }

    public void setMessagebody(String messagebody) {
        this.messagebody = messagebody;
    }

    public String getMessagetype() {
        return messagetype;
    }

    public void setMessagetype(String messagetype) {
        this.messagetype = messagetype;
    }

    public int getMessageid() {
        return messageid;
    }

    public void setMessageid(int messageid) {
        this.messageid = messageid;
    }

    public int getTotalreply() {
        return totalreply;
    }

    public void setTotalreply(int totalreply) {
        this.totalreply = totalreply;
    }

    public int getPraisenumber() {
        return praisenumber;
    }

    public void setPraisenumber(int praisenumber) {
        this.praisenumber = praisenumber;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }

    public String getLeavename() {
        return leavename;
    }

    public void setLeavename(String leavename) {
        this.leavename = leavename;
    }

    public String getLeavedate() {
        return leavedate;
    }

    public void setLeavedate(String leavedate) {
        this.leavedate = leavedate;
    }
}
