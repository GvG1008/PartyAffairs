package com.zqu.pa.entity.newsnotices;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class Notices {
    private Integer noticesId;

    private String title;

    private String date;

    private Integer type;

    private Integer state;

    private String creatorId;

    private String lastTime;

    private Integer click;

    private String coverpath;

    private String content;

    public Integer getNoticesId() {
        return noticesId;
    }

    public void setNoticesId(Integer noticesId) {
        this.noticesId = noticesId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", date);
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public String getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = DateToString.getDateString("yyyy/MM/dd HH:mm:ss", lastTime);
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getCoverpath() {
        return coverpath;
    }

    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath == null ? null : coverpath.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}