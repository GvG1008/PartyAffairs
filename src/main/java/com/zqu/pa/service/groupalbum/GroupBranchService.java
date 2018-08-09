package com.zqu.pa.service.groupalbum;

import java.util.List;

import com.zqu.pa.entity.groupalbum.GroupBranch;

public interface GroupBranchService {

    //获取团支部列表
    List<GroupBranch> listGroupBranch();
    
    //获取某用户的团支部
    GroupBranch getGroupBranch(String userId);
}
