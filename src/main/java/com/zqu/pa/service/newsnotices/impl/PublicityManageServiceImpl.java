package com.zqu.pa.service.newsnotices.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NewsMapper;
import com.zqu.pa.dao.newsnotices.NoticesMapper;
import com.zqu.pa.dao.newsnotices.PublicityManageMapper;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.service.newsnotices.PublicityManageService;
import com.zqu.pa.vo.newsnotices.PublicityInfo;

@Service
public class PublicityManageServiceImpl implements PublicityManageService {

    @Autowired
    NewsMapper newsDao;
    
    @Autowired
    NoticesMapper noticesDao;
    
    @Autowired
    PublicityManageMapper publicityManageDao;

    @Override
    public String insertNews(News news) {
        
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
    public String insertNotices(Notices notices) {
        
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

    @Override
    public List<PublicityInfo> getNewsList() {

        return publicityManageDao.getNewsManageList();
    }

    @Override
    public List<PublicityInfo> getNotices(int type) {
        
        return publicityManageDao.getNoticesManageList(type);
    }

    @Override
    public String checkNewsByBatch(String newsId) {
        List<Integer> Ids = this.StringToListId(newsId);
        if(Ids==null||Ids.size()==0)
            return "审核失败!";
        
        int result=0;
        try {
            //进行更新审核状态
            result=publicityManageDao.updateCheckNewsByBatch(Ids);
        }catch (Exception e) {
            return "审核失败!";
        }
        if(result==0)
            return "审核失败!";
        return "审核成功!";
    }

    @Override
    public String checkNoticesByBatch(String noticesId, int type) {
        List<Integer> Ids = this.StringToListId(noticesId);
        if(Ids==null||Ids.size()==0)
            return "审核失败!";
        
        int result=0;
        try {
            //进行更新审核状态,type分类型
            result=publicityManageDao.updateCheckNoticesByBatch(Ids, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(result==0)
            return "审核失败!";
        return "审核成功!";
    }
    
    
    /**
     * 根据Id字符串返回Id的list
     * @param Id
     * @return
     */
    public List<Integer> StringToListId(String Id) {
        if(Id==null||Id.equals(""))
            return null;
        
        List<Integer> Ids = new ArrayList<>();
        
        try {
            int index=0;
            int i=0;
            for( ; i<Id.length() ; i++) {
                if(Id.substring(i, i+1).equals("&")) {
                    if(i==0) {
                        index = i+1;
                        continue;
                    }
                    Ids.add(Integer.parseInt(Id.substring(index, i)));
                    index = i+1;
                }
            }
            if(i>index)
                Ids.add(Integer.parseInt(Id.substring(index, i)));
        } catch (NumberFormatException x) {
            return null;
        }
        return Ids;
    }

    @Override
    public int updateNews(News news) {
        news.setLastTime(new Date());
        return newsDao.updateByPrimaryKeySelective(news);
    }

    @Override
    public int updateNotices(Notices notices, int type) {
        notices.setLastTime(new Date());
        notices.setType(type);
        return noticesDao.updateByPrimaryKeySelective(notices);
    }

    @Override
    public String deleteNewsByBatch(String newsId) {
        List<Integer> Ids = this.StringToListId(newsId);
        if(Ids==null||Ids.size()==0)
            return "删除失败!";
        
        int result=0;
        try {
            //进行批量删除
            result=publicityManageDao.deleteNewsByBatch(Ids);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(result==0)
            return "删除失败!";
        return "成功删除"+result+"条新闻";
    }

    @Override
    public String deleteNoticesByBatch(String noticesId, int type) {
        List<Integer> Ids = this.StringToListId(noticesId);
        if(Ids==null||Ids.size()==0)
            return "删除失败!";
        
        int result=0;
        try {
            //进行批量删除
            result=publicityManageDao.deleteNoticesByBatch(Ids, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        if(result==0)
            return "删除失败!";
        return "成功删除"+result+"条公示";
    }

}
