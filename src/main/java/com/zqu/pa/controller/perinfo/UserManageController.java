package com.zqu.pa.controller.perinfo;

import org.apache.shiro.SecurityUtils;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.vo.perinfo.UserListInfo;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
@RequestMapping("/userManage")
public class UserManageController {
    
    @Autowired
    UserInfoService userInfoService;
    
    /**
     * 根据ID返回用户个人信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/PersonInfo/{userId}", method=RequestMethod.GET)
    public ServerResponse<UserPersonInfo> getDesignatedPersonInfo(@PathVariable String userId){
        
        if(userId==null)
            return ServerResponse.createByErrorMessage("获取指定用户个人信息失败");
        
        UserPersonInfo info = new UserPersonInfo();
        //通过id获取个人信息
        info = userInfoService.getUserPersonInfo(userId);

        if(info==null)
            return ServerResponse.createByErrorMessage("获取指定用户个人信息失败");
        return ServerResponse.createBySuccess("获取指定用户个人信息成功", info);
    }
    
    /**
     * 根据ID返回用户党员信息
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/PartyInfo/{userId}", method=RequestMethod.GET)
    public ServerResponse<UserPartyInfo> getDesignatedPartyInfo(@PathVariable String userId){
        
        if(userId==null)
            return ServerResponse.createByErrorMessage("获取指定用户个人信息失败");
        
        UserPartyInfo info = new UserPartyInfo();
        //通过id获取党员信息
        info = userInfoService.getUserPartyInfo(userId);

        if(info==null)
            return ServerResponse.createByErrorMessage("获取指定用户个人信息失败");
        return ServerResponse.createBySuccess("获取指定用户个人信息成功", info);
    }
    
    /**
     * 管理员修改用户个人信息
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update/personInfo", method=RequestMethod.POST)
    public ServerResponse updatePersonInfo(UserPersonInfo info) {
        
        if(info.getUserId()==null)
            return ServerResponse.createByErrorMessage("操作失败");

        //修改信息
        if(userInfoService.updateByUserPerson(info)==0)
            return ServerResponse.createByErrorMessage("修改个人信息失败");
        
        return ServerResponse.createBySuccessMessage("修改成功");
    }
    
    /**
     * 管理员修改用户党员信息
     * @param info
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update/partyInfo", method=RequestMethod.POST)
    public ServerResponse updatePartyInfo(UserPartyInfo info) {

        if(info.getUserId()==null)
            return ServerResponse.createByErrorMessage("操作失败");

        //修改信息
        if(userInfoService.updateByUserParty(info)==0)
            return ServerResponse.createByErrorMessage("修改党员信息失败");

        return ServerResponse.createBySuccessMessage("修改成功");
    }
    
    @ResponseBody
    @RequestMapping("/userListByBranch/{pageNum}/{num}")
    public ServerResponse<UserListInfo> getUserList(@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num){
        
        //获取当前session里的
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null||basicInfo.getBranchId()==0)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        UserListInfo listInfo = new UserListInfo();
        listInfo = userInfoService.getUserList(basicInfo.getBranchId(),page,num);
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败！");
        return ServerResponse.createBySuccess(listInfo);
    }
}
