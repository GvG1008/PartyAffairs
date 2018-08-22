package com.zqu.pa.dao.partyactivity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.partyactivity.ActivityManageMenu;
import com.zqu.pa.vo.partyactivity.ApplyMsg;

public interface PartyActivityManageMapper {

    //返回管理员管理活动所有列表信息，按照党支部Id返回信息
    List<ActivityManageMenu> getActivityList(@Param(value="branchId")Integer branchId);
    
    //返回对应活动ID的审核列表信息
    List<ApplyMsg> getActivityApply(@Param(value="activityId")Integer activityId, @Param(value="checkState")Integer checkState);
    
    //批量插入活动参加人员限制的角色身份
    int insertAcRoleBatch(@Param(value="activityId")Integer activityId, @Param(value="roleIds")Integer[] roleIds);

    //批量删除活动
    int deleteActivityBatch(@Param(value="branchId")Integer branchId, @Param(value="activityIds")List<Integer> activityIds);
    
    //批量审核人员
    int passApplyByBatch(@Param(value="checkId")String checkId, @Param(value="activityId")Integer activityId,@Param(value="userIds")List<String> userIds);
}
