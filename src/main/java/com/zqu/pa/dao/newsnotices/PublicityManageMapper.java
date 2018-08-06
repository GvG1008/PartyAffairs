package com.zqu.pa.dao.newsnotices;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PublicityInfo;

public interface PublicityManageMapper {
    //查询所有新闻信息除富文本，返回列表
    List<PublicityInfo> getNewsManageList(@Param("state")int state);
    //查询所有公示信息除富文本，返回列表
    List<PublicityInfo> getNoticesManageList(@Param("type")int type, @Param("state")int state);
    //批量审核新闻
    int updateCheckNewsByBatch(@Param("newsIds")List<Integer> newsIds);
    //批量审核公示，参数为类型
    int updateCheckNoticesByBatch(@Param("noticesIds")List<Integer> noticesIds,@Param("type")int type);
    //批量删除新闻
    int deleteNewsByBatch(@Param("newsIds")List<Integer> newsIds);
    //批量删除公示
    int deleteNoticesByBatch(@Param("noticesIds")List<Integer> noticesIds,@Param("type")int type);
}
