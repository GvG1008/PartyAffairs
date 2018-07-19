package com.zqu.pa.service.newsnotices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NewsMapper;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.service.newsnotices.NewsService;
import com.zqu.pa.vo.newsnotices.HomeList;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsDao;
    
    @Override
    public List<HomeList> getHomeNewsList(int num) {
        
        return newsDao.getHomeListLimit(num);
    }

}
