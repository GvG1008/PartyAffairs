package com.zqu.pa.dao.vote;

import com.zqu.pa.entity.vote.VoteChoice;
import com.zqu.pa.entity.vote.VoteChoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteChoiceMapper {
    long countByExample(VoteChoiceExample example);

    int deleteByExample(VoteChoiceExample example);

    int deleteByPrimaryKey(Long choiceId);

    int insert(VoteChoice record);

    int insertSelective(VoteChoice record);

    List<VoteChoice> selectByExample(VoteChoiceExample example);

    VoteChoice selectByPrimaryKey(Long choiceId);

    int updateByExampleSelective(@Param("record") VoteChoice record, @Param("example") VoteChoiceExample example);

    int updateByExample(@Param("record") VoteChoice record, @Param("example") VoteChoiceExample example);

    int updateByPrimaryKeySelective(VoteChoice record);

    int updateByPrimaryKey(VoteChoice record);
}