package com.zqu.pa.controller.perinfo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import com.zqu.pa.vo.perinfo.AllUserInfo;
import com.zqu.pa.vo.perinfo.Branch;
import com.zqu.pa.vo.perinfo.GradeClassSortList;
import com.zqu.pa.vo.perinfo.Role;
import com.zqu.pa.vo.perinfo.UserCheckList;
import com.zqu.pa.vo.perinfo.UserList;
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
    
    /**
     * 获取党员档案列表
     * 根据管理员所属党支部//党支部id为0表示所有
     * @return
     */
    @ResponseBody
    @RequestMapping("/userListByBranch")
    public ServerResponse<List<UserList>> getUserListByBranch(){
        
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        List<UserList> listInfo = null;
        //第一个参数党支部id，第四个参数1表示已审核
        listInfo = userInfoService.getUserList(basicInfo.getBranchId(),1);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败！");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 获取所有党支部党员档案列表（保留/更高权限）
     * @return
     */
    @ResponseBody
    @RequestMapping("/userList")
    public ServerResponse<List<UserList>> getUserList(){
        
        List<UserList> listInfo = null;
        //第一个参数0表示所有党支部，第四个参数1表示已审核
        listInfo = userInfoService.getUserList(0,1);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败！");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 获取待审核党员档案列表
     * 根据管理员所属党支部//党支部id为0表示所有
     * @return
     */
    @ResponseBody
    @RequestMapping("/userCheckListByBranch")
    public ServerResponse<List<UserCheckList>> getUserCheckListByBranch(){
        
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        List<UserCheckList> listInfo = null;
        //参数党支部id
        listInfo = userInfoService.getUserCheckList(basicInfo.getBranchId());

        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败！");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 获取待审核的所有党支部党员档案列表（保留/更高权限）
     * @return
     */
    @ResponseBody
    @RequestMapping("/userCheckList")
    public ServerResponse<List<UserCheckList>> getUserCheckList(){
        
        List<UserCheckList> listInfo = null;
        //参数0表示所有党支部
        listInfo = userInfoService.getUserCheckList(0);

        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败！");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 批量审核人员
     * userId字符串格式："userId1&userId2&userId3"..
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/batchCheckUserByBranch/{userId}")
    public ServerResponse checkUserByBatch(@PathVariable(value="userId") String userId){
        if(userId==null)
            return ServerResponse.createByErrorMessage("账号为空");
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");

        //除了branchId==0外,只能审核和自己相同的党支部人员
        String Msg = userInfoService.checkUserByBatch(basicInfo.getBranchId(),userId);
        if(Msg==null||!Msg.equals("审核成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }

    /**
     * 批量删除人员
     * userId字符串格式："userId1&userId2&userId3"..
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUserByBranch/{userId}")
    public ServerResponse deleteUserByBranch(@PathVariable(value="userId") String userId) {
        if(userId==null)
            return ServerResponse.createByErrorMessage("账号为空");
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        //除了branchId==0外,只能删除和自己相同的党支部人员
        String Msg = userInfoService.deleteUser(basicInfo.getBranchId(),userId);
        if(Msg==null||!Msg.equals("删除成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 新增用户所有信息
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertUser", method=RequestMethod.POST)
    public ServerResponse insertUserInfo(AllUserInfo user) {
        
        String Msg;
        try {
            Msg = userInfoService.insertNewUser(user);
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("创建用户出错!");
        }
        if(Msg==null)
            return ServerResponse.createByErrorMessage("创建用户失败!");
        if(!Msg.equals("创建用户成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 根据所属党支部获取年级班级列表(保留)
     * @return
     */
    @ResponseBody
    @RequestMapping("/GradeClassList")
    public ServerResponse<GradeClassSortList> getListByGradeClass() {
        GradeClassSortList list = new GradeClassSortList();
        
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        //获取班级年级信息列表
        list = userInfoService.getGradeClass(basicInfo.getBranchId());
        
        if(list==null)
            return ServerResponse.createByErrorMessage("获取年级班级信息失败!");
        return ServerResponse.createBySuccess(list);
    }
    
    /**
     * 获取党支部列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/BranchList",method=RequestMethod.GET)
    public ServerResponse<List<Branch>> getBranchList()   {
        List<Branch> list;
        try {
            list = userInfoService.getBranchList();
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("获取Branch列表出错!");
        }
        
        if(list==null)
            return ServerResponse.createByErrorMessage("获取Branch列表失败!");
        return ServerResponse.createBySuccess(list);
    }
    
    /**
     * 获取角色列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/RoleList",method=RequestMethod.GET)
    public ServerResponse<List<Role>> getRoleList()   {
        List<Role> list;
        try {
            list = userInfoService.getRoleList();
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("获取Role列表出错!");
        }
        
        if(list==null)
            return ServerResponse.createByErrorMessage("获取Role列表为空!");
        return ServerResponse.createBySuccess(list);
    }
}
