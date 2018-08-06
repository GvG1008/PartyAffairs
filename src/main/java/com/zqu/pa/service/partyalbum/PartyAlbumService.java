package com.zqu.pa.service.partyalbum;

import java.util.List;

import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyPicture;

public interface PartyAlbumService {

    //获取某个党支部下的所有相册列表
    List<PartyAlbum> listAlbum(Integer branchId);
    
    //根据相册ID获取某个相册下的所有图片
    List<PartyPicture> listAlbumPicture(Long albumId);
    
    //相册浏览量加1
    void addPageviews(Long albumId);
}
