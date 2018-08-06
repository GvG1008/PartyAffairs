package com.zqu.pa.dao.partyalbum;

import com.zqu.pa.entity.partyalbum.PartyAlbum;
import com.zqu.pa.entity.partyalbum.PartyAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PartyAlbumMapper {
    long countByExample(PartyAlbumExample example);

    int deleteByExample(PartyAlbumExample example);

    int deleteByPrimaryKey(Long albumId);

    int insert(PartyAlbum record);

    int insertSelective(PartyAlbum record);

    List<PartyAlbum> selectByExample(PartyAlbumExample example);

    PartyAlbum selectByPrimaryKey(Long albumId);

    int updateByExampleSelective(@Param("record") PartyAlbum record, @Param("example") PartyAlbumExample example);

    int updateByExample(@Param("record") PartyAlbum record, @Param("example") PartyAlbumExample example);

    int updateByPrimaryKeySelective(PartyAlbum record);

    int updateByPrimaryKey(PartyAlbum record);
    
    //相册浏览量字段pageviews加1
    int addPageviews(Long albumId);
}