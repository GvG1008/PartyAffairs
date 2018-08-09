package com.zqu.pa.service.groupalbum;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.groupalbum.GroupBranch;

public interface GroupBranchService {

    //获取团支部列表
    List<GroupBranch> listGroupBranch();
    
    //获取某用户的团支部
    GroupBranch getGroupBranch(String userId);
    
    //创建一个团支部并关联管理员账号
    ServerResponse createGroupBranch(String groupName, String userId);
    
    //更新团支部
    ServerResponse updateGroupBranch(Integer groupId, GroupBranch groupBranch);

}
