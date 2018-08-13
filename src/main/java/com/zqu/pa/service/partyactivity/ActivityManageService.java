package com.zqu.pa.service.partyactivity;

import java.util.List;

import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.vo.partyactivity.ActivityManageMenu;

public interface ActivityManageService {

    //返回管理员管理活动列表信息
    List<ActivityManageMenu> getActivityMenuList(int branchId);

    //创建新活动
    String insertActivity(PartyActivity pA, Integer[] roleIds);

}
