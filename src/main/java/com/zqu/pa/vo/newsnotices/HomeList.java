package com.zqu.pa.vo.newsnotices;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class HomeList {
    
    private Integer id;

    private String title;
    
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
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
