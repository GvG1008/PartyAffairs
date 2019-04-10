package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.vo.newsnotices.PublicityInfo;

public interface PublicityManageService {

    //添加未审核的新闻,返回结果消息
    String insertNews(News news);

    //添加未审核的公示，返回结果信息
    String insertNotices(Notices notices);

    //返回全部新闻的列表信息，包括审核未审核
    List<PublicityInfo> getNewsList();

    //返回全部公示的列表信息，根据参数返回相应的公示类型，包括审核未审核
    List<PublicityInfo> getNotices(int type);

    //批量审核新闻
    String checkNewsByBatch(String newsId);
    
    //批量审核公示
    String checkNoticesByBatch(String noticesId, int type);

    //修改新闻信息
    int updateNews(News news);

    //修改公示信息，type区分类型
    int updateNotices(Notices notices, int type);

    //批量删除新闻
    String deleteNewsByBatch(String newsId);

    //批量删除公示，type表示类型
    String deleteNoticesByBatch(String noticesId, int type);

}
