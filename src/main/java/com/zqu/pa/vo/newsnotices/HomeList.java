package com.zqu.pa.vo.newsnotices;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class HomeList {
    
    private String title;
    
    private String date;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = DateToString.getDateString("MM/dd", date);
    }
    
    
}
