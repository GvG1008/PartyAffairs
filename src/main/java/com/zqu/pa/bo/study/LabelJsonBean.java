package com.zqu.pa.bo.study;

public class LabelJsonBean {
    private String labelId;

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public LabelJsonBean(String labelId) {
        super();
        this.labelId = labelId;
    }

    public LabelJsonBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "LabelJsonBean [labelId=" + labelId + "]";
    }
    
}
