package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PageOfList;

public interface NewsService {
    
    //根据条数，返回新闻信息列表
    List<HomeList> getHomeNewsList(int num);
    
    //根据页数和每页记录数，返回列表信息
    PageOfList getMenuInfo(int page, int num);

    //根据id返回此新闻所有信息,
    News getNewsInfo(int news_id, int type);
    
}
