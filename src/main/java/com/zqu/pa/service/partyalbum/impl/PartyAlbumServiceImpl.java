package com.zqu.pa.service.partyalbum.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.partyalbum.PartyAlbumMapper;
import com.zqu.pa.dao.partyalbum.PartyPictureMapper;
import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyAlbumExample;
import com.zqu.pa.entity.partyalbum.PartyPicture;
import com.zqu.pa.entity.partyalbum.PartyPictureExample;
import com.zqu.pa.service.partyalbum.PartyAlbumService;
import com.zqu.pa.utils.DateUtil;

@Service
public class PartyAlbumServiceImpl implements PartyAlbumService {

    private Logger logger = LoggerFactory.getLogger(PartyAlbumServiceImpl.class);
    
    @Autowired
    private PartyAlbumMapper partyAlbumMapper;
    
    @Autowired
    private PartyPictureMapper partyPictureMapper;
    
    @Override
    public List<PartyAlbum> listAlbum(Integer branchId) {
        
        PartyAlbumExample example = new PartyAlbumExample();
        example.createCriteria().andBranchIdEqualTo(branchId);
        List<PartyAlbum> listAlbum = new ArrayList<>();
        listAlbum = partyAlbumMapper.selectByExample(example);
        if (listAlbum == null || listAlbum.size() == 0) {
            logger.info("党支部ID：" + branchId + " 下无活动相册");
            return listAlbum;
        }        
        for (PartyAlbum partyAlbum : listAlbum) {
            partyAlbum.setStringCreateDate(DateUtil.formatTime(partyAlbum.getCreateDate()));
        }
        return listAlbum;
    }

    @Override
    public List<PartyPicture> listAlbumPicture(Long albumId) {
        
        PartyPictureExample example = new PartyPictureExample();
        example.createCriteria().andAlbumIdEqualTo(albumId);
        List<PartyPicture> listPicture = new ArrayList<>();
        listPicture = partyPictureMapper.selectByExample(example);
        if (listPicture == null || listPicture.size() == 0) {
            logger.info("相册ID：" + albumId + " 下无图片");
            return listPicture;
        }
        addPageviews(albumId);
        return listPicture;
    }

    @Override
    public void addPageviews(Long albumId) {
        
        partyAlbumMapper.addPageviews(albumId);
    }

}
