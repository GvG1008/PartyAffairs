package com.zqu.pa.dao.vote;

import com.zqu.pa.entity.vote.VoteInfo;
import com.zqu.pa.entity.vote.VoteInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteInfoMapper {
    long countByExample(VoteInfoExample example);

    int deleteByExample(VoteInfoExample example);

    int deleteByPrimaryKey(Long voteId);

    int insert(VoteInfo record);

    int insertSelective(VoteInfo record);

    List<VoteInfo> selectByExample(VoteInfoExample example);

    VoteInfo selectByPrimaryKey(Long voteId);

    int updateByExampleSelective(@Param("record") VoteInfo record, @Param("example") VoteInfoExample example);

    int updateByExample(@Param("record") VoteInfo record, @Param("example") VoteInfoExample example);

    int updateByPrimaryKeySelective(VoteInfo record);

    int updateByPrimaryKey(VoteInfo record);
}