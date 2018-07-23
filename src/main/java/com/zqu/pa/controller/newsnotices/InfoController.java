package com.zqu.pa.controller.newsnotices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.service.newsnotices.NewsService;
import com.zqu.pa.service.newsnotices.NoticesService;

@Controller
public class InfoController {
    
    @Autowired
    NewsService newsService;
    
    @Autowired
    NoticesService noticesService;
    
    /**
     * 显示新闻详细内容页面的信息，不包括未审核的,返回新闻信息不是全部的
     * @param news_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/news/{news_id}")
    public ServerResponse<News> getNewsInfo(@PathVariable int news_id){
        
        News news = newsService.getNewsInfo(news_id,1);
        
        if(news!=null)
            return ServerResponse.createBySuccess("获取新闻信息成功", news);
        else
            return ServerResponse.createByErrorMessage("获取新闻信息失败");
    }
    
    /**
     * 显示新闻详细内容页面的信息，包括未审核的，返回新闻全部信息
     * @param news_id
     * @return
     */
    @ResponseBody
    @RequestMapping("/allNews/{news_id}")
    public ServerResponse<News> getAllNewsInfo(@PathVariable int news_id){
        
        News news = newsService.getNewsInfo(news_id,0);
        
        if(news!=null)
            return ServerResponse.createBySuccess("获取新闻信息成功", news);
        else
            return ServerResponse.createByErrorMessage("获取新闻信息失败");
    }
    
    @ResponseBody
    @RequestMapping("/notices/public/{notices_id}")
    public ServerResponse<Notices> getPublicNoticesInfo(@PathVariable int notices_id){
        Notices notices = noticesService.getNoticesInfo(notices_id,0,1);
        
        if(notices!=null)
            return ServerResponse.createBySuccess("获取通知公示信息成功", notices);
        else
            return ServerResponse.createByErrorMessage("获取通知公示信息失败");
    }
    
    @ResponseBody
    @RequestMapping("/notices/party/{notices_id}")
    public ServerResponse<Notices> getPartyNoticesInfo(@PathVariable int notices_id){
        Notices notices = noticesService.getNoticesInfo(notices_id,1,1);
        
        if(notices!=null)
            return ServerResponse.createBySuccess("获取党内公示信息成功", notices);
        else
            return ServerResponse.createByErrorMessage("获取党内公示信息失败");
    }
    
    @ResponseBody
    @RequestMapping("/allNotices/public/{notices_id}")
    public ServerResponse<Notices> getAllPublicNoticesInfo(@PathVariable int notices_id){
        Notices notices = noticesService.getNoticesInfo(notices_id,0,0);
        
        if(notices!=null)
            return ServerResponse.createBySuccess("获取通知公示信息成功", notices);
        else
            return ServerResponse.createByErrorMessage("获取通知公示信息失败");
    }

    @ResponseBody
    @RequestMapping("/allNotices/party/{notices_id}")
    public ServerResponse<Notices> getAllPartyNoticesInfo(@PathVariable int notices_id){
        Notices notices = noticesService.getNoticesInfo(notices_id,1,0);
        
        if(notices!=null)
            return ServerResponse.createBySuccess("获取党内公示信息成功", notices);
        else
            return ServerResponse.createByErrorMessage("获取党内公示信息失败");
    }
}
