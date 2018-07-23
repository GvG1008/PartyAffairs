package com.zqu.pa.dao.userlogin;

import java.util.List;

import com.zqu.pa.entity.partybranch.PartyBranch;
import com.zqu.pa.entity.userlogin.User;
import com.zqu.pa.vo.userInfo.UserBasicInfo;



public interface UserDao {

    User getUser();

    User getUserLogin(String userId);
    
    /**
     * 根据身份ID获取权限列表
     * @param roleId
     * @return
     */
    List<String> getRolePermission(int roleId);

/*  按照userId查询名字
    String getRealName(String userId);
        按照userId查询党支部名字
    PartyBranch getPartyBranch(String userId);
 */
    UserBasicInfo getUserBasicInfo(String userId);
}
