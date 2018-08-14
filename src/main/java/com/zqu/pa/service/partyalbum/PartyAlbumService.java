package com.zqu.pa.service.partyalbum;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyPicture;

public interface PartyAlbumService {

    //获取某个党支部下的所有相册列表
    List<PartyAlbum> listAlbum(Integer branchId);
    
    //根据相册ID获取某个相册下的所有图片
    Map<String, Object> listAlbumPicture(Long albumId);
    
    //相册浏览量加1
    void addPageviews(Long albumId);
    
    //创建一个党活动相册
    ServerResponse createAlbum(PartyAlbum partyAlbum, MultipartFile[] files, HttpServletRequest request);
    
    //删除一个党活动相册
    ServerResponse removeAlbum(Long albumId);
 }
