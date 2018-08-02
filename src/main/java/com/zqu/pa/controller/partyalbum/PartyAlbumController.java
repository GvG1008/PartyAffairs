package com.zqu.pa.controller.partyalbum;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyPicture;
import com.zqu.pa.service.partyalbum.PartyAlbumService;

@Controller
@RequestMapping("/partyalbum")
public class PartyAlbumController {

    @Autowired
    private PartyAlbumService partyAlbumService;
    
    /**
     * 获取对应党支部下的所有活动相册列表
     * @param branchId 党支部ID
     * @return
     */
    @ResponseBody
    @RequestMapping("/{branchId}")
    public ServerResponse listAlbum(@PathVariable Integer branchId) {
        
        List<PartyAlbum> listAlbum = null;
        try {
            listAlbum = partyAlbumService.listAlbum(branchId);
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
        
        List<PartyPicture> listPicture = null;
        try {
            listPicture = partyAlbumService.listAlbumPicture(albumId);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(listPicture);
    }
}
