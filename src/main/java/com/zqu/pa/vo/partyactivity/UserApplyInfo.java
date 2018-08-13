package com.zqu.pa.vo.partyactivity;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class UserApplyInfo {

    private String userId;
    
    private Integer activityId;
    
    private String name; //活动名称
    
    private String submitTime; //报名提交时间
    
    private String phoneNum; //联系号码
    
    private Integer checkState; //审核状态：1审核通过，0未审核，-1审核不通过
    
    private String checkInfo; //审核信息返回 
    
    private Integer allowDelete; //是否允许撤销审核，未审核时允许删除报名信息

    private Integer isDelete; //该活动是否已被删除 1为已删除

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = DateToString.getDateString("MM/dd hh:mm", submitTime);
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        if(checkState==1) {
            checkInfo = "审核通过";
            allowDelete = 0;
        }
        else if(checkState==0) {
            checkInfo = "等待审核";
            allowDelete = 1;
        }
        else if(checkState==-1) {
            checkInfo = "审核未通过";
            allowDelete = 0;
        }
        this.checkState = checkState;
    }

    public String getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(String checkInfo) {
        this.checkInfo = checkInfo;
    }

    public Integer getAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(Integer allowDelete) {
        this.allowDelete = allowDelete;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
    
    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        if(isDelete==1) {
            checkInfo = "该活动已删除";
            allowDelete = 1;
        }
        this.isDelete = isDelete;
    }
}
