package com.example.administrator.kuaidi.Http.HttpBean;

import java.io.Serializable;
import java.util.List;

public class RepMykuaidi implements Serializable {
    private int id;
    private String number;     //单号
    private String receiver;  //收件人
    private String sender;    //发件人
    private String saddress;  //发件人地址
    private String raddress;  //收件人地址
    private String sphone;   //发件人电话
    private String rphone;   //收件人电话
    private int uid;
    private int state;    //0 快递中   1 已派件  2 已签收
    private  String user;  //
    private List<ListState> list;   //快递投递状态

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSaddress() {
        return saddress;
    }

    public void setSaddress(String saddress) {
        this.saddress = saddress;
    }

    public String getRaddress() {
        return raddress;
    }

    public void setRaddress(String raddress) {
        this.raddress = raddress;
    }

    public String getSphone() {
        return sphone;
    }

    public void setSphone(String sphone) {
        this.sphone = sphone;
    }

    public String getRphone() {
        return rphone;
    }

    public void setRphone(String rphone) {
        this.rphone = rphone;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<ListState> getList() {
        return list;
    }

    public void setList(List<ListState> list) {
        this.list = list;
    }

    public class ListState implements  Serializable{
        private int id;
        private String message;
        private String time;
        private int oid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public int getOid() {
            return oid;
        }

        public void setOid(int oid) {
            this.oid = oid;
        }
    }

}
