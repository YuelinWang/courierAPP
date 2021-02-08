package com.my.kuaidi.admin.rmp.model;

import java.io.Serializable;



public class SysModuleCategory implements Serializable{

    private Long id;//主键
    private String moduleCategoryName;//模块名称
    private Integer sortNum;//排序

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getModuleCategoryName(){
        return moduleCategoryName;
    }
    public void setModuleCategoryName(String moduleCategoryName){
        this.moduleCategoryName = moduleCategoryName;
    }
    public Integer getSortNum(){
        return sortNum;
    }
    public void setSortNum(Integer sortNum){
        this.sortNum = sortNum;
    }

}
