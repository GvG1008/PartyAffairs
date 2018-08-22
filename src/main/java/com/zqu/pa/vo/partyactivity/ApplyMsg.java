package com.zqu.pa.vo.partyactivity;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class ApplyMsg {

    private String userId;
    
    private String name;
    
    private String roleName;
    
    private String branchName;
    
    private String phoneNum;
    
    private String applyTime;
    
    private String checkName;
    
    private String checkTime;
    
    private Integer checkState;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = DateToString.getDateString("yyyy-MM-dd hh:mm:ss", applyTime);
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(Date checkName) {
        this.checkName = DateToString.getDateString("yyyy-MM-dd hh:mm:ss", checkName);
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public Integer getCheckState() {
        return checkState;
    }

    public void setCheckState(Integer checkState) {
        this.checkState = checkState;
    }
    
}
