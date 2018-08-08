package com.zqu.pa.dao.partyactivity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.partyactivity.ActivityMenuList;

public interface PartyActivityListMapper {

    //根据条件，获取用户的活动列表
    List<ActivityMenuList> getActivityMenuListLimit(@Param("index") int index , @Param("num") int num ,
             @Param("branchId") int branchId);
    //根据活动ID获取角色身份列表
    List<Integer> getActivityRoleList(@Param("activityId")int activityId);
}
