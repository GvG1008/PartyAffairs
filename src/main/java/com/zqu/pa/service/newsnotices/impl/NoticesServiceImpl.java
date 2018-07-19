package com.zqu.pa.service.newsnotices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NoticesMapper;
import com.zqu.pa.service.newsnotices.NoticesService;
import com.zqu.pa.vo.newsnotices.HomeList;

@Service
public class NoticesServiceImpl implements NoticesService{

    @Autowired
    NoticesMapper noticesDao;
    
    @Override
    public List<HomeList> getHomeNewsList(int num, int type) {
        return noticesDao.getHomeListLimit(num, type);
    }
}
