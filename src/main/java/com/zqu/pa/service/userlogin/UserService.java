package com.zqu.pa.service.userlogin;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.partybranch.PartyBranch;
import com.zqu.pa.entity.userlogin.User;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

public interface UserService {

    User getUser();
    
    User userlogin(String userId);
    /**
     * 根据身份ID获取权限列表
     * @param roleId
     * @return
     */
    List<String> getPermissionMenu(int roleId);
    
    /**
     * 根据Id获取用户信息
     * @param userId 用户id
     * @return
     */
    UserBasicInfo getUserBasicInfo(String userId);
    
    /**
     * 管理员重置用户密码，默认密码
     * @param userId 用户id
     * @return
     */
    ServerResponse resetPassword(String userId);

}
