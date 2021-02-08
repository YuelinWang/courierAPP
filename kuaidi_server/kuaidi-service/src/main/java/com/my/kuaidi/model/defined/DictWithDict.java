package com.my.kuaidi.model.defined;
import java.io.Serializable;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import com.my.kuaidi.model.Dict;

public class DictWithDict extends Dict  implements Serializable{
    private Long dictId;
    private String dictDictCategory;
    private String dictCode;
    private String dictName;
    private String dictDictDescription;
    private Long dictParentId;
    private Timestamp dictCreateTime;
    private Timestamp dictUpdateTime;
    private Byte dictOrderNo;
    public Long getDictId(){
       return dictId;
    }
    public void setDictId(Long dictId){
        this.dictId = dictId;
    }
    public String getDictDictCategory(){
       return dictDictCategory;
    }
    public void setDictDictCategory(String dictDictCategory){
        this.dictDictCategory = dictDictCategory;
    }
    public String getDictCode(){
       return dictCode;
    }
    public void setDictCode(String dictCode){
        this.dictCode = dictCode;
    }
    public String getDictName(){
       return dictName;
    }
    public void setDictName(String dictName){
        this.dictName = dictName;
    }
    public String getDictDictDescription(){
       return dictDictDescription;
    }
    public void setDictDictDescription(String dictDictDescription){
        this.dictDictDescription = dictDictDescription;
    }
    public Long getDictParentId(){
       return dictParentId;
    }
    public void setDictParentId(Long dictParentId){
        this.dictParentId = dictParentId;
    }
    public Timestamp getDictCreateTime(){
       return dictCreateTime;
    }
    public void setDictCreateTime(Timestamp dictCreateTime){
        this.dictCreateTime = dictCreateTime;
    }
    public Timestamp getDictUpdateTime(){
       return dictUpdateTime;
    }
    public void setDictUpdateTime(Timestamp dictUpdateTime){
        this.dictUpdateTime = dictUpdateTime;
    }
    public Byte getDictOrderNo(){
       return dictOrderNo;
    }
    public void setDictOrderNo(Byte dictOrderNo){
        this.dictOrderNo = dictOrderNo;
    }

}
