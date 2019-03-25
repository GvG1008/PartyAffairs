package com.zqu.pa.dao.perinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;

import com.zqu.pa.vo.perinfo.UserInfoAndHead;
import com.zqu.pa.vo.userInfo.UserLoginInfo;

public interface UserManageMapper {

    //新增账号信息
    int insertUserLoginInfo(UserLoginInfo info);
    //审核，更新账号状态
    int changeUserState(@Param("userId") String userId);
    //批量验证党支部是否一至
    List<String> checkBranchByBatch(@Param("userIds")List<String> userIds,@Param("branchId")int branchId);
    //批量更新账号信息表的账号状态
    int batchChangeUserState(@Param("userIds")List<String> userIds);
    //批量更新个人信息表审核状态
    int updateCheckUserByBatch(@Param("checkId") String checkId,@Param("userIds")List<String> userIds);
    //批量删除用户账号信息
    int deleteUserLogin(@Param("userIds")List<String> userIds);
    //批量删除用户个人信息
    int deleteUserPersonInfo(@Param("userIds")List<String> userIds);
    //批量删除用户党员信息
    int deleteUserPartyInfo(@Param("userIds")List<String> userIds);
    
    //用户修改自己密码
    int updatePassword(@Param("userId")String userId, @Param("oldPassword")String old_password,  @Param("newPassword")String new_password);
    //判断输入的密码是否正确
    int checkPassword(@Param("userId")String userId, @Param("password")String password);
    
    UserInfoAndHead getUserPartyInfoAndHeadById(@Param("userId")String userId);
}
