package com.zqu.pa.dao.perinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.userInfo.UserBasicInfo;

public interface UserListMapper {

    //根据branchId来搜索对应用户信息列表，分页
    List<UserBasicInfo> getUserBasicList(@Param("branchId") int branchId, @Param("index") int index, @Param("num") int num);
}
