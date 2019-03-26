package com.zqu.pa.controller.newsnotices;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.newsnotices.NewsService;
import com.zqu.pa.service.newsnotices.NoticesService;
import com.zqu.pa.vo.newsnotices.HomeList;

@Controller
@RequestMapping("/homelist")
public class HomeListController {
    
    @Autowired
    private NewsService newsService;

    @Autowired
    private NoticesService noticesService;
    
    /**
     * 返回主页所显示的新闻信息列表，num条
     * @return
     */
    @ResponseBody
    @RequestMapping("/newslist/{num}")
    public ServerResponse<List<HomeList>> getNewsList(@PathVariable int num){
        
        List<HomeList> newslist = newsService.getHomeNewsList(num);
        return ServerResponse.createBySuccess("success", newslist);
    }
    
    /**
     * 返回主页所显示的通知公示信息列表，num条
     * @return
     */
    @ResponseBody
    @RequestMapping("/noticeslist/public/{num}")
    public ServerResponse<List<HomeList>> getNoticesListPublic(@PathVariable int num){
        
        List<HomeList> newslist = noticesService.getHomeNewsList(num,0);
        if(newslist!=null)
            return ServerResponse.createBySuccess("success", newslist);
        else
            return ServerResponse.createByErrorMessage("error");
    }
    
    /**
     * 返回主页所显示的党内公示信息列表，num条
     * @return
     */
    @ResponseBody
    @RequestMapping("/noticeslist/party/{num}")
    public ServerResponse<List<HomeList>> getNoticesListParty(@PathVariable int num){
        //此处获取session里用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null) {
        	return ServerResponse.createBySuccess("success", null);
        }            
    	
        List<HomeList> newslist = noticesService.getHomeNewsList(num,1);
        if(newslist!=null)
            return ServerResponse.createBySuccess("success", newslist);
        else
            return ServerResponse.createByErrorMessage("error");
    }

}
