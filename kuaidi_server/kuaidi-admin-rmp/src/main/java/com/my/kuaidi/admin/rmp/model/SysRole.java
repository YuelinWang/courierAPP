package com.my.kuaidi.admin.rmp.model;

import java.io.Serializable;



public class SysRole implements Serializable{

    private Long id;//主键
    private String roleName;//角色名

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getRoleName(){
        return roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

}
