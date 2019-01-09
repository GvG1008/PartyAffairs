package com.zqu.pa.dao.meeting;

import com.zqu.pa.entity.meeting.Meeting;
import com.zqu.pa.entity.meeting.MeetingExample;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.MeetingInfo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MeetingMapper {
    
    //*标记使用
    long countByExample(MeetingExample example);

    int deleteByExample(MeetingExample example);

    int deleteByPrimaryKey(Integer meetingId);

    //*修改使用
    int insert(Meeting record);

    int insertSelective(Meeting record);

    List<Meeting> selectByExampleWithBLOBs(MeetingExample example);

    List<Meeting> selectByExample(MeetingExample example);

    //*标记使用
    Meeting selectByPrimaryKey(Integer meetingId);

    int updateByExampleSelective(@Param("record") Meeting record, @Param("example") MeetingExample example);

    int updateByExampleWithBLOBs(@Param("record") Meeting record, @Param("example") MeetingExample example);

    int updateByExample(@Param("record") Meeting record, @Param("example") MeetingExample example);
    
    //*标记使用
    int updateByPrimaryKeySelective(Meeting record);

    int updateByPrimaryKeyWithBLOBs(Meeting record);

    int updateByPrimaryKey(Meeting record);

    //根据数目，获取审核通过，仅有标题与日期的会议列表，按日期排序
    List<HomeList> getMenuListLimit(@Param("branchId")int branchId,@Param("index") int index , @Param("num") int num);

    //查询所有会议信息除富文本，返回列表
    List<MeetingInfo> getMeetingManageList();
    
    //批量审核会议
    int updateCheckMeetingByBatch(@Param("meetingIds")List<Integer> meetingIds);
    
    //批量删除会议
    int deleteMeetingByBatch(@Param("meetingIds")List<Integer> meetingIds);
    
    String selectUserName(@Param("userId")String userId);
    
    String selectUserHeadImg(@Param("userId")String userId);
    
    String selectBranchName(@Param("branchId")Integer branchId);
    
}