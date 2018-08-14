package com.zqu.pa.service.partyalbum.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.partyalbum.PartyAlbumMapper;
import com.zqu.pa.dao.partyalbum.PartyPictureMapper;
import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyAlbumExample;
import com.zqu.pa.entity.partyalbum.PartyPicture;
import com.zqu.pa.entity.partyalbum.PartyPictureExample;
import com.zqu.pa.service.partyalbum.PartyAlbumService;
import com.zqu.pa.utils.DateUtil;
import com.zqu.pa.utils.FTPSSMLoad;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

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
    public Map<String, Object> listAlbumPicture(Long albumId) {
        
        PartyAlbumExample example1 = new PartyAlbumExample();
        example1.createCriteria().andAlbumIdEqualTo(albumId);
        PartyAlbum partyAblum = partyAlbumMapper.selectByPrimaryKey(albumId);
        Map<String, Object> map = new HashMap<>();
        if (partyAblum == null) {
            return null;     
        } 
        map.put("albumTitle", partyAblum.getAlbumTitle());
        map.put("description", partyAblum.getDescription());
        
        PartyPictureExample example = new PartyPictureExample();
        example.createCriteria().andAlbumIdEqualTo(albumId);
        List<PartyPicture> listPicture = new ArrayList<>();
        listPicture = partyPictureMapper.selectByExample(example);
        if (listPicture == null || listPicture.size() == 0) {
            logger.info("相册ID：" + albumId + " 下无图片");
        }
        map.put("pictures", listPicture);
        addPageviews(albumId);
        return map;
    }

    @Override
    public void addPageviews(Long albumId) {
        
        partyAlbumMapper.addPageviews(albumId);
    }

    @Transactional
    @Override
    public ServerResponse createAlbum(PartyAlbum partyAlbum, MultipartFile[] files, HttpServletRequest request) {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            logger.error("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }    
        String userId = basicInfo.getUserId();
        int branchId = basicInfo.getBranchId();
        
        if (files == null || files.length <= 0) {
            logger.debug("未上传任何党活动图片");
            return ServerResponse.createByErrorMessage("未上传任何党活动图片");
        }
        
        Long nTime = new Date().getTime();
        partyAlbum.setAlbumId(nTime);
        partyAlbum.setBranchId(branchId);
        partyAlbum.setCreateDate(nTime);
        partyAlbum.setQuantity(files.length);
        partyAlbum.setUserId(userId);
        //HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //上传到FTP的路径
        String remotePath = "/partyalbum/" + nTime + "/";       
        for (int i = 0; i < files.length; i++) {
            Map map = FTPSSMLoad.upload(files[i], request, remotePath);                
            String path = remotePath + (String)map.get("uri");
            logger.debug("相册图片相对路径：" + path);
            if (i == 0) {
                //上传的第一张图片作为封面图
                partyAlbum.setCoverImage(path);
                partyAlbumMapper.insertSelective(partyAlbum);
            }
            PartyPicture partyPicture = new PartyPicture();
            partyPicture.setAlbumId(nTime);
            partyPicture.setImage(path);
            partyPictureMapper.insertSelective(partyPicture);
        }
        return ServerResponse.createBySuccess();
    }

    @Transactional
    @Override
    public ServerResponse removeAlbum(Long albumId) {
           
        PartyPictureExample example = new PartyPictureExample();
        example.createCriteria().andAlbumIdEqualTo(albumId);
        int j = partyPictureMapper.deleteByExample(example);
        if (j <= 0)
            return ServerResponse.createByError();
        
        int i = partyAlbumMapper.deleteByPrimaryKey(albumId);
        if (i <= 0)
            return ServerResponse.createByError();
        
        return ServerResponse.createBySuccess();
    }
}
