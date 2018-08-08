package com.zqu.pa.service.partyactivity;

import java.util.Map;

import com.zqu.pa.vo.partyactivity.PageOfList;

public interface UserActivityService {

    //根据页数和每页记录数，和所属党支部返回对应活动列表信息
    PageOfList getMenuInfo(int page, int num, int branchId);

    //点击我要报名后获取是否可以报名的信息
    Map getApplyResult(int activityId);

    //获取当前用户是否报过名
    int getIsApply(int activityId, String userId);

}
