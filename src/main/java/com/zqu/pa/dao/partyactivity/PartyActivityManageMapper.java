package com.zqu.pa.dao.partyactivity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.partyactivity.ActivityManageMenu;

public interface PartyActivityManageMapper {

    //返回管理员管理活动所有列表信息，按照党支部Id返回信息
    List<ActivityManageMenu> getActivityList(@Param(value="branchId")Integer branchId);
    
    //批量插入活动参加人员限制的角色身份
    int insertAcRoleBatch(@Param(value="activityId")Integer activityId, @Param(value="roleIds")Integer[] roleIds);
}
