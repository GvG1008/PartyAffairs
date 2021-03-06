package com.zqu.pa.service.partyactivity;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.vo.partyactivity.ActivityInfo;
import com.zqu.pa.vo.partyactivity.ActivityManageMenu;
import com.zqu.pa.vo.partyactivity.ApplyMsg;

public interface ActivityManageService {

    //返回管理员管理活动列表信息
    List<ActivityManageMenu> getActivityMenuList(int branchId);

    //创建新活动
    String insertActivity(PartyActivity pA, Integer[] roleIds);

    //批量删除活动
    ServerResponse<String> deleteActivityBatch(String activityId);

    //修改对应ID活动信息
    ServerResponse<String> updateActivityInfo(ActivityInfo info);

    //返回活动对应的审核列表
    ServerResponse<List<ApplyMsg>> getactivityApplyList(Integer activityId, Integer checkState);

    //批量审核人员通过
    ServerResponse<String> checkApply(Integer activityId, String userId);

    //批量审核人员不通过
    ServerResponse<String> revokeApply(Integer activityId, String userId);

    //获取活动审核通过人员excel表
    HSSFWorkbook getExcelTable(Integer activityId);

}
