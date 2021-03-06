package com.zqu.pa.controller.newsnotices;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.zqu.pa.vo.newsnotices.MenuList;
import com.zqu.pa.vo.newsnotices.PageOfList;

@Controller
public class MenuListController {

    @Autowired
    NewsService newsService;
    
    @Autowired
    NoticesService noticesService;

    /**
     * 根据路径传来的页数，返回新闻列表页面信息
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/newsMenu/{pageNum}/{num}")
    public ServerResponse<PageOfList> getNewsMenu(@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num){
        PageOfList info = new PageOfList();
        
        //传入跳转的页数与当前显示的条数,page为页数，num为每页条数
        info = newsService.getMenuInfo(page,num);
        
        if(info==null||info.getList()==null)
            return ServerResponse.createByErrorMessage("获取失败");
        return ServerResponse.createBySuccess("获取新闻列表", info);
    }

    /**
     * 根据路径传来的页数，返回公示列表页面信息
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/noticesMenu/public/{pageNum}/{num}")
    public ServerResponse<PageOfList> getPublicMenu(@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num){
        PageOfList info = new PageOfList();
        
        //传入跳转的页数与当前显示的条数,page为页数，num为每页条数
        info = noticesService.getMenuInfo(page,num,0);

        if(info==null||info.getList()==null)
            return ServerResponse.createByErrorMessage("获取失败");
        return ServerResponse.createBySuccess("获取列表成功", info);
    }

    /**
     * 根据路径传来的页数，返回公示列表页面信息
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/noticesMenu/party/{pageNum}/{num}")
    public ServerResponse<PageOfList> getPartyMenu(@PathVariable(value="pageNum") int page,@PathVariable(value="num") int num){
        //此处获取session里用户userId
        String userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null) {
        	return ServerResponse.createBySuccess("success", null);
        }   
    	
    	PageOfList info = new PageOfList();
        
        //传入跳转的页数与当前显示的条数,page为页数，num为每页条数
        info = noticesService.getMenuInfo(page,num,1);

        if(info==null||info.getList()==null)
            return ServerResponse.createByErrorMessage("获取失败");
        return ServerResponse.createBySuccess("获取列表成功", info);
    }
}
