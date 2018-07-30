package com.zqu.pa.dao.perinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.perinfo.UserCheckList;
import com.zqu.pa.vo.perinfo.UserList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

public interface UserListMapper {
    
    int countByBranch(@Param("branchId") int branchId , @Param("checkState") int checkState);
    //根据branchId来搜索对应用户信息列表
    List<UserList> getUserBasicList(@Param("branchId") int branchId,  @Param("checkState") int checkState);
    //根据branchId来搜索审核列表
    List<UserCheckList> getUserCheckList(@Param("branchId") int branchId);
    //根据branchId来返回年级列表(保留)
    List<String> getGradeList(@Param("branchId") int branchId);
    //根据branchId来返回班级列表(保留)
    List<String> getClassList(@Param("branchId") int branchId);
}
