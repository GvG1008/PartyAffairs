package com.zqu.pa.entity.exam;

public class ExamCategory {
    private Integer categoryId; //题库分类ID

    private String categoryName; //题库分类名称

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }
}