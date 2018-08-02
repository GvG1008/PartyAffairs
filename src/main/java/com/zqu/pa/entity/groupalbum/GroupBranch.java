package com.zqu.pa.entity.groupalbum;

public class GroupBranch {
    private Integer groupId; //团支部ID

    private String groupName; //团支部名称

    private String userId; //对应团支部管理员ID

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}