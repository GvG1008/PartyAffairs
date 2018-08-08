package com.zqu.pa.dao.partyactivity;

import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.entity.partyactivity.PartyActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyActivityMapper {
    long countByExample(PartyActivityExample example);

    int deleteByExample(PartyActivityExample example);

    int deleteByPrimaryKey(Integer activityId);

    int insert(PartyActivity record);

    int insertSelective(PartyActivity record);

    List<PartyActivity> selectByExampleWithBLOBs(PartyActivityExample example);

    List<PartyActivity> selectByExample(PartyActivityExample example);

    PartyActivity selectByPrimaryKey(Integer activityId);

    int updateByExampleSelective(@Param("record") PartyActivity record, @Param("example") PartyActivityExample example);

    int updateByExampleWithBLOBs(@Param("record") PartyActivity record, @Param("example") PartyActivityExample example);

    int updateByExample(@Param("record") PartyActivity record, @Param("example") PartyActivityExample example);

    int updateByPrimaryKeySelective(PartyActivity record);

    int updateByPrimaryKeyWithBLOBs(PartyActivity record);

    int updateByPrimaryKey(PartyActivity record);
}