package com.zqu.pa.controller.partyalbum;

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
import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyPicture;
import com.zqu.pa.service.partyalbum.PartyAlbumService;
import com.zqu.pa.utils.FTPSSMLoad;

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
    @RequestMapping(value = "/{branchId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/picture/{albumId}", method = RequestMethod.GET)
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
    
    /**
     * 创建一个党活动相册，上传多个图片到FTP服务器
     * @param partyAlbum 相册信息
     * @param files 所有图片信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ServerResponse createAlbum(PartyAlbum partyAlbum, 
            @RequestParam("files") MultipartFile[] files, HttpServletRequest request) {
        
        ServerResponse result = null;
        try {
            result = partyAlbumService.createAlbum(partyAlbum, files, request);
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }   
    
    
    /**
     * 删除党活动相册
     * @param albumId 相册ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{albumId}", method = RequestMethod.DELETE)
    public ServerResponse removeAlbum(@PathVariable Long albumId) {
             
        return partyAlbumService.removeAlbum(albumId);
    }
}
