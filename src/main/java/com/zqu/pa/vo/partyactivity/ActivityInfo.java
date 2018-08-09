package com.zqu.pa.vo.partyactivity;

import com.zqu.pa.utils.DateUtil;

public class ActivityInfo {
    
    private Integer activityId;
    
    private String name;
    
    private String content;
    
    private Integer num;
    
    private String address;
    
    private String unit;
    
    private String registrationStart;
    
    private String registrationEnd;
    
    private String activityStart;
    
    private String activityEnd;
    
    private Integer alreadyJoinNum; //已经成功参加的人数

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(Long registrationStart) {
        this.registrationStart = DateUtil.formatTime2("yyyy/MM/dd hh:mm:ss", registrationStart);
    }

    public String getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(Long registrationEnd) {
        this.registrationEnd = DateUtil.formatTime2("yyyy/MM/dd hh:mm:ss", registrationEnd);
    }

    public String getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Long activityStart) {
        this.activityStart = DateUtil.formatTime2("yyyy/MM/dd hh:mm:ss", activityStart);
    }

    public String getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(Long activityEnd) {
        this.activityEnd = DateUtil.formatTime2("yyyy/MM/dd hh:mm:ss", activityEnd);
    }

    public Integer getAlreadyJoinNum() {
        return alreadyJoinNum;
    }

    public void setAlreadyJoinNum(Integer alreadyJoinNum) {
        this.alreadyJoinNum = alreadyJoinNum;
    }
    
}
