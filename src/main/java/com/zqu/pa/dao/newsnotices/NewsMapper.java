package com.zqu.pa.dao.newsnotices;

import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.NewsExample;
import com.zqu.pa.vo.newsnotices.HomeList;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NewsMapper {
    long countByExample(NewsExample example);

    int deleteByExample(NewsExample example);

    int deleteByPrimaryKey(Integer newsId);

    int insert(News record);

    int insertSelective(News record);

    List<News> selectByExampleWithBLOBs(NewsExample example);

    List<News> selectByExample(NewsExample example);

    News selectByPrimaryKey(Integer newsId);

    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExampleWithBLOBs(@Param("record") News record, @Param("example") NewsExample example);

    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKeyWithBLOBs(News record);

    int updateByPrimaryKey(News record);
    

    //以上为逆向工程生成
    //根据数目，获取审核通过，仅有标题与日期的新闻列表，按日期排序
    List<HomeList> getHomeListLimit(@Param("limit") int limit);
}