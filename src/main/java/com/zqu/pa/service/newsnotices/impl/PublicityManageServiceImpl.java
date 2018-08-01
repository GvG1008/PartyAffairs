package com.zqu.pa.service.newsnotices.impl;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NewsMapper;
import com.zqu.pa.dao.newsnotices.NoticesMapper;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.service.newsnotices.PublicityManageService;

@Service
public class PublicityManageServiceImpl implements PublicityManageService {

    @Autowired
    NewsMapper newsDao;
    
    @Autowired
    NoticesMapper noticesDao;

    @Override
    public String InsertNews(News news) {
        
        //获取当前用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return "无法获取当前session信息";
        
        //存储发布人
        news.setCreatorId(userId);
        
        //存储新闻状态
        news.setState(0);
        
        //数据库插入新闻
        int result = newsDao.insert(news);
        
        if(result==0)
            return "添加新闻失败";
        return "添加新闻成功";
    }

    @Override
    public String InsertNotices(Notices notices) {
        
        //获取当前用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return "无法获取当前session信息";
        
        //存储发布人
        notices.setCreatorId(userId);
        
        //存储公示状态
        notices.setState(0);
        
        //数据库插入新闻
        int result = noticesDao.insert(notices);

        if(result==0)
            return "添加公示失败";
        return "添加公示成功";
    }

}
