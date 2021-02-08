package com.example.administrator.kuaidi.ModelBean;

import java.io.Serializable;

public class UserInfoItem implements Serializable {
    private int id;
    private String username;
    private String password;
    private String realname;
    private int   state;
    private String phone;
    private int  status;   //0:在线的用户  显示添加好友按钮， 1:已是好友 两个控件都不用显示 2:授权界面 显示checkbox 不显示添加好友按钮

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
