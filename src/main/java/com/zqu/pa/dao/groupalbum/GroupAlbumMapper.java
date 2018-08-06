package com.zqu.pa.dao.groupalbum;

import com.zqu.pa.entity.groupalbum.GroupAlbum;
import com.zqu.pa.entity.groupalbum.GroupAlbumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupAlbumMapper {
    long countByExample(GroupAlbumExample example);

    int deleteByExample(GroupAlbumExample example);

    int deleteByPrimaryKey(Long albumId);

    int insert(GroupAlbum record);

    int insertSelective(GroupAlbum record);

    List<GroupAlbum> selectByExample(GroupAlbumExample example);

    GroupAlbum selectByPrimaryKey(Long albumId);

    int updateByExampleSelective(@Param("record") GroupAlbum record, @Param("example") GroupAlbumExample example);

    int updateByExample(@Param("record") GroupAlbum record, @Param("example") GroupAlbumExample example);

    int updateByPrimaryKeySelective(GroupAlbum record);

    int updateByPrimaryKey(GroupAlbum record);
    
    //相册浏览量字段pageviews加1
    int addPageviews(Long albumId);
}