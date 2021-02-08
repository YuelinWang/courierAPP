package com.my.kuaidi.admin.rmp.model.defined;

import com.my.kuaidi.admin.rmp.model.SysModule;

import java.io.Serializable;


public class SysModuleWithSysModuleCategory extends SysModule  implements Serializable{
        private Long sysModuleCategoryId;
    private String moduleCategoryName;
    private Integer sysModuleCategorySortNum;
    public Long getSysModuleCategoryId(){
       return sysModuleCategoryId;
    }
    public void setSysModuleCategoryId(Long sysModuleCategoryId){
        this.sysModuleCategoryId = sysModuleCategoryId;
    }
    public String getModuleCategoryName(){
       return moduleCategoryName;
    }
    public void setModuleCategoryName(String moduleCategoryName){
        this.moduleCategoryName = moduleCategoryName;
    }
    public Integer getSysModuleCategorySortNum(){
       return sysModuleCategorySortNum;
    }
    public void setSysModuleCategorySortNum(Integer sysModuleCategorySortNum){
        this.sysModuleCategorySortNum = sysModuleCategorySortNum;
    }

}
