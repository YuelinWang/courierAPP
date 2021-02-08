package com.example.administrator.kuaidi.DatabaseModel;

import org.litepal.crud.LitePalSupport;

public class NoticesModel extends LitePalSupport {
    private int nYear;
    private int nMonth;
    private String strContent;
    private long  lTime;

    public int getnMonth() {
        return nMonth;
    }

    public void setnMonth(int nMonth) {
        this.nMonth = nMonth;
    }

    public String getStrContent() {
        return strContent;
    }

    public void setStrContent(String strContent) {
        this.strContent = strContent;
    }

    public long getlTime() {
        return lTime;
    }

    public void setlTime(long lTime) {
        this.lTime = lTime;
    }

    public int getnYear() {
        return nYear;
    }

    public void setnYear(int nYear) {
        this.nYear = nYear;
    }
}
