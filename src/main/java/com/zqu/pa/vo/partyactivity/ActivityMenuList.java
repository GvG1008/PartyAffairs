package com.zqu.pa.vo.partyactivity;

import java.util.Date;

import com.zqu.pa.utils.StringTimeToLong;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.utils.DateUtil;

public class ActivityMenuList {

    private Integer activityId; //活动ID
    
    private String name; //活动名称
    
    private Integer num; //活动人数
    
    private String releaseUnit; //活动发布单位
    
    private String createTime; //创建时间
    
    private String registrationEnd; //报名截止时间
    
    private Integer state; //报名状态码 1可报名，0报名已截止

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

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateToString.getDateString("yyyy/MM/dd hh:mm:ss", createTime);
    }
    
    public String getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(Long registrationEnd) {
        if(new Date().getTime()>registrationEnd)
            state=0;
        else
            state=1;
        this.registrationEnd = DateUtil.formatTime2("yyyy/MM/dd hh:mm:ss",registrationEnd);
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
