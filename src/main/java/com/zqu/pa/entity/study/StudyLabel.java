package com.zqu.pa.entity.study;

import java.util.Date;

public class StudyLabel {
    private Integer labelId;

    private String labelName;

    private Date createtime;

    private Date updatetime;

    public StudyLabel(Integer labelId, String labelName, Date createtime, Date updatetime) {
        this.labelId = labelId;
        this.labelName = labelName;
        this.createtime = createtime;
        this.updatetime = updatetime;
    }

    public StudyLabel() {
        super();
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName == null ? null : labelName.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        return "StudyLabel [labelId=" + labelId + ", labelName=" + labelName + ", createtime=" + createtime
                + ", updatetime=" + updatetime + "]";
    }
    
    
}