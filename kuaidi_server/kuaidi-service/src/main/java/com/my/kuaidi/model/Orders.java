package com.my.kuaidi.model;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.*;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Orders implements Serializable{

    private Integer id;//id
    private String number;//快递单号
    private String receiver;//收货人
    private String sender;//发货人
    private String saddress;//发货地址
    private String raddress;//收获地址
    private String sphone;//发货人手机号
    private String rphone;//收货人手机号
    private Integer uid;//寄件人id
    private Integer state;//0为快递中1为已派件2以签收
    private User user;//这个订单的授权人


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private List<Logistics> list;


    public List<Logistics> getList() {
        return list;
    }

    public void setList(List<Logistics> list) {
        this.list = list;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }

    public String getNumber(){
        return number;
    }
    public void setNumber(String number){
        this.number = number;
    }

    public String getReceiver(){
        return receiver;
    }
    public void setReceiver(String receiver){
        this.receiver = receiver;
    }

    public String getSender(){
        return sender;
    }
    public void setSender(String sender){
        this.sender = sender;
    }

    public String getSaddress(){
        return saddress;
    }
    public void setSaddress(String saddress){
        this.saddress = saddress;
    }

    public String getRaddress(){
        return raddress;
    }
    public void setRaddress(String raddress){
        this.raddress = raddress;
    }

    public String getSphone(){
        return sphone;
    }
    public void setSphone(String sphone){
        this.sphone = sphone;
    }

    public String getRphone(){
        return rphone;
    }
    public void setRphone(String rphone){
        this.rphone = rphone;
    }

    public Integer getUid(){
        return uid;
    }
    public void setUid(Integer uid){
        this.uid = uid;
    }

    public Integer getState(){
        return state;
    }
    public void setState(Integer state){
        this.state = state;
    }
}
