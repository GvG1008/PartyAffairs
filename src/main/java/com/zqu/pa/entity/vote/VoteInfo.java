package com.zqu.pa.entity.vote;

public class VoteInfo {
    private Long voteId; //投票ID

    private Integer type; //投票类型 0：当选，1：多选， 2：排序

    private String title; //投票标题

    private String description; //投票补充信息（可选）

    private Long startTime; //投票开始时间

    private Long endTime; //投票截止时间

    private Integer least; //多选，最少选择几项

    private Integer most; //多选，最多选择几项

    private String createId; //创建用户ID

    private Integer status; //状态 0：创建， 1：正在进行， -1：投票结束

    private Integer anonymity; //匿名（不使用）

    public Long getVoteId() {
        return voteId;
    }

    public void setVoteId(Long voteId) {
        this.voteId = voteId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getLeast() {
        return least;
    }

    public void setLeast(Integer least) {
        this.least = least;
    }

    public Integer getMost() {
        return most;
    }

    public void setMost(Integer most) {
        this.most = most;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(Integer anonymity) {
        this.anonymity = anonymity;
    }
}