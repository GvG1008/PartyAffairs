package com.zqu.pa.service.meeting.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.dao.meeting.MeetingMapper;
import com.zqu.pa.entity.meeting.Meeting;
import com.zqu.pa.entity.meeting.MeetingExample;
import com.zqu.pa.entity.meeting.MeetingExample.Criteria;
import com.zqu.pa.service.meeting.MeetingService;
import com.zqu.pa.vo.newsnotices.MeetingInfo;
import com.zqu.pa.vo.newsnotices.PageOfList;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    MeetingMapper meetingDao;
    
    @Override
    public PageOfList getMenuInfo(int branchId, int page, int num) {
        if(num==0)
            return null;
        
        PageOfList info = new PageOfList();
        
        MeetingExample example = new MeetingExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);
        if(branchId!=0)
            criteria.andBranchIdEqualTo(branchId);
        
        //总记录条数
        int totalInfoNum = (int) meetingDao.countByExample(example);
        info.setTotalInfoNum(totalInfoNum);
        //总页数
        int totalPageNum = (int)(totalInfoNum+num-1)/num;
        info.setTotalPageNum(totalPageNum);
        
        if(totalPageNum<page)
            page = totalPageNum;
        if(page<=0)
            page = 1;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录
        int index = (page-1)*num;
        info.setList(meetingDao.getMenuListLimit(branchId,index, num));
        
        return info;
    }

    
    @Override
    public Meeting getMeetingInfo(int meeting_id, int type) {
        
        Meeting meeting = new Meeting();
        meeting = meetingDao.selectByPrimaryKey(meeting_id);
        if(meeting==null)
            return null;
        //获取通过审核的
        if(type==1) {
            //若未通过审核，返回null
            if(meeting.getState()==0)
                return null;
            else {
                //查看该条新闻信息页面，此新闻click+1
                Meeting meeting2 = new  Meeting();
                meeting2.setMeetingId(meeting.getMeetingId());
                meeting2.setClick(meeting.getClick()+1);
                if(meetingDao.updateByPrimaryKeySelective(meeting2)==0)
                    return null;
                meeting.setClick(meeting.getClick()+1);
                //显示在公众页面,删除多余信息
                meeting.setBranchId(null);
                meeting.setCreatorId(null);
                meeting.setLastTime(null);
                meeting.setState(null);
            }
        }
        return meeting;
    }


    @Override
    public String InsertMeeting(Meeting meeting) {
        
        //获取当前用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return "无法获取当前session信息";
        
        //存储发布人
        meeting.setCreatorId(userId);
        
        //存储新闻状态
        meeting.setState(0);
        
        //数据库插入新闻
        int result = meetingDao.insert(meeting);
        
        if(result==0)
            return "添加会议失败";
        return "添加会议成功";
    }


    @Override
    public List<MeetingInfo> getMeetingList() {
        
        return meetingDao.getMeetingManageList();
    }


    @Override
    public String checkMeetingByBatch(String meetingId) {
        List<Integer> Ids = this.StringToListId(meetingId);
        if(Ids==null||Ids.size()==0)
            return "审核失败!";
        
        int result=0;
        try {
            //进行更新审核状态
            result=meetingDao.updateCheckMeetingByBatch(Ids);
        }catch (Exception e) {
            return "审核失败!";
        }
        if(result==0)
            return "审核失败!";
        return "审核成功!";
    }

    /**
     * 根据Id字符串返回Id的list
     * @param Id
     * @return
     */
    public List<Integer> StringToListId(String Id) {
        if(Id==null||Id.equals(""))
            return null;
        
        List<Integer> Ids = new ArrayList<>();
        
        try {
            int index=0;
            int i=0;
            for( ; i<Id.length() ; i++) {
                if(Id.substring(i, i+1).equals("&")) {
                    if(i==0) {
                        index = i+1;
                        continue;
                    }
                    Ids.add(Integer.parseInt(Id.substring(index, i)));
                    index = i+1;
                }
            }
            if(i>index)
                Ids.add(Integer.parseInt(Id.substring(index, i)));
        } catch (NumberFormatException x) {
            return null;
        }
        return Ids;
    }


    @Override
    public int updateMeeting(Meeting meeting) {
        meeting.setLastTime(new Date());
        return meetingDao.updateByPrimaryKeySelective(meeting);
    }


    @Transactional
    @Override
    public String deleteMeetingByBatch(String meetingId) {
        List<Integer> Ids = this.StringToListId(meetingId);
        if(Ids==null||Ids.size()==0)
            return "删除失败!";
        
        int result=0;
        try {
            //进行批量删除
            result=meetingDao.deleteMeetingByBatch(Ids);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(result==0)
            return "删除失败!";
        return "成功删除"+result+"条会议信息";
    }
}
