package com.zqu.pa.controller.groupalbum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.groupalbum.GroupAlbum;
import com.zqu.pa.entity.groupalbum.GroupPicture;
import com.zqu.pa.service.groupalbum.GroupAlbumService;

@Controller
@RequestMapping("/groupalbum")
public class GroupAlbumController {

    @Autowired
    private GroupAlbumService groupAlbumService;
    
    /**
     * 获取对应团支部下的所有活动相册列表
     * @param groupId 团支部ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public ServerResponse listAlbum(@PathVariable Integer groupId) {
        
        List<GroupAlbum> listAlbum = null;
        try {
            listAlbum = groupAlbumService.listAlbum(groupId);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(listAlbum);
    }
    
    /**
     * 获取相册里的所有图片和图片标题、描述
     * @param albumId 相册ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/picture/{albumId}", method = RequestMethod.GET)
    public ServerResponse listAlbumPicture(@PathVariable Long albumId) {
        
        Map<String, Object> map = new HashMap<>();
        try {
            map = groupAlbumService.listAlbumPicture(albumId);
            if (map == null) 
                return ServerResponse.createByError();
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(map);
    }
    
    /**
     * 创建一个团活动相册，上传多个图片到FTP服务器
     * @param groupAlbum 相册信息
     * @param files 所有图片信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ServerResponse createAlbum(GroupAlbum groupAlbum, 
            @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        
        ServerResponse result = null;
        try {
            result = groupAlbumService.createAlbum(groupAlbum, files, request);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }   
    
    /**
     * 删除团活动相册
     * @param albumId 相册ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{albumId}", method = RequestMethod.DELETE)
    public ServerResponse removeAlbum(@PathVariable Long albumId) {
             
        return groupAlbumService.removeAlbum(albumId);
    }
}
