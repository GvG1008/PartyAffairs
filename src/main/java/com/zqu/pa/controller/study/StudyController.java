package com.zqu.pa.controller.study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zqu.pa.common.Const;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.study.StudyVideoRecordMapper;
import com.zqu.pa.entity.study.StudyDocument;
import com.zqu.pa.entity.study.StudyDocumentLabel;
import com.zqu.pa.entity.study.StudyDocumentMust;
import com.zqu.pa.entity.study.StudyLabel;
import com.zqu.pa.entity.study.StudyVideo;
import com.zqu.pa.entity.study.StudyVideoLabel;
import com.zqu.pa.entity.study.StudyVideoMust;
import com.zqu.pa.entity.study.StudyVideoRecord;
import com.zqu.pa.service.study.IStudyService;
import com.zqu.pa.utils.FTPSSMLoad;
import com.zqu.pa.utils.StringExtend;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

/**
 * 学习模块Controller
 * 
 * @author Lee
 *
 */
@Controller
@RequestMapping("/study/")
public class StudyController {

    @Autowired
    private IStudyService iStudyService;
    @Autowired
    private StudyVideoRecordMapper studyVideoRecordMapper;

    private String getUserid() {
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        String userId = basicInfo.getUserId();
        return userId;
    }
    private boolean isNeedLogin(HttpSession session) {
        String userId =  (String)session.getAttribute("userId");
        if(userId.equals("")||userId==null)
            return true;
        return false;
    }
    /**
     * 新建标签
     * 
     * @param labelName
     * @param session
     * @return
     */
    @RequestMapping(value = "create_label.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse creatLabel(@RequestParam(value = "label_name", required = true) String labelName,
            HttpSession session) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        boolean isNullOrEmpty = StringExtend.isNullOrEmpty(labelName);
        if (isNullOrEmpty)
            return ServerResponse.createByErrorMessage("label_name不可为空");
        else
            return iStudyService.createLabel(labelName);
    }

    /**
     * 获取全部标签
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "get_labels.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getLabels(HttpSession session) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        return iStudyService.getLabels();
    }

    /**
     * 修改标签
     * 
     * @param labelId
     * @param labelName
     * @param session
     * @return
     */
    @RequestMapping(value = "change_label.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse changeLabel(@RequestParam(value = "label_id", required = true) String labelId,
            @RequestParam(value = "label_name", required = true) String labelName, HttpSession session) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        int id = 0;
        try {
            id = Integer.parseInt(labelId);
        } catch (NumberFormatException nfe) {
            return ServerResponse.createByErrorMessage("label_id为整数");
        }
        boolean isNullOrEmpty = StringExtend.isNullOrEmpty(labelName);
        if (isNullOrEmpty)
            return ServerResponse.createByErrorMessage("label_name不可为空");
        StudyLabel studyLabel = new StudyLabel(id, labelName, null, null);
        return iStudyService.changeLabel(studyLabel);
    }

    /**
     * 上传文档资料
     * 
     * @param file
     * @param img
     * @param request
     * @param session
     * @param title
     * @param introduction
     * @param labels
     * @return
     */
    @RequestMapping(value = "upload_study_document.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadStudyDocument(@RequestParam(value = "upload_file") MultipartFile file,
            @RequestParam(value = "upload_img") MultipartFile img, HttpServletRequest request, HttpSession session,
            @RequestParam(value = "document_title") String title,
            @RequestParam(value = "document_introduction") String introduction,
            @RequestParam(value = "label_id") String[] labelId) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        // TODO 校验参数的格式
        String userID = this.getUserid();
        System.out.println(file.isEmpty() + " -- " + img.isEmpty());
        Map fileMap = FTPSSMLoad.upload(file, request, "/document/");
        Map imgMap = FTPSSMLoad.upload(img, request, "/document/");
        StudyDocument sd = new StudyDocument(null, title, introduction, imgMap.get("http_url").toString(),
                fileMap.get("download_url").toString(), userID, null, null);
        int labelCount = labelId.length;
       // int userCount = userId.length;
        ArrayList<StudyDocumentLabel> studyDocumentLabelList = Lists.newArrayList();
        ArrayList<StudyDocumentMust> studyDocumentMustList = Lists.newArrayList();
        for (String lid : labelId) {
            StudyDocumentLabel sdl = new StudyDocumentLabel(null, null, Integer.parseInt(lid));
            studyDocumentLabelList.add(sdl);
        }
        //for (String uid : userId) {
            StudyDocumentMust sdm = new StudyDocumentMust();
            sdm.setUserId(userID);
            studyDocumentMustList.add(sdm);
       // }
        System.out.println(sd);
        System.out.println(studyDocumentLabelList);
        return iStudyService.uploadStudyDocument(sd, studyDocumentLabelList,studyDocumentMustList);
    }

    /**
     * 获取全部文档学习资料(上架)
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_documents.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocuments(HttpSession session,String page,String pageNum) {
        return iStudyService.getStudyDocumentsPuton(Integer.parseInt(page),Integer.parseInt(pageNum));
    }

    /**
     * 根据标签id获取文档学习资料(上架)
     * 
     * @param labelId
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_documents_by_label_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocumentsByLabelId(@RequestParam(value = "label_id") String[] labelId,
            HttpSession session,String page,String pageNum) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        int idCount = labelId.length;
        for (int i = 0; i < idCount; i++)
            hashSet.add(Integer.parseInt(labelId[i]));
        ArrayList<Integer> idList = Lists.newArrayList();
        for (Integer labelid : hashSet) {
            idList.add(labelid);
        }
        return iStudyService.getStudyDocumentsPutonByLabelId(idList,Integer.parseInt(page),Integer.parseInt(pageNum));
    }

    /**
     * 根据标签id获取必学文档学习资料(上架)
     * @param labelId
     * @param session
     * @param page
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "get_study_documents_must_by_label_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocumentsMustByLabelId(@RequestParam(value = "label_id") String[] labelId,
            HttpSession session,String page,String pageNum) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        int idCount = labelId.length;
        for (int i = 0; i < idCount; i++)
            hashSet.add(Integer.parseInt(labelId[i]));
        ArrayList<Integer> idList = Lists.newArrayList();
        for (Integer labelid : hashSet) {
            idList.add(labelid);
        }
        String userId = this.getUserid();
        return iStudyService.getStudyDocumentsMustPutonByLabelId(userId,idList,Integer.parseInt(page),Integer.parseInt(pageNum));
    }
    
    /**
     * 获取用户必学的文档资料
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_documents_must.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocumentMust(HttpSession session,String page,String pageNum) {
        String userId = this.getUserid();
        return iStudyService.getStudyDocumentMust(userId,Integer.parseInt(page),Integer.parseInt(pageNum));
    }
    
    /**
     * 下载接口
     * 
     * @param response
     * @param session
     * @param path
     * @param filename
     * @return
     */
    @RequestMapping(value = "download_document.do")
    @ResponseBody
    public ServerResponse downDocument(HttpServletResponse response, HttpSession session, String path,
            String filename) {
        FTPSSMLoad.download(response, path, filename);
        String userId = this.getUserid();
        System.out.println(userId);
        String downloadURL = Const.DOWN_INTERFACE + "path=" + path + "&filename=" + filename;
        return iStudyService.statisticsDownload(userId, downloadURL);
    }

    /**
     * 根据文档id获取文档资料详情
     * @param documentId
     * @return
     */
    @RequestMapping(value = "get_study_document_details.do")
    @ResponseBody
    public ServerResponse getStudyDocumentDetails(@RequestParam(value = "document_id", required = true) String documentId) {
        return iStudyService.getStudyDcumentDetails(Integer.parseInt(documentId));
    }
    
    /*GvG*/
    /**
     * 在用户查看文档时，该接口可查询返回该文档是否已学，根据此显示点击已学按钮
     * @param documentId
     * @return
     */
    @RequestMapping(value = "get_study_document_already_state/{documentId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getStudyDocumentMustState(@PathVariable(value = "documentId") Integer documentId) {
        String userId = this.getUserid();
        if(userId == null)
            return ServerResponse.createByErrorMessage("无法获取当前登录信息");
        if(documentId == null)
            return ServerResponse.createByErrorMessage("文档ID错误");
        return iStudyService.getStudyDocumentMustAlreadyState(userId,documentId);
    }
    
    /**
     * 用户点击已学，将用户该文档学习状态改为已学
     * @param documentId
     * @return
     */
    @RequestMapping(value = "set_study_document_already/{documentId}", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setStudyDocumentMustState(@PathVariable(value = "documentId") Integer documentId) {
        String userId = this.getUserid();
        if(userId == null)
            return ServerResponse.createByErrorMessage("无法获取当前登录信息");
        if(documentId == null)
            return ServerResponse.createByErrorMessage("文档ID错误");
        return iStudyService.setStudyDocumentMustState(userId,documentId);
        
    }
    
    /*GvG*/
    
    
    
    
    
    
    
    /**
     * 上传视频学习资料
     * @param file
     * @param img
     * @param request
     * @param session
     * @param title
     * @param introduction
     * @param labelId
     * @param userId
     * @return
     */
    @RequestMapping(value = "upload_study_video.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadStudyVideo(@RequestParam(value = "upload_file") MultipartFile file,
            @RequestParam(value = "upload_img") MultipartFile img, HttpServletRequest request, HttpSession session,
            @RequestParam(value = "video_title") String title,
            @RequestParam(value = "video_introduction") String introduction,
            @RequestParam(value = "label_id") String[] labelId) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        // TODO 校验参数的格式
        String userID = this.getUserid();
        System.out.println(file.isEmpty() + " -- " + img.isEmpty());
        Map fileMap = FTPSSMLoad.upload(file, request, "/video/");
        Map imgMap = FTPSSMLoad.upload(img, request, "/video/");
        StudyVideo sv = new StudyVideo(null, title, introduction, imgMap.get("http_url").toString(), fileMap.get("http_url").toString(), userID, null, null);
        int labelCount = labelId.length;
        //int userCount = userId.length;
        ArrayList<StudyVideoLabel> studyVideoLabelList = Lists.newArrayList();
        ArrayList<StudyVideoMust> studyVideoMustList = Lists.newArrayList();
        for(String lid : labelId) {
            StudyVideoLabel svl = new StudyVideoLabel(null, null, Integer.parseInt(lid));
            studyVideoLabelList.add(svl);
        }
       // for(String uid : userId) {
            StudyVideoMust svm = new StudyVideoMust();
            svm.setUserId(userID);
            studyVideoMustList.add(svm);
       // }
        return iStudyService.uploadStudyVideo(sv, studyVideoLabelList, studyVideoMustList);
    }
    
    /**
     * 获取全部视频学习资料(上架)
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_videos.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyVideos(HttpSession session,String page,String pageNum) {
        return iStudyService.getStudyVideosPuton(Integer.parseInt(page),Integer.parseInt(pageNum));
    }
    
    /**
     * 根据标签id获取视频学习资料(上架)
     * 
     * @param labelId
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_videos_by_label_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyVideosByLabelId(@RequestParam(value = "label_id") String[] labelId,
            HttpSession session,String page,String pageNum) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        int idCount = labelId.length;
        for (int i = 0; i < idCount; i++)
            hashSet.add(Integer.parseInt(labelId[i]));
        ArrayList<Integer> idList = Lists.newArrayList();
        for (Integer labelid : hashSet) {
            idList.add(labelid);
        }
        return iStudyService.getStudyVideosPutonByLabelId(idList,Integer.parseInt(page),Integer.parseInt(pageNum));
    }
    
    /**
     * 记录视频学习记录
     * @param userId
     * @param videoId
     * @param schedule
     */
    @RequestMapping(value = "video_record.do", method = RequestMethod.GET)
    public void videoRecord(String videoId,String schedule) {
        String userId = this.getUserid();
        int vid = Integer.parseInt(videoId);
        float s = Float.parseFloat(schedule);
        StudyVideoRecord record = new StudyVideoRecord(vid, userId, s, null);
        if(studyVideoRecordMapper.update(record) == 0)
            studyVideoRecordMapper.insert(record);
    }
    
    /**
     * 获取用户必学的视频资料
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_videos_must.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyVideoMust(HttpSession session,String page,String pageNum) {
        String userId = this.getUserid();
        return iStudyService.getStudyVideoMust(userId,Integer.parseInt(page),Integer.parseInt(pageNum));
    }
    
    /**
     * 根据标签id获取必学视频学习资料(上架)
     * 
     * @param labelId
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_videos_must_by_label_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyVideosMustByLabelId(@RequestParam(value = "label_id") String[] labelId,
            HttpSession session,String page,String pageNum) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        int idCount = labelId.length;
        for (int i = 0; i < idCount; i++)
            hashSet.add(Integer.parseInt(labelId[i]));
        ArrayList<Integer> idList = Lists.newArrayList();
        for (Integer labelid : hashSet) {
            idList.add(labelid);
        }
        String userId = this.getUserid();
        return iStudyService.getStudyVideosMustPutonByLabelId(userId, idList, Integer.parseInt(page), Integer.parseInt(pageNum));
    }
    
    /**
     * 根据视频id获取视频学习资料详情
     * @param videoId
     * @return
     */
    @RequestMapping(value = "get_study_video_details.do")
    @ResponseBody
    public ServerResponse getStudyVideoDetails(@RequestParam(value = "video_id", required = true) String videoId) {
        return iStudyService.getStudyVideoDetails(Integer.parseInt(videoId));
    }
    
    /**
     * 根据documentId删除文档
     * @author eachen
     * @param documentId
     * @return
     */
    @RequestMapping(value="delete_study_document_by_documentId")
    @ResponseBody
    public ServerResponse deleteStudyDocument(@RequestParam(value = "documentId") Integer[] documentId) {
    	return iStudyService.deleteStudyDocument(documentId);
    }
    
    /**
     * 根据videoId删除文档
     * @author eachen
     * @param videoId
     * @return
     */
    @RequestMapping(value="delete_study_video_by_videoId")
    @ResponseBody
    public ServerResponse deleteStudyVideo(@RequestParam(value = "videoId") Integer[] videoId) {
    	return iStudyService.deleteStudyVideo(videoId);
    }
    
    /* GvG */
    /**
     * 在用户查看视频时，该接口可查询返回该视频是否已学，根据此显示点击已学按钮
     * @param videoId
     * @return
     */
    @RequestMapping(value = "get_study_video_already_state/{videoId}", method = RequestMethod.GET)
    @ResponseBody
    public ServerResponse getStudyVideoMustState(@PathVariable(value = "videoId") Integer videoId) {
        String userId = this.getUserid();
        if(userId == null)
            return ServerResponse.createByErrorMessage("无法获取当前登录信息");
        if(videoId == null)
            return ServerResponse.createByErrorMessage("视频ID错误");
        return iStudyService.getStudyVideoMustAlreadyState(userId,videoId);
    }
    
    /**
     * 用户点击已学，将用户该视频学习状态改为已学
     * @param videoId
     * @return
     */
    @RequestMapping(value = "set_study_video_already/{videoId}", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setStudyVideoMustState(@PathVariable(value = "videoId") Integer videoId) {
        String userId = this.getUserid();
        if(userId == null)
            return ServerResponse.createByErrorMessage("无法获取当前登录信息");
        if(videoId == null)
            return ServerResponse.createByErrorMessage("视频ID错误");
        return iStudyService.setStudyVideoMustState(userId,videoId);
        
    }
    /* GvG */
}
