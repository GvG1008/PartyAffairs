package com.zqu.pa.dao.partyactivity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.partyactivity.ActivityMenuList;

public interface PartyActivityListMapper {

    List<ActivityMenuList> getActivityMenuListLimit(@Param("index") int index , @Param("num") int num ,
             @Param("branchId") int branchId);
}
