package com.ljq.bishe.pojo;

public class  Logininfo {
    private String username;
    private String password;
    private String logintype;
    private String msg;
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getLogintype() {
        return logintype;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
