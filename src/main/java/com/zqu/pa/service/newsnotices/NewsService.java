package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.vo.newsnotices.HomeList;

public interface NewsService {
    
    List<HomeList> getHomeNewsList(int num);
    
}
