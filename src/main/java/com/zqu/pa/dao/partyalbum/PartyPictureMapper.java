package com.zqu.pa.dao.partyalbum;

import com.zqu.pa.entity.partyalbum.PartyPicture;
import com.zqu.pa.entity.partyalbum.PartyPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyPictureMapper {
    long countByExample(PartyPictureExample example);

    int deleteByExample(PartyPictureExample example);

    int deleteByPrimaryKey(Long imageId);

    int insert(PartyPicture record);

    int insertSelective(PartyPicture record);

    List<PartyPicture> selectByExample(PartyPictureExample example);

    PartyPicture selectByPrimaryKey(Long imageId);

    int updateByExampleSelective(@Param("record") PartyPicture record, @Param("example") PartyPictureExample example);

    int updateByExample(@Param("record") PartyPicture record, @Param("example") PartyPictureExample example);

    int updateByPrimaryKeySelective(PartyPicture record);

    int updateByPrimaryKey(PartyPicture record);
}