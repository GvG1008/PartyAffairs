package com.zqu.pa.controller.study;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.zqu.pa.common.Const;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.study.StudyDocument;
import com.zqu.pa.entity.study.StudyDocumentLabel;
import com.zqu.pa.entity.study.StudyDocumentMust;
import com.zqu.pa.entity.study.StudyLabel;
import com.zqu.pa.entity.study.StudyVideo;
import com.zqu.pa.entity.study.StudyVideoLabel;
import com.zqu.pa.entity.study.StudyVideoMust;
import com.zqu.pa.service.study.IStudyService;
import com.zqu.pa.utils.FTPSSMLoad;
import com.zqu.pa.utils.StringExtend;

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

    private String getUserid(HttpSession session) {
        return (String)session.getAttribute("userId");
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
            @RequestParam(value = "label_id") String[] labelId, @RequestParam(value = "user_id") String[] userId) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        // TODO 校验参数的格式
        String userID = this.getUserid(session);
        System.out.println(file.isEmpty() + " -- " + img.isEmpty());
        Map fileMap = FTPSSMLoad.upload(file, request, "/document/");
        Map imgMap = FTPSSMLoad.upload(img, request, "/document/");
        StudyDocument sd = new StudyDocument(null, title, introduction, imgMap.get("http_url").toString(),
                fileMap.get("download_url").toString(), userID, null, null);
        int labelCount = labelId.length;
        int userCount = userId.length;
        ArrayList<StudyDocumentLabel> studyDocumentLabelList = Lists.newArrayList();
        ArrayList<StudyDocumentMust> studyDocumentMustList = Lists.newArrayList();
        for (String lid : labelId) {
            StudyDocumentLabel sdl = new StudyDocumentLabel(null, null, Integer.parseInt(lid));
            studyDocumentLabelList.add(sdl);
        }
        for (String uid : userId) {
            StudyDocumentMust sdm = new StudyDocumentMust();
            sdm.setUserId(uid);
            studyDocumentMustList.add(sdm);
        }
        /*
         * JsonParser parser = new JsonParser(); JsonArray jsonArray =
         * parser.parse(labels).getAsJsonArray(); Gson gson = new Gson();
         * ArrayList<StudyDocumentLabel> studyDocumentLabelList = Lists.newArrayList();
         * for (JsonElement bean : jsonArray) { LabelJsonBean labelJsonBean =
         * gson.fromJson(bean, LabelJsonBean.class);
         * System.out.println(labelJsonBean.getLabelId()); StudyDocumentLabel sdl = new
         * StudyDocumentLabel(null, null, Integer.parseInt(labelJsonBean.getLabelId()));
         * studyDocumentLabelList.add(sdl); }
         */
        System.out.println(sd);
        System.out.println(studyDocumentLabelList);
        return iStudyService.uploadStudyDocument(sd, studyDocumentLabelList,studyDocumentMustList);
    }

    /**
     * 获取全部文档学习资料
     * 
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_documents.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocuments(HttpSession session) {
        return iStudyService.getStudyDocuments();
    }

    /**
     * 根据标签id获取文档学习资料
     * 
     * @param labelId
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_documents_by_label_id.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocumentsByLabelId(@RequestParam(value = "label_id") String[] labelId,
            HttpSession session) {
        HashSet<Integer> hashSet = Sets.newHashSet();
        int idCount = labelId.length;
        for (int i = 0; i < idCount; i++)
            hashSet.add(Integer.parseInt(labelId[i]));
        ArrayList<Integer> idList = Lists.newArrayList();
        for (Integer labelid : hashSet) {
            idList.add(labelid);
        }
        return iStudyService.getStudyDocumentsByLabelId(idList);
    }

    /**
     * 获取用户必学的文档资料
     * @param session
     * @return
     */
    @RequestMapping(value = "get_study_documents_must.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudyDocumentMust(HttpSession session) {
        String userId = this.getUserid(session);
        return iStudyService.getStudyDocumentMust(userId);
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
        String userId = this.getUserid(session);
        System.out.println(userId);
        String downloadURL = Const.DOWN_INTERFACE + "path=" + path + "&filename=" + filename;
        return iStudyService.statisticsDownload(userId, downloadURL);
    }
    
    
    @RequestMapping(value = "upload_study_video.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse uploadStudyVideo(@RequestParam(value = "upload_file") MultipartFile file,
            @RequestParam(value = "upload_img") MultipartFile img, HttpServletRequest request, HttpSession session,
            @RequestParam(value = "video_title") String title,
            @RequestParam(value = "video_introduction") String introduction,
            @RequestParam(value = "label_id") String[] labelId, @RequestParam(value = "user_id") String[] userId) {
        // TODO 判断是否登录，通过登录用户验证身份权限
        // TODO 校验参数的格式
        String userID = this.getUserid(session);
        System.out.println(file.isEmpty() + " -- " + img.isEmpty());
        Map fileMap = FTPSSMLoad.upload(file, request, "/video/");
        Map imgMap = FTPSSMLoad.upload(img, request, "/video/");
        StudyVideo sv = new StudyVideo(null, title, introduction, imgMap.get("http_url").toString(), fileMap.get("http_url").toString(), userID, null, null);
        int labelCount = labelId.length;
        int userCount = userId.length;
        ArrayList<StudyVideoLabel> studyVideoLabelList = Lists.newArrayList();
        ArrayList<StudyVideoMust> studyVideoMustList = Lists.newArrayList();
        for(String lid : labelId) {
            StudyVideoLabel svl = new StudyVideoLabel(null, null, Integer.parseInt(lid));
            studyVideoLabelList.add(svl);
        }
        for(String uid : userId) {
            StudyVideoMust svm = new StudyVideoMust();
            svm.setUserId(uid);
            studyVideoMustList.add(svm);
        }
        return iStudyService.uploadStudyDocument(sd, studyDocumentLabelList,studyDocumentMustList);
    }
}
