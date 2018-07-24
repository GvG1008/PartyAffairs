package com.zqu.pa.service.perinfo;

import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.vo.perinfo.UserListInfo;

public interface UserInfoService {

    //根据id搜索该用户个人信息
    UserPersonInfo getUserPersonInfo(String userId);
    
    //根据id搜索该用户党员信息
    UserPartyInfo getUserPartyInfo(String userId);
    
    //用户根据userId来修改个人信息
    int updateByUserPerson(UserPersonInfo info);

    //用户根据userId来修改党员信息
    int updateByUserParty(UserPartyInfo info);

    //根据所属党支部返回用户信息列表，根据页数，每页记录返回相应信息
    //branchId==0表示查询所有党支部人员信息
    UserListInfo getUserList(int branchId, int page, int num);

}
