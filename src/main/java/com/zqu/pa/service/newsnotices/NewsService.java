package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.vo.newsnotices.HomeList;

public interface NewsService {
    
    //根据条数，返回新闻信息列表
    List<HomeList> getHomeNewsList(int num);
    
}
