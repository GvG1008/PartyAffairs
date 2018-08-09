package com.zqu.pa.service.groupalbum.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.groupalbum.GroupAlbumMapper;
import com.zqu.pa.dao.groupalbum.GroupPictureMapper;
import com.zqu.pa.entity.groupalbum.GroupAlbum;
import com.zqu.pa.entity.groupalbum.GroupAlbumExample;
import com.zqu.pa.entity.groupalbum.GroupBranch;
import com.zqu.pa.entity.groupalbum.GroupPicture;
import com.zqu.pa.entity.groupalbum.GroupPictureExample;
import com.zqu.pa.service.groupalbum.GroupAlbumService;
import com.zqu.pa.service.groupalbum.GroupBranchService;
import com.zqu.pa.utils.DateUtil;
import com.zqu.pa.utils.FTPSSMLoad;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class GroupAlbumServiceImpl implements GroupAlbumService {

    private Logger logger = LoggerFactory.getLogger(GroupAlbumServiceImpl.class);
    
    @Autowired
    private GroupAlbumMapper groupAlbumMapper;
    
    @Autowired
    private GroupPictureMapper groupPictureMapper;
    
    @Autowired
    private GroupBranchService groupBranchService;
    
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

    @Transactional
    @Override
    public ServerResponse createAlbum(GroupAlbum groupAlbum, MultipartFile[] files, HttpServletRequest request) {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if (basicInfo == null) {
            logger.debug("用户未登录");
            return ServerResponse.createByErrorMessage("用户未登录");
        }    
        String userId = basicInfo.getUserId();
        GroupBranch groupBranch = groupBranchService.getGroupBranch(userId);
        if (groupBranch == null) {
            logger.debug("用户不是团支部管理员");
            return ServerResponse.createByError();
        }
        int groupId = groupBranch.getGroupId();
        
        if (files == null || files.length <= 0) {
            logger.debug("未上传任何团活动图片");
            return ServerResponse.createByErrorMessage("未上传任何团活动图片");
        }
        
        Long nTime = new Date().getTime();
        groupAlbum.setAlbumId(nTime);
        groupAlbum.setGroupId(groupId);
        groupAlbum.setCreateDate(nTime);
        groupAlbum.setQuantity(files.length);
        groupAlbum.setUserId(userId);
        //HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        //上传到FTP的路径
        String remotePath = "/groupalbum/" + groupId + "/" + nTime + "/";       
        for (int i = 0; i < files.length; i++) {
            Map map = FTPSSMLoad.upload(files[i], request, remotePath);                
            String path = remotePath + (String)map.get("uri");
            logger.debug("相册图片相对路径：" + path);
            if (i == 0) {
                //上传的第一张图片作为封面图
                groupAlbum.setCoverImage(path);
                groupAlbumMapper.insertSelective(groupAlbum);
            }
            GroupPicture groupPicture = new GroupPicture();
            groupPicture.setAlbumId(nTime);
            groupPicture.setImage(path);
            groupPictureMapper.insertSelective(groupPicture);
        }
        return ServerResponse.createBySuccess();
    }

    @Transactional
    @Override
    public ServerResponse removeAlbum(Long albumId) {
        
        GroupPictureExample example = new GroupPictureExample();
        example.createCriteria().andAlbumIdEqualTo(albumId);
        int j = groupPictureMapper.deleteByExample(example);
        if (j <= 0)
            return ServerResponse.createByError();
        
        int i = groupAlbumMapper.deleteByPrimaryKey(albumId);
        if (i <= 0)
            return ServerResponse.createByError();
        
        return ServerResponse.createBySuccess();
    }

}
