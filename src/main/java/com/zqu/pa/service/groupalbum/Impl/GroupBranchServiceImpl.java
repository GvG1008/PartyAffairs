package com.zqu.pa.service.groupalbum.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.groupalbum.GroupBranchMapper;
import com.zqu.pa.entity.groupalbum.GroupBranch;
import com.zqu.pa.entity.groupalbum.GroupBranchExample;
import com.zqu.pa.service.groupalbum.GroupBranchService;

@Service
public class GroupBranchServiceImpl implements GroupBranchService {

    @Autowired
    private GroupBranchMapper groupBranchMapper;
    
    @Override
    public List<GroupBranch> listGroupBranch() {
        
        GroupBranchExample example = new GroupBranchExample();
        return groupBranchMapper.selectByExample(example);
    }

    @Override
    public GroupBranch getGroupBranch(String userId) {
        
        GroupBranchExample example = new GroupBranchExample();
        List<GroupBranch> list = groupBranchMapper.selectByExample(example);
        if (list == null || list.size() == 0)
            return null;
        return list.get(0);
    }

    @Override
    public ServerResponse createGroupBranch(String groupName, String userId) {
        
        GroupBranch groupBranch = new GroupBranch();
        groupBranch.setGroupName(groupName);
        groupBranch.setUserId(userId);
        int i = groupBranchMapper.insertSelective(groupBranch);
        if (i > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    }

    @Override
    public ServerResponse updateGroupBranch(Integer groupId, GroupBranch groupBranch) {
        
        groupBranch.setGroupId(groupId);
        int i = groupBranchMapper.updateByPrimaryKeySelective(groupBranch);
        if (i > 0)
            return ServerResponse.createBySuccess();
        return ServerResponse.createByError();
    }

}
