package com.zqu.pa.controller.meeting;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.meeting.Meeting;
import com.zqu.pa.service.meeting.MeetingService;
import com.zqu.pa.utils.FTPSSMLoad;
import com.zqu.pa.vo.newsnotices.MeetingInfo;
import com.zqu.pa.vo.newsnotices.PageOfList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Controller
public class MeetingController {

    @Autowired
    MeetingService meetingService;
    
    /**
     * 根据路径传来的页数，党支部ID，返回已审核会议列表信息（标题，日期列表）
     * @param branchId  若传来为0，则返回所有会议列表
     * @param page
     * @param num
     * @return
     */
    @ResponseBody
    @RequestMapping("/meetingMenu_1/{branchId}/{pageNum}/{num}")
    public ServerResponse<PageOfList> getMeetingMenuOne(@PathVariable(value="branchId")Integer branchId,@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num){
        PageOfList info = new PageOfList();
        
        //传入跳转的页数与当前显示的条数,page为页数，num为每页条数
        info = meetingService.getMenuInfo(branchId,page,num);
        
        if(info==null||info.getList()==null)
            return ServerResponse.createByErrorMessage("获取失败");
        return ServerResponse.createBySuccess("获取会议列表", info);
    }
    
    /**
     * 根据路径传来的页数，以及当前登陆用户党支部ID，返回会议列表（标题，日期列表）
     * 若党支部ID为0，返回所有已审核会议列表
     * @param page
     * @param num
     * @return
     */
    @ResponseBody
    @RequestMapping("/meetingMenu_2/{pageNum}/{num}")
    public ServerResponse<PageOfList> getMeetingMenuTwo(@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num){
        PageOfList info = new PageOfList();
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("当前未登录");
        
        //传入跳转的页数与当前显示的条数,page为页数，num为每页条数
        info = meetingService.getMenuInfo(basicInfo.getBranchId(),page,num);
        
        if(info==null||info.getList()==null)
            return ServerResponse.createByErrorMessage("获取失败");
        return ServerResponse.createBySuccess("获取会议列表", info);
    }
    
    /**
     * 前台获取会议所有信息，只能获取已审核的,返回会议信息不是全部的
     * @param meeting_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/meeting/{meeting_id}")
    public ServerResponse<Meeting> getMeetingInfo(@PathVariable int meeting_id){
        
        Meeting meeting = meetingService.getMeetingInfo(meeting_id,1);
        
        if(meeting!=null)
            return ServerResponse.createBySuccess("获取会议信息成功", meeting);
        else
            return ServerResponse.createByErrorMessage("获取会议信息失败");
    }
    
    /**
     * 后台获取会议所有信息，包括未审核的,返回全部的会议信息
     * @param meeting_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/meeting_all/{meeting_id}")
    public ServerResponse<Meeting> getAllMeetingInfo(@PathVariable int meeting_id){
        
        Meeting meeting = meetingService.getMeetingInfo(meeting_id,0);
        
        if(meeting!=null)
            return ServerResponse.createBySuccess("获取会议信息成功", meeting);
        else
            return ServerResponse.createByErrorMessage("获取会议信息失败");
    }
    
    /**
     * 添加插入未审核的会议
     * @param meeting
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertMeeting",method=RequestMethod.POST)
    public ServerResponse InsertMeeting(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="video",required = false) MultipartFile video,
            @RequestParam(value="title") String title, @RequestParam(value="branchId")Integer branchId, 
            @RequestParam(value="content")String content,HttpServletRequest request) {
        Meeting meeting = new Meeting();
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        meeting.setTitle(title);
        meeting.setBranchId(branchId);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        meeting.setContent(content);
        
      //封面图上传
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/meeting/cover/");
            //存储路径
            meeting.setCoverpath(fileMap.get("http_url").toString());
        }else
            meeting.setCoverpath(null);
        
        //视频上传
        if(!video.isEmpty()) {
            //判断是否为视频文件
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(video, request, "/meeting/video/");
            //存储路径
            meeting.setVideo(fileMap.get("http_url").toString());
        }else
            meeting.setVideo(null);
        
        String Msg;
        try {
            Msg = meetingService.InsertMeeting(meeting);
            
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加会议信息失败!");
        }
        
        if(Msg==null)
            return ServerResponse.createByErrorMessage("添加会议信息失败!");
        if(!Msg.equals("添加会议信息成功"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }

    /**
     * 返回管理员端管理会议的列表信息,包括已审核未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/mentingList",method=RequestMethod.GET)
    public ServerResponse<List<MeetingInfo>> getMeetingList() {
        List<MeetingInfo> listInfo = null;
        
        //获取会议列表
        listInfo  = meetingService.getMeetingList();
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 批量审核会议
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkMeeting/{meetingId}")
    public ServerResponse checkMeeting(@PathVariable String meetingId) {
        if(meetingId==null)
            return ServerResponse.createByErrorMessage("会议ID为空");
        
        //批量审核
        String Msg = meetingService.checkMeetingByBatch(meetingId);
        if(!Msg.equals("审核成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 管理员修改会议,不可修改党支部ID
     * @param meeting
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateMeeting")
    public ServerResponse updateMeeting(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="video",required = false) MultipartFile video,
            @RequestParam(value="meetingId")Integer meetingId,
            @RequestParam(value="title") String title,
            @RequestParam(value="content")String content,HttpServletRequest request) {
        if(meetingId==null)
            return ServerResponse.createByErrorMessage("ID为空");
        Meeting meeting = new Meeting();
        //存储ID
        meeting.setMeetingId(meetingId);
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        //存储标题
        meeting.setTitle(title);

        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        meeting.setContent(content);
        
        //修改封面图，上传图片
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/meeting/cover/");
            //存储路径
            meeting.setCoverpath(fileMap.get("http_url").toString());
        }else
            meeting.setCoverpath(null);
        
        //修改视频地址，上传视频
        if(!video.isEmpty()) {
            //判断是否为视频
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(video, request, "/meeting/video/");
            //存储路径
            meeting.setVideo(fileMap.get("http_url").toString());
        }else
            meeting.setVideo(null);
        
        if(meetingService.updateMeeting(meeting)==0)
            return ServerResponse.createByErrorMessage("修改会议信息失败!");
        return ServerResponse.createBySuccessMessage("修改会议信息成功!");
    }
    

    /**
     * 批量删除会议信息
     * @param meetingId 一串meetingId的字符串"&meetingId1&meetingId2&meetingId3.."
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteMeeting/{meetingId}")
    public ServerResponse deleteMeeting(@PathVariable String meetingId) {
        if(meetingId==null)
            return ServerResponse.createByErrorMessage("会议ID为空");
        
        //批量删除
        String Msg = meetingService.deleteMeetingByBatch(meetingId);
        if(Msg.equals("删除失败!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
}
