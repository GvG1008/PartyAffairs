package com.zqu.pa.service.groupalbum.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
