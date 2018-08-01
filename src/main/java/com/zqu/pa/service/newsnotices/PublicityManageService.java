package com.zqu.pa.service.newsnotices;

import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;

public interface PublicityManageService {

    //添加未审核的新闻,返回结果消息
    String InsertNews(News news);

    //添加未审核的公示，返回结果信息
    String InsertNotices(Notices notices);

}
