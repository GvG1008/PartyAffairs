package com.zqu.pa.dao.vote;

import com.zqu.pa.entity.vote.VoteUserExample;
import com.zqu.pa.entity.vote.VoteUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VoteUserMapper {
    long countByExample(VoteUserExample example);

    int deleteByExample(VoteUserExample example);

    int deleteByPrimaryKey(VoteUserKey key);

    int insert(VoteUserKey record);

    int insertSelective(VoteUserKey record);

    List<VoteUserKey> selectByExample(VoteUserExample example);

    int updateByExampleSelective(@Param("record") VoteUserKey record, @Param("example") VoteUserExample example);

    int updateByExample(@Param("record") VoteUserKey record, @Param("example") VoteUserExample example);
}