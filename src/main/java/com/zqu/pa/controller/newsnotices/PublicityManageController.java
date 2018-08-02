package com.zqu.pa.controller.newsnotices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.service.newsnotices.PublicityManageService;
import com.zqu.pa.vo.newsnotices.PublicityInfo;

@Controller
@RequestMapping("/publicityManage")
public class PublicityManageController {

    @Autowired
    PublicityManageService publicityManageService;
    
    /**
     * 添加插入未审核的新闻
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertNews",method=RequestMethod.POST)
    public ServerResponse InsertNews(News news) {
        
        String Msg;
        try {
            Msg = publicityManageService.InsertNews(news);
            
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加新闻失败!");
        }
        
        if(Msg==null)
            return ServerResponse.createByErrorMessage("添加新闻失败!");
        if(!Msg.equals("添加新闻成功"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 添加插入未审核的公共公示
     * @param notices
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertPublicNotices",method=RequestMethod.POST)
    public ServerResponse InsertPublicNotices(Notices notices) {
        
        String Msg;
        try {
            //存储公示的类型为公共公示
            notices.setType(0);
            Msg = publicityManageService.InsertNotices(notices);
            
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加公示失败!");
        }
        
        if(Msg==null)
            return ServerResponse.createByErrorMessage("添加公示失败!");
        if(!Msg.equals("添加公示成功"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 添加插入未审核的党内公示
     * @param notices
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertPartyNotices",method=RequestMethod.POST)
    public ServerResponse InsertPartyNotices(Notices notices) {
        
        String Msg;
        try {
            //存储公示的类型为党内公示
            notices.setType(1);
            Msg = publicityManageService.InsertNotices(notices);
            
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("添加公示失败!");
        }
        
        if(Msg==null)
            return ServerResponse.createByErrorMessage("添加公示失败!");
        if(!Msg.equals("添加公示成功"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    @ResponseBody
    @RequestMapping(value="/newsList")
    public ServerResponse<List<PublicityInfo>> getNewsList() {
        List<PublicityInfo> listInfo = null;
        
        //获取列表
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败！");
        return ServerResponse.createBySuccess(listInfo);
    }
}
