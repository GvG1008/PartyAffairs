package com.zqu.pa.entity.partyactivity;

public class PartyActivityUserKey {
    private Integer activityId; //活动ID

    private String userId; //用户ID

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}