package com.my.kuaidi.admin.rmp.model.defined;


import com.my.kuaidi.admin.rmp.model.SysAdminUser;

import java.io.Serializable;


public class SysAdminUserWithSysRole extends SysAdminUser implements Serializable{
        private Long sysRoleId;
    private String roleName;
    public Long getSysRoleId(){
       return sysRoleId;
    }
    public void setSysRoleId(Long sysRoleId){
        this.sysRoleId = sysRoleId;
    }
    public String getRoleName(){
       return roleName;
    }
    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

}
