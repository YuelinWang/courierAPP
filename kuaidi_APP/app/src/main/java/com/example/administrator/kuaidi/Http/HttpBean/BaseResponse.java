package com.example.administrator.kuaidi.Http.HttpBean;

public class BaseResponse<T> {
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    /**
     * 0：成功，1：失败
     */
    private boolean flag;

    private int code;

    private String desc;

    private Object count;

    private T data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getCount() {
        return count;
    }

    public void setCount(Object count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
