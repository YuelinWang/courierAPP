package com.my.kuaidi.admin.rmp.model.defined;

import com.my.kuaidi.admin.rmp.model.SysRoleModulePermission;

import java.io.Serializable;


public class SysRoleModulePermissionWithSysPermission extends SysRoleModulePermission  implements Serializable{
        private Long sysPermissionId;
    private String permissionName;
    private String filterPlatform;
    private String backendUrlReg;
    private String frontDom;
    private String frontAction;
    private Long sysPermissionModuleId;
    public Long getSysPermissionId(){
       return sysPermissionId;
    }
    public void setSysPermissionId(Long sysPermissionId){
        this.sysPermissionId = sysPermissionId;
    }
    public String getPermissionName(){
       return permissionName;
    }
    public void setPermissionName(String permissionName){
        this.permissionName = permissionName;
    }
    public String getFilterPlatform(){
       return filterPlatform;
    }
    public void setFilterPlatform(String filterPlatform){
        this.filterPlatform = filterPlatform;
    }
    public String getBackendUrlReg(){
       return backendUrlReg;
    }
    public void setBackendUrlReg(String backendUrlReg){
        this.backendUrlReg = backendUrlReg;
    }
    public String getFrontDom(){
       return frontDom;
    }
    public void setFrontDom(String frontDom){
        this.frontDom = frontDom;
    }
    public String getFrontAction(){
       return frontAction;
    }
    public void setFrontAction(String frontAction){
        this.frontAction = frontAction;
    }
    public Long getSysPermissionModuleId(){
       return sysPermissionModuleId;
    }
    public void setSysPermissionModuleId(Long sysPermissionModuleId){
        this.sysPermissionModuleId = sysPermissionModuleId;
    }

}
