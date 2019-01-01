package com.zqu.pa.service.meeting;

import java.util.List;

import com.zqu.pa.entity.meeting.Meeting;
import com.zqu.pa.vo.newsnotices.MeetingInfo;
import com.zqu.pa.vo.newsnotices.PageOfList;

public interface MeetingService {

    //根据党支部ID，页数和每页记录数，返回相应党支部ID的会议列表信息
    PageOfList getMenuInfo(int branchId, int page, int num);

    //根据id返回此会议所有信息
    Meeting getMeetingInfo(int meeting_id, int type);

    //添加未审核的会议信息,返回结果消息
    String InsertMeeting(Meeting meeting);

    //返回全部会议的列表信息，包括审核未审核
    List<MeetingInfo> getMeetingList();

    //批量审核会议
    String checkMeetingByBatch(String meetingId);

    //修改会议信息
    int updateMeeting(Meeting meeting);

    //批量删除会议
    String deleteMeetingByBatch(String meetingId);

}
