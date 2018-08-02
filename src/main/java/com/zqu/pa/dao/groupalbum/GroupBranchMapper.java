package com.zqu.pa.dao.groupalbum;

import com.zqu.pa.entity.groupalbum.GroupBranch;
import com.zqu.pa.entity.groupalbum.GroupBranchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupBranchMapper {
    long countByExample(GroupBranchExample example);

    int deleteByExample(GroupBranchExample example);

    int deleteByPrimaryKey(Integer groupId);

    int insert(GroupBranch record);

    int insertSelective(GroupBranch record);

    List<GroupBranch> selectByExample(GroupBranchExample example);

    GroupBranch selectByPrimaryKey(Integer groupId);

    int updateByExampleSelective(@Param("record") GroupBranch record, @Param("example") GroupBranchExample example);

    int updateByExample(@Param("record") GroupBranch record, @Param("example") GroupBranchExample example);

    int updateByPrimaryKeySelective(GroupBranch record);

    int updateByPrimaryKey(GroupBranch record);
}