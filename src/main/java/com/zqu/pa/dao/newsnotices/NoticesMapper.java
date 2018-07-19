package com.zqu.pa.dao.newsnotices;

import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.entity.newsnotices.NoticesExample;
import com.zqu.pa.vo.newsnotices.HomeList;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NoticesMapper {
    long countByExample(NoticesExample example);

    int deleteByExample(NoticesExample example);

    int deleteByPrimaryKey(Integer noticesId);

    int insert(Notices record);

    int insertSelective(Notices record);

    List<Notices> selectByExampleWithBLOBs(NoticesExample example);

    List<Notices> selectByExample(NoticesExample example);

    Notices selectByPrimaryKey(Integer noticesId);

    int updateByExampleSelective(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByExampleWithBLOBs(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByExample(@Param("record") Notices record, @Param("example") NoticesExample example);

    int updateByPrimaryKeySelective(Notices record);

    int updateByPrimaryKeyWithBLOBs(Notices record);

    int updateByPrimaryKey(Notices record);
    
    //以上为逆向工程生成
    List<HomeList> getHomeListLimit(@Param("limit") int limit,@Param("type") int type);
}