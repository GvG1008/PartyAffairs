package com.zqu.pa.entity.groupalbum;

public class GroupAlbum {
    private Long albumId; //相册ID

    private Integer groupId; //团支部ID

    private String albumTitle; //相册标题

    private String coverImage; //封面图片地址

    private String description; //相册描述

    private Integer quantity; //相册图片数量

    private String userId; //创建者ID

    private Long createDate; //相册创建时间戳

    private Integer pageviews; //相册浏览量
    
    private String stringCreateDate; //相册创建时间格式化字符串

    public String getStringCreateDate() {
        return stringCreateDate;
    }

    public void setStringCreateDate(String stringCreateDate) {
        this.stringCreateDate = stringCreateDate;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle == null ? null : albumTitle.trim();
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage == null ? null : coverImage.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Integer getPageviews() {
        return pageviews;
    }

    public void setPageviews(Integer pageviews) {
        this.pageviews = pageviews;
    }
}