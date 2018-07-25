package com.zqu.pa.dao.perinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.perinfo.UserList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

public interface UserListMapper {
    
    int countByBranch(@Param("branchId") int branchId , @Param("check") int check);
    
    //根据branchId来搜索对应用户信息列表，分页
    List<UserList> getUserBasicList(@Param("branchId") int branchId, @Param("index") int index, @Param("num") int num);
}
