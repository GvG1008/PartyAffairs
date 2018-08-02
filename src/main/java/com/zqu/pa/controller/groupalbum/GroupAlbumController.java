package com.zqu.pa.controller.groupalbum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/{groupId}")
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
     * 获取相册里的所有图片
     * @param albumId 相册ID
     * @return
     */
    @ResponseBody
    @RequestMapping("/picture/{albumId}")
    public ServerResponse listAlbumPicture(@PathVariable Long albumId) {
        
        List<GroupPicture> listPicture = null;
        try {
            listPicture = groupAlbumService.listAlbumPicture(albumId);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(listPicture);
    }
}
