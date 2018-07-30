package com.zqu.pa.dao.perinfo;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.userInfo.UserLoginInfo;

public interface UserManageMapper {

    //新增账号信息
    int insertUserLoginInfo(UserLoginInfo info);
    //审核，更新账号状态
    int changeUserState(@Param("userId") String userId);
    //批量审核

}
