package com.zqu.pa.dao.newsnotices;

import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.entity.newsnotices.NoticesExample;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.MenuList;

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

    //根据数目和公示类型号，获取审核通过，仅有标题与日期的公示列表，按日期排序
    List<HomeList> getHomeListLimit(@Param("limit") int limit,@Param("type") int type);
    List<HomeList> getMenuListLimit(@Param("index")int index,@Param("num") int num,@Param("type") int type);
    
}