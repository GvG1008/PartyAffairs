package com.zqu.pa.vo.newsnotices;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class MeetingInfo {
    
    private int id;
    
    private int branchId;

    private String title;
    
    private String date;
    
    private String creatorId;
    
    private String creator; //录入人名字

    private int state;
    
    private String lastTime;
    
    private int click;
    
    private String coverpath;
    
    private String video;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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
        this.date = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", date);
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
    
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", lastTime);
    }

    public int getClick() {
        return click;
    }

    public void setClick(int click) {
        this.click = click;
    }

    public String getCoverpath() {
        return coverpath;
    }

    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath;
    }
    
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}

