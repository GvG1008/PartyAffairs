package com.zqu.pa.dao.partyactivity;

import com.zqu.pa.entity.partyactivity.PartyActivityRoleExample;
import com.zqu.pa.entity.partyactivity.PartyActivityRoleKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyActivityRoleMapper {
    long countByExample(PartyActivityRoleExample example);

    int deleteByExample(PartyActivityRoleExample example);

    int deleteByPrimaryKey(PartyActivityRoleKey key);

    int insert(PartyActivityRoleKey record);

    int insertSelective(PartyActivityRoleKey record);

    List<PartyActivityRoleKey> selectByExample(PartyActivityRoleExample example);

    int updateByExampleSelective(@Param("record") PartyActivityRoleKey record, @Param("example") PartyActivityRoleExample example);

    int updateByExample(@Param("record") PartyActivityRoleKey record, @Param("example") PartyActivityRoleExample example);
}