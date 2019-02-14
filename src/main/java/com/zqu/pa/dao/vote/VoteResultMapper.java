package com.zqu.pa.dao.vote;

import com.zqu.pa.entity.vote.VoteResult;
import com.zqu.pa.entity.vote.VoteResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteResultMapper {
    long countByExample(VoteResultExample example);

    int deleteByExample(VoteResultExample example);

    int deleteByPrimaryKey(Long resultId);

    int insert(VoteResult record);

    int insertSelective(VoteResult record);

    List<VoteResult> selectByExample(VoteResultExample example);

    VoteResult selectByPrimaryKey(Long resultId);

    int updateByExampleSelective(@Param("record") VoteResult record, @Param("example") VoteResultExample example);

    int updateByExample(@Param("record") VoteResult record, @Param("example") VoteResultExample example);

    int updateByPrimaryKeySelective(VoteResult record);

    int updateByPrimaryKey(VoteResult record);
    
    long countVoteChoice(@Param("voteId")Long voteId, @Param("choiceId")Long choiceId);
    
    long countVoteSortChoice(@Param("voteId")Long voteId, @Param("choiceId")Long choiceId, @Param("sort")Integer sort);
}