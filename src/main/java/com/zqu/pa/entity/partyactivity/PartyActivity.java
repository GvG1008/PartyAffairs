package com.zqu.pa.entity.partyactivity;

import java.util.Date;

import com.zqu.pa.utils.DateToString;

public class PartyActivity {
    private Integer activityId; //活动ID

    private Integer branchId; //党支部ID

    private String name; //活动名称

    private Integer num; //活动人数

    private String address; //活动地点

    private String releaseUnit; //活动发布单位

    private String coverpath; //封面图保留字段

    private String createTime; //创建时间

    private String createId; //创建人ID

    private Long registrationStart; //报名开始时间

    private Long registrationEnd; //报名截止时间

    private Long activityStart; //活动开始时间

    private Long activityEnd; //活动结束时间

    private String activityResult; //活动结果总结

    private Integer isDelete; //是否删除

    private String content; //活动内容

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        this.address = address == null ? null : address.trim();
    }

    public String getReleaseUnit() {
        return releaseUnit;
    }

    public void setReleaseUnit(String releaseUnit) {
        this.releaseUnit = releaseUnit == null ? null : releaseUnit.trim();
    }

    public String getCoverpath() {
        return coverpath;
    }

    public void setCoverpath(String coverpath) {
        this.coverpath = coverpath == null ? null : coverpath.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateToString.getDateString("yyyy/MM/dd hh:mm:ss", createTime);
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Long getRegistrationStart() {
        return registrationStart;
    }

    public void setRegistrationStart(Long registrationStart) {
        this.registrationStart = registrationStart;
    }

    public Long getRegistrationEnd() {
        return registrationEnd;
    }

    public void setRegistrationEnd(Long registrationEnd) {
        this.registrationEnd = registrationEnd;
    }

    public Long getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(Long activityStart) {
        this.activityStart = activityStart;
    }

    public Long getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(Long activityEnd) {
        this.activityEnd = activityEnd;
    }

    public String getActivityResult() {
        return activityResult;
    }

    public void setActivityResult(String activityResult) {
        this.activityResult = activityResult == null ? null : activityResult.trim();
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}