package com.zqu.pa.service.newsnotices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NewsMapper;
import com.zqu.pa.dao.newsnotices.PublicityListMapper;
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
    
    @Autowired
    PublicityListMapper publicityListDao;
    
    @Override
    public List<HomeList> getHomeNewsList(int num) {
        
        return publicityListDao.getNewsHomeListLimit(num);
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
        else if(page<=0)
            page = 1;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录
        int index = (page-1)*num;
        info.setList(publicityListDao.getNewsMenuListLimit(index, num));
        
        return info;
    }

    @Override
    public News getNewsInfo(int news_id, int type) {
        
        News news = new News();
        news = newsDao.selectByPrimaryKey(news_id);
        if(news==null)
            return null;
        //获取通过审核的
        if(type==1) {
            //若未通过审核，返回null
            if(news.getState()==0)
                return null;
            else {
                //查看该条新闻信息页面，此新闻click+1
                News news2 = new News();
                news2.setNewsId(news.getNewsId());
                news2.setClick(news.getClick()+1);
                if(newsDao.updateByPrimaryKeySelective(news2)==0)
                    return null;
                news.setClick(news.getClick()+1);
                //显示在公众页面,删除多余信息
                news.setCreatorId(null);
                news.setLastTime(null);
                news.setState(null);
            }
        }
        return news;
    }
}
