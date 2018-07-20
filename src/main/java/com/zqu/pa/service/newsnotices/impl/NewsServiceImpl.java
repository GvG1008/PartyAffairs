package com.zqu.pa.service.newsnotices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NewsMapper;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.NewsExample;
import com.zqu.pa.entity.newsnotices.NewsExample.Criteria;
import com.zqu.pa.service.newsnotices.NewsService;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PageOfList;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsDao;
    
    @Override
    public List<HomeList> getHomeNewsList(int num) {
        
        return newsDao.getHomeListLimit(num);
    }

    @Override
    public PageOfList getMenuInfo(int page, int num) {
        PageOfList info = new PageOfList();
        
        NewsExample example = new NewsExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);
        
        //总记录条数
        int totalInfoNum = (int) newsDao.countByExample(example);
        info.setTotalInfoNum(totalInfoNum);
        //总页数
        int totalPageNum = (int)(totalInfoNum+num-1)/num;
        info.setTotalPageNum(totalPageNum);
        
        if(totalPageNum<page)
            page = totalPageNum;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录
        int index = (page-1)*num;
        info.setList(newsDao.getMenuListLimit(index, num));
        
        return info;
    }

}
