package com.zqu.pa.controller.perinfo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zqu.pa.common.Const;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.utils.FTPUtil;
import com.zqu.pa.utils.IMGUtil;

@Controller
@RequestMapping("/userInfo")
public class PersonalController {
    
    @Autowired
    UserInfoService userInfoService;
    
    /**
     * 获取该session用户所有个人信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/personInfo")
    public ServerResponse<UserPersonInfo> getUserPerInfo(){
        
        UserPersonInfo info = new UserPersonInfo();

        //此处获取session里用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return ServerResponse.createByErrorMessage("登录已失效");

        //通过id获取个人信息
        info = userInfoService.getUserPersonInfo(userId);
        
        if(info==null)
            return ServerResponse.createByErrorMessage("获取用户个人信息失败");
        return ServerResponse.createBySuccess("获取用户个人信息成功", info);
    }
    
    /**
     * 获取该session用户所有个人党员信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/partyInfo")
    public ServerResponse<UserPartyInfo> getUserPartyInfo(){
        
        UserPartyInfo info = new UserPartyInfo();

        //此处获取session里用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return ServerResponse.createByErrorMessage("登录已失效");

        //通过id获取党员信息
        info = userInfoService.getUserPartyInfo(userId);
        
        if(info==null)
            return ServerResponse.createByErrorMessage("获取用户个人信息失败");
        return ServerResponse.createBySuccess("获取用户个人信息成功", info);
    }

    /**
     * 用户修改自己的个人信息
     * @param info 接收表单UserPersonInfo信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePerson")
    public ServerResponse updateUserPersonInfo(UserPersonInfo info) {
       
        //获取当前session里的userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");

        //如果数据注入的ID不是当前session里的userId,返回错误
        if(userId==null||info.getUserId()==null)
            return ServerResponse.createByErrorMessage("操作失败");
        else {
            info.setGrade(null);
            info.setClassName(null);
            info.setCheckState(null);
            info.setCheckId(null);
            info.setCreateId(null);
            info.setCreateTime(null);
            info.setImgHead(null);
            info.setLastTime(null);
            info.setName(null);
            info.setSex(null);
        }
        
        //修改信息
        if(userInfoService.updateByUserPerson(info)==0)
            return ServerResponse.createByErrorMessage("修改个人信息失败");
        
        return ServerResponse.createBySuccessMessage("修改成功");
    }
    
    /**
     * 用户修改自己的部分党员信息
     * @param info 接收表单UserPartyInfo信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateParty")
    public ServerResponse updateUserPartyInfo(UserPartyInfo info) {
        
        //获取当前session里的userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        //如果数据注入的ID不是当前session里的userId,返回错误
        if(userId==null||info.getUserId()==null||!userId.equals(info.getUserId()))
            return ServerResponse.createByErrorMessage("操作失败");
        else {
            //将用户不可修改的一些党员信息设为null
            //..
        }
        
        //修改信息
        if(userInfoService.updateByUserParty(info)==0)
            return ServerResponse.createByErrorMessage("修改党员信息失败");
        return ServerResponse.createBySuccessMessage("修改成功");
    }
    
    /**
     * 当前用户自己修改头像（保留）
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/updateImgHead", method = RequestMethod.POST)
    public ServerResponse<String> updateImgHead(@RequestParam(value="imgHeadFile",required = false) MultipartFile file, HttpServletRequest request) {
        if (file == null) return ServerResponse.createByErrorMessage("修改失败");
        String path = request.getSession().getServletContext().getRealPath("upload");

        String fileName = file.getOriginalFilename();
        Integer i = fileName.lastIndexOf(".") + 1;
        String fileExtensionName = fileName.substring(i);
        String filePrefixName = fileName.substring(0, i-1);
        Date d = new Date();
        String uploadFileName = filePrefixName + DateToString.getDateString("yyyy-MM-dd", d) + UUID.randomUUID().toString() + "." + fileExtensionName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //检验文件是否为图片
            if(!this.checkIMG(targetFile))
                return ServerResponse.createByErrorMessage("上传的不是图片文件");
            
            //压缩图片300x300
            IMGUtil.compressPic(targetFile, 180, 180, path+"\\"+uploadFileName);
            
            //上传至FTP
            targetFile = new File(path, uploadFileName);
            FTPUtil.uploadFile("/userInfo/profilePhoto/",Lists.newArrayList(targetFile));
            targetFile.delete();
            
            //修改数据库头像路径
            String fullPath = Const.HTTP_PREFIX+"/userInfo/profilePhoto/"+uploadFileName;
            if(userInfoService.updateImgHead(fullPath,null)==0)
                return ServerResponse.createByErrorMessage("修改失败");
            return ServerResponse.createBySuccess("修改头像成功", fullPath);
        } catch (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }
    
    /**
     * 登录用户修改自己的密码
     * @param old_password
     * @param new_password
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update_mine_password", method = RequestMethod.POST)
    public ServerResponse updateMinePassword(
            @RequestParam(value="old_password")String old_password, @RequestParam(value="new_password")String new_password) {
        if(StringUtils.isBlank(old_password)||StringUtils.isBlank(new_password)) {
            return ServerResponse.createByErrorMessage("输入密码为空！");
        }
        if(new_password.contains(" ")) {
            return ServerResponse.createByErrorMessage("输入密码含有空格！");
        }
        //其他判断密码是否合法
        //。。

        //获取当前session里的userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        int result = userInfoService.updatePassword(userId,old_password,new_password);
        if (result == 0) {
        	return ServerResponse.createByErrorMessage("密码输入失败");
        }else if (result == 1) {
        	return ServerResponse.createByErrorMessage("修改失败");
        }else {
        	return ServerResponse.createBySuccessMessage("修改成功");
        }
    }
    
    /**
     * 判断是否为图片
     * @param file
     * @return
     */
    public boolean checkIMG(File file){
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }
}
