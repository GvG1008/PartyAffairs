package com.zqu.pa.controller.newsnotices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.newsnotices.NewsService;
import com.zqu.pa.service.newsnotices.NoticesService;
import com.zqu.pa.vo.newsnotices.HomeList;

@Controller
@RequestMapping("/homelist")
public class HomeListController {
    
    @Autowired
    NewsService newsService;

    @Autowired
    NoticesService noticesService;
    
    /**
     * 返回主页所显示的新闻信息列表，7条
     * @return
     */
    @ResponseBody
    @RequestMapping("/newslist")
    public ServerResponse<List<HomeList>> getNewsList(){
        
        List<HomeList> newslist = newsService.getHomeNewsList(7);
        return ServerResponse.createBySuccess("成功信息", newslist);
    }
    
    /**
     * 返回主页所显示的通知公示信息列表，7条
     * @return
     */
    @ResponseBody
    @RequestMapping("/noticeslist/public")
    public ServerResponse<List<HomeList>> getNoticesListPublic(){
        
        List<HomeList> newslist = noticesService.getHomeNewsList(7,0);
        if(newslist!=null)
            return ServerResponse.createBySuccess("success", newslist);
        else
            return ServerResponse.createByErrorMessage("error");
    }
    
    /**
     * 返回主页所显示的党内公示信息列表，7条
     * @return
     */
    @ResponseBody
    @RequestMapping("/noticeslist/party")
    public ServerResponse<List<HomeList>> getNoticesListParty(){
        
        List<HomeList> newslist = noticesService.getHomeNewsList(7,1);
        if(newslist!=null)
            return ServerResponse.createBySuccess("success", newslist);
        else
            return ServerResponse.createByErrorMessage("error");
    }

}
