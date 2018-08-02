package com.zqu.pa.dao.newsnotices;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.newsnotices.HomeList;

public interface PublicityListMapper {
    
    //根据数目，获取审核通过，仅有标题与日期的新闻列表，按日期排序
    List<HomeList> getNewsHomeListLimit(@Param("limit") int limit);
    List<HomeList> getNewsMenuListLimit(@Param("index") int index , @Param("num") int num);
    
    //根据数目和公示类型号，获取审核通过，仅有标题与日期的公示列表，按日期排序
    List<HomeList> getNoticesHomeListLimit(@Param("limit") int limit,@Param("type") int type);
    List<HomeList> getNoticesMenuListLimit(@Param("index")int index,@Param("num") int num,@Param("type") int type);
    
}
