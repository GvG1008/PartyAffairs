package com.zqu.pa.entity.groupalbum;

public class GroupPicture {
    private Long imageId; //图片ID

    private Long albumId; //团相册ID

    private String image; //图片地址

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }
}