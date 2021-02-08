package com.my.kuaidi.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.math.*;
import java.io.Serializable;
import lombok.Data;

@Data
public class Friend implements Serializable{

    private Integer id;//id
    private Integer uid;//用户id
    private Integer fid;//好友id
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp time;//添加好友时间


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

    public Integer getFid(){
        return fid;
    }
    public void setFid(Integer fid){
        this.fid = fid;
    }

    public Timestamp getTime(){
        return time;
    }
    public void setTime(Timestamp time){
        this.time = time;
    }
}
