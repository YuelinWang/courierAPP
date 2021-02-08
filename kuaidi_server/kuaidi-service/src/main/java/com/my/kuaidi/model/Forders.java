package com.my.kuaidi.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.*;
import java.io.Serializable;
import lombok.Data;

@Data
public class Forders implements Serializable{

    private Integer id;//id
    private Integer uid;//用户id
    private Integer oid;//订单id


    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public Integer getUid(){
        return uid;
    }
    public void setUid(Integer uid){
        this.uid = uid;
    }

    public Integer getOid(){
        return oid;
    }
    public void setOid(Integer oid){
        this.oid = oid;
    }
}
