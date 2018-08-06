package com.zqu.pa.service.groupalbum.Impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.groupalbum.GroupAlbumMapper;
import com.zqu.pa.dao.groupalbum.GroupPictureMapper;
import com.zqu.pa.entity.groupalbum.GroupAlbum;
import com.zqu.pa.entity.groupalbum.GroupAlbumExample;
import com.zqu.pa.entity.groupalbum.GroupPicture;
import com.zqu.pa.entity.groupalbum.GroupPictureExample;
import com.zqu.pa.service.groupalbum.GroupAlbumService;
import com.zqu.pa.utils.DateUtil;

@Service
public class GroupAlbumServiceImpl implements GroupAlbumService {

    private Logger logger = LoggerFactory.getLogger(GroupAlbumServiceImpl.class);
    
    @Autowired
    private GroupAlbumMapper groupAlbumMapper;
    
    @Autowired
    private GroupPictureMapper groupPictureMapper;
    
    @Override
    public List<GroupAlbum> listAlbum(Integer groupId) {
        
        GroupAlbumExample example = new GroupAlbumExample();
        example.createCriteria().andGroupIdEqualTo(groupId);
        List<GroupAlbum> listAlbum = new ArrayList<>();
        listAlbum = groupAlbumMapper.selectByExample(example);
        if (listAlbum == null || listAlbum.size() == 0) {
            logger.info("团支部ID：" + groupId + " 下无活动相册");
            return listAlbum;
        }        
        for (GroupAlbum groupAlbum : listAlbum) {
            groupAlbum.setStringCreateDate(DateUtil.formatTime(groupAlbum.getCreateDate()));
        }
        return listAlbum;
    }

    @Override
    public List<GroupPicture> listAlbumPicture(Long albumId) {
        
        GroupPictureExample example = new GroupPictureExample();
        example.createCriteria().andAlbumIdEqualTo(albumId);
        List<GroupPicture> listPicture = new ArrayList<>();
        listPicture = groupPictureMapper.selectByExample(example);
        if (listPicture == null || listPicture.size() == 0) {
            logger.info("团相册ID：" + albumId + " 下无图片");
            return listPicture;
        }
        addPageviews(albumId);
        return listPicture;
    }

    @Override
    public void addPageviews(Long albumId) {
        
        groupAlbumMapper.addPageviews(albumId);
    }

}
