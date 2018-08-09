package com.zqu.pa.service.groupalbum;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.groupalbum.GroupAlbum;
import com.zqu.pa.entity.groupalbum.GroupPicture;

public interface GroupAlbumService {

    //获取某个团支部下的所有相册列表
    List<GroupAlbum> listAlbum(Integer groupId);
    
    //根据相册ID获取某个相册下的所有图片
    List<GroupPicture> listAlbumPicture(Long albumId);
    
    //相册浏览量加1
    void addPageviews(Long albumId);
    
    //创建一个团活动相册
    ServerResponse createAlbum(GroupAlbum groupAlbum, MultipartFile[] files, HttpServletRequest request);
    
    //删除一个团活动相册
    ServerResponse removeAlbum(Long albumId);
}
