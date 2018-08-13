package com.zqu.pa.vo.partyactivity;

import com.zqu.pa.entity.partyactivity.PartyActivity;

public class ActivityManageMenu extends PartyActivity{
  
    private String branchName; //活动所属党支部名称
    
    private String createName; //创建人姓名
    
    private Integer alreadyJoinNum; //已经加入的人数
    
    private Integer applyNum; //申请审核的人数
    
    private String activityState; //活动当前状态信息

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Integer getAlreadyJoinNum() {
        return alreadyJoinNum;
    }

    public void setAlreadyJoinNum(Integer alreadyJoinNum) {
        this.alreadyJoinNum = alreadyJoinNum;
    }

    public Integer getApplyNum() {
        return applyNum;
    }

    public void setApplyNum(Integer applyNum) {
        this.applyNum = applyNum;
    }

    public String getActivityState() {
        return activityState;
    }

    public void setActivityState(String activityState) {
        this.activityState = activityState;
    }
}
