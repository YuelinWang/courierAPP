package com.my.kuaidi.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.*;
import java.io.Serializable;
import lombok.Data;
/**
* 代码为自动生成 Created by www.magicalcoder.com
* 如果你改变了此类 read 请将此行删除
* 欢迎加入官方QQ群:323237052
*/
@Data
public class User implements Serializable{

    private Integer id;//id
    private String username;//用户名，手机号
    private String password;//密码
    private String realname;//姓名
    private Integer state;//状态0正常1禁用
    private String phone;//电话号


    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getRealname(){
        return realname;
    }
    public void setRealname(String realname){
        this.realname = realname;
    }

    public Integer getState(){
        return state;
    }
    public void setState(Integer state){
        this.state = state;
    }

    public String getPhone(){
        return phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
}
