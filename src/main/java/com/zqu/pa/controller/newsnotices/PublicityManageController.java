package com.zqu.pa.controller.newsnotices;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.zqu.pa.common.Const;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.newsnotices.News;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.service.newsnotices.PublicityManageService;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.utils.FTPSSMLoad;
import com.zqu.pa.utils.FTPUtil;
import com.zqu.pa.utils.IMGUtil;
import com.zqu.pa.vo.newsnotices.PublicityInfo;

@Controller
@RequestMapping("/publicityManage")
public class PublicityManageController {

    @Autowired
    private PublicityManageService publicityManageService;
    
    /**
     * 富文本框插入图片上传接口,返回访问图片路径
     * @param file
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/uploadIMG",method=RequestMethod.POST)
    public ServerResponse uploadContentIMG(@RequestParam(value = "uploadIMG",required = false) MultipartFile file,HttpServletRequest request) {
        if(file==null||file.isEmpty())
            return ServerResponse.createByErrorMessage("上传图片为空");
        String path = request.getSession().getServletContext().getRealPath("upload");
        String fileName = file.getOriginalFilename();
        Integer i = fileName.lastIndexOf(".") + 1;
        String fileExtensionName = fileName.substring(i);
        String filePrefixName = fileName.substring(0, i-1);
        Date d = new Date();
        String uploadFileName = filePrefixName + DateToString.getDateString("yyyy-MM-dd", d) + UUID.randomUUID().toString() + "." + fileExtensionName;
        File fileDir = new File(path);
        if (!fileDir.exists()) {
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);
        try {
            file.transferTo(targetFile);
            //检验文件是否为图片
            if(!this.checkIMG(targetFile))
                return ServerResponse.createByErrorMessage("上传文件不是图片文件");

            //上传至FTP
            targetFile = new File(path, uploadFileName);
            FTPUtil.uploadFile("/newsnotices/content/img/",Lists.newArrayList(targetFile));
            targetFile.delete();
            
            //返回访问路径
            String fullPath = Const.HTTP_PREFIX+"/newsnotices/content/img/"+uploadFileName;
            return ServerResponse.createBySuccess(fullPath);
            
        }catch  (IOException e) {
            e.printStackTrace();
            return ServerResponse.createByErrorMessage("修改失败");
        }
    }
    
    /**
     * 添加插入未审核的新闻
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/insertNews",method=RequestMethod.POST)
    public ServerResponse InsertNews(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="title") String title, @RequestParam(value="source")String source, 
            @RequestParam(value="content")String content,HttpServletRequest request) {

        
        
        News news = new News();
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        news.setTitle(title);
        news.setSource(source);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        news.setContent(content);
        
        //封面图上传
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/newsnotices/cover/");
            //存储路径
            news.setCoverpath(fileMap.get("http_url").toString());
        }else
            news.setCoverpath(null);
        
        String Msg;
        try {
            Msg = publicityManageService.insertNews(news);
            
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
    public ServerResponse InsertPublicNotices(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="title") String title, @RequestParam(value="content")String content,
            HttpServletRequest request) {
        
        Notices notices = new Notices();
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        notices.setTitle(title);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        notices.setContent(content);
        
        //封面图上传
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/newsnotices/cover/");
            //存储路径
            notices.setCoverpath(fileMap.get("http_url").toString());
        }else
            notices.setCoverpath(null);
        
        String Msg;
        try {
            //存储公示的类型为公共公示
            notices.setType(0);
            Msg = publicityManageService.insertNotices(notices);
            
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
    public ServerResponse InsertPartyNotices(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="title") String title, @RequestParam(value="content")String content,
            HttpServletRequest request) {
        
        Notices notices = new Notices();
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        notices.setTitle(title);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        notices.setContent(content);
        
        //封面图上传
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/newsnotices/cover/");
            //存储路径
            notices.setCoverpath(fileMap.get("http_url").toString());
        }else
            notices.setCoverpath(null);
        
        String Msg;
        try {
            //存储公示的类型为党内公示
            notices.setType(1);
            Msg = publicityManageService.insertNotices(notices);
            
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
     * 返回管理员端管理新闻的列表信息,包括已审核未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/newsList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getNewsList() {
        List<PublicityInfo> listInfo = null;
        
        //获取新闻列表
        listInfo  = publicityManageService.getNewsList();
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理通知公示的列表信息,包括已审核未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/publicNoticesList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getPublicNoticesList() {
        List<PublicityInfo> listInfo = null;
        
        //获取公示列表,参数0表示获取类型为通知公示
        listInfo  = publicityManageService.getNotices(0);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 返回管理员端管理党内公示的列表信息,包括已审核未审核
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/partyNoticesList",method=RequestMethod.GET)
    public ServerResponse<List<PublicityInfo>> getPartyNoticesList() {
        List<PublicityInfo> listInfo = null;
        
        //获取公示列表,参数0表示获取类型为通知公示
        listInfo  = publicityManageService.getNotices(1);
        
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表信息失败!");
        return ServerResponse.createBySuccess(listInfo);
    }
    
    /**
     * 批量审核新闻
     * @param newsId
     * @return
     */
    @RequiresPermissions("article:approve")
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
    @RequiresPermissions("article:approve")
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
    @RequiresPermissions("article:approve")
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
    @RequiresPermissions("article:update")
    @ResponseBody
    @RequestMapping("/updateNews")
    public ServerResponse updateNews(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="newsId")Integer newsId,
            @RequestParam(value="title") String title, @RequestParam(value="source")String source, 
            @RequestParam(value="content")String content,HttpServletRequest request) {
        if(newsId==null)
            return ServerResponse.createByErrorMessage("ID为空");
        News news = new News();
        //存储ID
        news.setNewsId(newsId);
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        //存储标题
        news.setTitle(title);
        //存储新闻来源，默认存储时必然有值，若为空则置无
        if(source==null||source.equals(""))
            news.setSource("无");
        else
            news.setSource(source);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        news.setContent(content);
        
        //修改封面图，上传图片
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/newsnotices/cover/");
            //存储路径
            news.setCoverpath(fileMap.get("http_url").toString());
        }else
            news.setCoverpath(null);
        
        if(publicityManageService.updateNews(news)==0)
            return ServerResponse.createByErrorMessage("修改新闻信息失败!");
        return ServerResponse.createBySuccessMessage("修改新闻信息成功!");
    }
    
    /**
     * 管理员修改通知公示信息（不可修改公示类型）
     * @param notices
     * @return
     */
    @RequiresPermissions("article:update")
    @ResponseBody
    @RequestMapping("/updateNotices/public")
    public ServerResponse updatePublicNotices(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="noticesId")Integer noticesId,
            @RequestParam(value="title") String title, @RequestParam(value="content")String content,
            HttpServletRequest request) {
        if(noticesId==null)
            return ServerResponse.createByErrorMessage("ID为空");
        Notices notices= new Notices();
        //存储ID
        notices.setNoticesId(noticesId);
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        //存储标题
        notices.setTitle(title);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        notices.setContent(content);
        
        //修改封面图，上传图片
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/newsnotices/cover/");
            //存储路径
            notices.setCoverpath(fileMap.get("http_url").toString());
        }else
            notices.setCoverpath(null);
        
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
    @RequiresPermissions("article:update")
    @ResponseBody
    @RequestMapping("/updateNotices/party")
    public ServerResponse updatePartyNotices(@RequestParam(value="coverpath",required = false) MultipartFile cover,
            @RequestParam(value="noticesId")Integer noticesId,
            @RequestParam(value="title") String title, @RequestParam(value="content")String content,
            HttpServletRequest request) {
        if(noticesId==null)
            return ServerResponse.createByErrorMessage("ID为空");
        Notices notices= new Notices();
        //存储ID
        notices.setNoticesId(noticesId);
        if(title==null)
            return ServerResponse.createByErrorMessage("标题不能为空");
        //存储标题
        notices.setTitle(title);
        if(content==null)
            return ServerResponse.createByErrorMessage("内容不能为空");
        notices.setContent(content);
        
        //修改封面图，上传图片
        if(!cover.isEmpty()) {
            //判断是否为图片
            //。。
            //FTP上传
            Map fileMap = FTPSSMLoad.upload(cover, request, "/newsnotices/cover/");
            //存储路径
            notices.setCoverpath(fileMap.get("http_url").toString());
        }else
            notices.setCoverpath(null);
        
        //1表示类型为党内
        if(publicityManageService.updateNotices(notices,1)==0)
            return ServerResponse.createByErrorMessage("修改党内公示信息失败!");
        return ServerResponse.createBySuccessMessage("修改党内公示信息成功!");
    }
    
    /**
     * 批量删除新闻
     * @param newsId 一串newsId的字符串"&newsId1&newsId2&newsId3.."
     * @return
     */
    @RequiresPermissions("article:delete")
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
    
    /**
     * 批量删除通知公示
     * @param noticesId 一串noticesId的字符串"noticesId1&noticesId2&noticesId3.."
     * @return
     */
    @RequiresPermissions("article:delete")
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
    
    /**
     * 批量删除党内公示
     * @param noticesId 一串noticesId的字符串"&noticesId1&noticesId2&noticesId3.."
     * @return
     */
    @RequiresPermissions("article:delete")
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
    
    /**
     * 判断是否为图片
     * @param file
     * @return
     */
    public boolean checkIMG(File file){
        try {
            Image image = ImageIO.read(file);
            return image != null;
        } catch(IOException ex) {
            return false;
        }
    }
}
