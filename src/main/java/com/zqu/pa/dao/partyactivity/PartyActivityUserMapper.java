package com.zqu.pa.dao.partyactivity;

import com.zqu.pa.entity.partyactivity.PartyActivityUser;
import com.zqu.pa.entity.partyactivity.PartyActivityUserExample;
import com.zqu.pa.entity.partyactivity.PartyActivityUserKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyActivityUserMapper {
    long countByExample(PartyActivityUserExample example);

    int deleteByExample(PartyActivityUserExample example);

    int deleteByPrimaryKey(PartyActivityUserKey key);

    int insert(PartyActivityUser record);

    int insertSelective(PartyActivityUser record);

    List<PartyActivityUser> selectByExample(PartyActivityUserExample example);

    PartyActivityUser selectByPrimaryKey(PartyActivityUserKey key);

    int updateByExampleSelective(@Param("record") PartyActivityUser record, @Param("example") PartyActivityUserExample example);

    int updateByExample(@Param("record") PartyActivityUser record, @Param("example") PartyActivityUserExample example);

    int updateByPrimaryKeySelective(PartyActivityUser record);

    int updateByPrimaryKey(PartyActivityUser record);
}