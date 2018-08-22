package com.zqu.pa.vo.partyactivity;

import com.zqu.pa.entity.partyactivity.PartyActivityUser;

public class CheckApplyMenu  extends PartyActivityUser{

    private String userName;
    
    private String sex;
    
    private Integer branchId;
    
    private String branchName;
    
    private String className;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    
}
