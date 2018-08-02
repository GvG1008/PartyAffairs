package com.zqu.pa.dao.groupalbum;

import com.zqu.pa.entity.groupalbum.GroupPicture;
import com.zqu.pa.entity.groupalbum.GroupPictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupPictureMapper {
    long countByExample(GroupPictureExample example);

    int deleteByExample(GroupPictureExample example);

    int deleteByPrimaryKey(Long imageId);

    int insert(GroupPicture record);

    int insertSelective(GroupPicture record);

    List<GroupPicture> selectByExample(GroupPictureExample example);

    GroupPicture selectByPrimaryKey(Long imageId);

    int updateByExampleSelective(@Param("record") GroupPicture record, @Param("example") GroupPictureExample example);

    int updateByExample(@Param("record") GroupPicture record, @Param("example") GroupPictureExample example);

    int updateByPrimaryKeySelective(GroupPicture record);

    int updateByPrimaryKey(GroupPicture record);
}