package com.zqu.pa.dao.perinfo;

import com.zqu.pa.vo.userInfo.UserLoginInfo;

public interface UserManageMapper {

    //新增账号信息
    int insertUserLoginInfo(UserLoginInfo info);
}
