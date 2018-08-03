package com.zqu.pa.controller.newsnotices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.Server;
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
    
    /**
     * 返回管理员端管理新闻的列表信息,已审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/newsList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getNewsList() {
        List<PublicityInfo> listInfo = null;
        
        //获取新闻列表,1表示已审核
        listInfo  = publicityManageService.getNewsList(1);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理通知公示的列表信息,已审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/publicNoticesList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getPublicNoticesList() {
        List<PublicityInfo> listInfo = null;
        
        //获取公示列表,第一个参数0表示获取类型为通知公示,第二个参数1表示已审核
        listInfo  = publicityManageService.getNotices(0,1);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理党内公示的列表信息,已审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/partyNoticesList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getPartyNoticesList() {
        List<PublicityInfo> listInfo = null;
        
        //获取公示列表,第一个参数0表示获取类型为通知公示,第二个参数1表示已审核
        listInfo  = publicityManageService.getNotices(1,1);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理新闻的列表信息,未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/newsCheckList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getNewsCheckList() {
        List<PublicityInfo> listInfo = null;
        
        //获取新闻列表,0表示未审核
        listInfo  = publicityManageService.getNewsList(0);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理通知公示的列表信息,未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/publicNoticesCheckList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getPublicNoticesCheckList() {
        List<PublicityInfo> listInfo = null;
        
        //获取公示列表,第一个参数0表示获取类型为通知公示,第二个参数0表示未审核
        listInfo  = publicityManageService.getNotices(0,0);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理党内公示的列表信息,未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/partyNoticesCheckList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getPartyNoticesCheckList() {
        List<PublicityInfo> listInfo = null;
        
        //获取公示列表,第一个参数0表示获取类型为通知公示,第二个参数1表示已审核
        listInfo  = publicityManageService.getNotices(1,0);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 批量审核新闻
     * @param newsId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkNews/{newsId}")
    public ServerResponse checkNews(@PathVariable String newsId) {
        if(newsId==null)
            return ServerResponse.createByErrorMessage("新闻ID为空");
        
        //批量审核
        String Msg = publicityManageService.checkNewsByBatch(newsId);
        if(!Msg.equals("审核成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 批量审核通知公示
     * @param noticesId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkNotices/public/{noticesId}")
    public ServerResponse checkPublicNotices(@PathVariable String noticesId) {
        if(noticesId==null)
            return ServerResponse.createByErrorMessage("通知公示ID为空");
        
        //批量审核
        String Msg = publicityManageService.checkNoticesByBatch(noticesId,0);
        if(!Msg.equals("审核成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 批量审核党内公示
     * @param noticesId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkNotices/party/{noticesId}")
    public ServerResponse checkPartyNotices(@PathVariable String noticesId) {
        if(noticesId==null)
            return ServerResponse.createByErrorMessage("党内公示ID为空");
        
        //批量审核
        String Msg = publicityManageService.checkNoticesByBatch(noticesId,1);
        if(!Msg.equals("审核成功!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    /**
     * 管理员修改新闻信息
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateNews")
    public ServerResponse updateNews(News news) {
        if(news.getNewsId()==0)
            return ServerResponse.createByErrorMessage("操作失败");
        
        if(publicityManageService.updateNews(news)==0)
            return ServerResponse.createByErrorMessage("修改新闻信息失败!");
        return ServerResponse.createBySuccessMessage("修改新闻信息成功!");
    }
    
    /**
     * 管理员修改通知公示信息（不可修改公示类型）
     * @param notices
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateNotices/public")
    public ServerResponse updatePublicNotices(Notices notices) {
        if(notices.getNoticesId()==0)
            return ServerResponse.createByErrorMessage("操作失败");
        
        //0表示类型为通知
        if(publicityManageService.updateNotices(notices,0)==0)
            return ServerResponse.createByErrorMessage("修改通知公示信息失败!");
        return ServerResponse.createBySuccessMessage("修改通知公示信息成功!");
    }
    
    /**
     * 管理员修改党内公示信息（不可修改公示类型）
     * @param notices
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateNotices/party")
    public ServerResponse updatePartyNotices(Notices notices) {
        if(notices.getNoticesId()==0)
            return ServerResponse.createByErrorMessage("操作失败");
        
        //1表示类型为党内
        if(publicityManageService.updateNotices(notices,1)==0)
            return ServerResponse.createByErrorMessage("修改党内公示信息失败!");
        return ServerResponse.createBySuccessMessage("修改党内公示信息成功!");
    }
    
    @ResponseBody
    @RequestMapping("/deleteNews/{newsId}")
    public ServerResponse deleteNews(@PathVariable String newsId) {
        if(newsId==null)
            return ServerResponse.createByErrorMessage("新闻ID为空");
        
        //批量删除
        String Msg = publicityManageService.deleteNewsByBatch(newsId);
        if(Msg.equals("删除失败!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    @ResponseBody
    @RequestMapping("/deleteNotices/public/{noticesId}")
    public ServerResponse deletePublicNotices(@PathVariable String noticesId) {
        if(noticesId==null)
            return ServerResponse.createByErrorMessage("通知公示ID为空");
        //批量删除
        String Msg = publicityManageService.deleteNoticesByBatch(noticesId,0);
        if(Msg.equals("删除失败!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
    
    @ResponseBody
    @RequestMapping("/deleteNotices/party/{noticesId}")
    public ServerResponse deletePartyNotices(@PathVariable String noticesId) {
        if(noticesId==null)
            return ServerResponse.createByErrorMessage("党内公示ID为空");
        //批量删除
        String Msg = publicityManageService.deleteNoticesByBatch(noticesId,1);
        if(Msg.equals("删除失败!"))
            return ServerResponse.createByErrorMessage(Msg);
        return ServerResponse.createBySuccessMessage(Msg);
    }
}
