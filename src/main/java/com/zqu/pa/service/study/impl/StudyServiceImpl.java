package com.zqu.pa.service.study.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.study.StudyDocumentLabelMapper;
import com.zqu.pa.dao.study.StudyDocumentMapper;
import com.zqu.pa.dao.study.StudyDocumentMustMapper;
import com.zqu.pa.dao.study.StudyDocumentStatisticsMapper;
import com.zqu.pa.dao.study.StudyLabelMapper;
import com.zqu.pa.dao.study.StudyVideoLabelMapper;
import com.zqu.pa.dao.study.StudyVideoMapper;
import com.zqu.pa.dao.study.StudyVideoMustMapper;
import com.zqu.pa.entity.study.StudyDocument;
import com.zqu.pa.entity.study.StudyDocumentLabel;
import com.zqu.pa.entity.study.StudyDocumentMust;
import com.zqu.pa.entity.study.StudyDocumentStatistics;
import com.zqu.pa.entity.study.StudyLabel;
import com.zqu.pa.entity.study.StudyVideo;
import com.zqu.pa.entity.study.StudyVideoLabel;
import com.zqu.pa.entity.study.StudyVideoMust;
import com.zqu.pa.entity.study.StudyVideoRecord;
import com.zqu.pa.service.study.IStudyService;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.vo.study.StudyDocumentVO1;
import com.zqu.pa.vo.study.StudyVideoVO1;
import com.zqu.pa.vo.study.StudyVideoVO2;

@Service("iStudyService")
public class StudyServiceImpl implements IStudyService {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private StudyLabelMapper studyLabelMapper;
    @Autowired
    private StudyDocumentMapper studyDocumentMapper;
    @Autowired
    private StudyDocumentLabelMapper studyDocumentLabelMapper;
    @Autowired
    private StudyDocumentMustMapper studyDocumentMustMapper;
    @Autowired
    private StudyDocumentStatisticsMapper studyDocumentStatisticsMapper;
    @Autowired
    private StudyVideoMapper studyVideoMapper;
    @Autowired
    private StudyVideoLabelMapper studyVideoLabelMapper;
    @Autowired
    private StudyVideoMustMapper studyVideoMustMapper;
    
    @Override
    public ServerResponse createLabel(String labelName) {
        StudyLabel studyLabel = new StudyLabel(null, labelName, null, null);
        logger.info("调用createLabel(String labelName)新建一个标签  " + studyLabel.toString());
        int result = studyLabelMapper.insert(studyLabel);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("新建标签成功");
        else
            return ServerResponse.createByErrorMessage("新建标签失败");
    }

    @Override
    public ServerResponse getLabels() {
        logger.info("调用getLabels()获取标签");
        List labels = studyLabelMapper.selectAll();
        return ServerResponse.createBySuccess(labels);
    }

    @Override
    public ServerResponse changeLabel(StudyLabel studyLabel) {
        logger.info("调用changeLabel(String labelId, String labelName)修改标签");
        int result = studyLabelMapper.updateByPrimaryKey(studyLabel);
        if (result > 0)
            return ServerResponse.createBySuccessMessage("修改标签成功");
        else
            return ServerResponse.createByErrorMessage("修改标签失败");
    }

    @Override
    @Transactional
    public ServerResponse uploadStudyDocument(StudyDocument sd, List<StudyDocumentLabel> sdls,
            List<StudyDocumentMust> sdms) {
        // 添加文档记录
        boolean r1 = studyDocumentMapper.insert(sd) > 0;
        // 获取文档id
        int documentId = studyDocumentMapper.selectDocumentIdByFilePath(sd.getFilePath());
        // 添加文档标签记录
        boolean r2 = true;
        for (StudyDocumentLabel sdl : sdls) {
            sdl.setDocumentId(documentId);
            if (studyDocumentLabelMapper.insert(sdl) < 0)
                r2 = false;
        }
        // 添加文档必学对象
        boolean r3 = true;
        for (StudyDocumentMust sdm : sdms) {
            sdm.setDocumentId(documentId);
            if (studyDocumentMustMapper.insert(sdm) < 0)
                r3 = false;
        }
        if (r1 && r2 && r3) {
            return ServerResponse.createBySuccess("上传文档资料成功");
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动开启事务回滚
        }
        return ServerResponse.createByErrorMessage("上传文档资料失败");
    }

    @Override
    @Transactional
    public ServerResponse getStudyDocumentsPuton(int page,int pageNum) {
        int totalNum = studyDocumentMapper.selectCountPutOn();
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        List<StudyDocument> sdl = studyDocumentMapper.selectPutOn(index,pageNum);
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes,sls);
            list.add(sdvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    @Transactional
    public ServerResponse getStudyDocumentsPutoff() {
        List<StudyDocument> sdl = studyDocumentMapper.selectPutOff();
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes,sls);
            list.add(sdvo1);
        }
        return ServerResponse.createBySuccess(list);
    }
    
    @Override
    @Transactional
    public ServerResponse getStudyDocumentsByLabelId(List<Integer> idList) {
        List<Integer> documentIds = studyDocumentLabelMapper.selectDocumentIdByLabels(idList);
        List<StudyDocument> sdl = Lists.newArrayList();
        int size1 = documentIds.size();
        for (int i = 0; i < size1; i++)
            sdl.add(studyDocumentMapper.selectByPrimaryKey(documentIds.get(i)));
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes,sls);
            list.add(sdvo1);
        }
        return ServerResponse.createBySuccess(list);
    }

    @Override
    @Transactional
    public ServerResponse getStudyDocumentsPutonByLabelId(List<Integer> idList,int page,int pageNum) {
        int totalNum = studyDocumentMapper.selectCountPutonByLabelId(idList);
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        HashMap paramMap = Maps.newHashMap();
        paramMap.put("index", index);
        paramMap.put("num", pageNum);
        paramMap.put("idList", idList);
        List<StudyDocument> sdl = studyDocumentMapper.selectPutonByLabelId(paramMap);
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes,sls);
            list.add(sdvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }
    
    @Override
    @Transactional
    public ServerResponse getStudyDocumentsMustPutonByLabelId(String userId,List<Integer> idList,int page,int pageNum) {
        HashMap paramMap = Maps.newHashMap();
        paramMap.put("idList", idList);
        paramMap.put("userId", userId);
        
        int totalNum = studyDocumentMapper.selectCountMustPutonByLabelId(paramMap);
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        paramMap.put("index", index);
        paramMap.put("num", pageNum);

        List<StudyDocument> sdl = studyDocumentMapper.selectMustPutonByLabelId(paramMap);
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes,sls);
            list.add(sdvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    @Transactional
    public ServerResponse getStudyDocumentMust(String userId,int page,int pageNum) {
        int totalNum = studyDocumentMapper.selectCountMustPutonByUserId(userId);
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        List<StudyDocument> sdl = studyDocumentMapper.selectMustPutonByUserId(index,pageNum,userId);
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes,sls);
            list.add(sdvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    public ServerResponse statisticsDownload(String userId, String url) {
        int documentId = studyDocumentMapper.selectDocumentIdByFilePath(url);
        StudyDocumentStatistics studyDocumentStatistics = new StudyDocumentStatistics();
        studyDocumentStatistics.setDocumentId(documentId);
        studyDocumentStatistics.setUserId(userId);
        StudyDocumentStatistics studyDocumentStatistics1 = studyDocumentStatisticsMapper
                .selectByDocumentIdAndUserId(studyDocumentStatistics);
        if (studyDocumentStatistics1 == null) {
            studyDocumentStatistics.setTimes(1);
            studyDocumentStatisticsMapper.insert(studyDocumentStatistics);
        } else {
            int times = studyDocumentStatistics1.getTimes() + 1;
            studyDocumentStatistics.setTimes(times);
            studyDocumentStatisticsMapper.updateByDocumentIdAndUserId(studyDocumentStatistics);
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    @Transactional
    public ServerResponse uploadStudyVideo(StudyVideo sv, List<StudyVideoLabel> svls,
            List<StudyVideoMust> svms) {
        // 添加视频记录
        boolean r1 = studyVideoMapper.insert(sv) > 0;
        // 获取视频id
        int videoId = studyVideoMapper.selectVideoIdByVideoPath(sv.getVideoPath());
        // 添加视频标签记录
        boolean r2 = true;
        for (StudyVideoLabel svl : svls) {
            svl.setVideoId(videoId);
            if (studyVideoLabelMapper.insert(svl) < 0)
                r2 = false;
        }
        // 添加视频必学对象
        boolean r3 = true;
        for (StudyVideoMust svm : svms) {
            svm.setVideoId(videoId);
            if (studyVideoMustMapper.insert(svm) < 0)
                r3 = false;
        }
        if (r1 && r2 && r3) {
            return ServerResponse.createBySuccess("上传视频资料成功");
        } else {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); // 手动开启事务回滚
        }
        return ServerResponse.createByErrorMessage("上传视频资料失败");
    }
    
    @Override
    @Transactional
    public ServerResponse getStudyVideosPuton(int page,int pageNum) {
        int totalNum = studyVideoMapper.selectCountPuton();
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        
        List<StudyVideo> svl = studyVideoMapper.selectPuton(index,pageNum);
        int size = svl.size();
        List<StudyVideoVO1> list = Lists.newArrayList();
        for(int i=0;i<size;i++) {
            StudyVideo sv = svl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sv.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sv.getUserId());
            List<StudyLabel> sls = studyLabelMapper.selectByVideoId(sv.getVideoId());
            StudyVideoVO1 svvo1 = new StudyVideoVO1(sv.getVideoId(), sv.getVideoTitle(), sv.getVideoIntroduction(), sv.getCoverImg(), sv.getVideoPath(), uploadUser, updateTime, sls);
            list.add(svvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }
    

    @Override
    @Transactional
    public ServerResponse getStudyVideosPutonByLabelId(List<Integer> idList,int page,int pageNum) {
        int totalNum = studyVideoMapper.selectCountPutonByLabelId(idList);
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        HashMap paramMap = Maps.newHashMap();
        paramMap.put("index", index);
        paramMap.put("num",pageNum);
        paramMap.put("idList", idList);
        List<StudyVideo> svl = studyVideoMapper.selectPutonByLabelId(paramMap);
        int size = svl.size();
        List<StudyVideoVO1> list = Lists.newArrayList();
        for(int i=0;i<size;i++) {
            StudyVideo sv = svl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sv.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sv.getUserId());
            List<StudyLabel> sls = studyLabelMapper.selectByVideoId(sv.getVideoId());
            StudyVideoVO1 svvo1 = new StudyVideoVO1(sv.getVideoId(), sv.getVideoTitle(), sv.getVideoIntroduction(), sv.getCoverImg(), sv.getVideoPath(), uploadUser, updateTime, sls);
            list.add(svvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }
    
    @Override
    @Transactional
    public ServerResponse getStudyVideoMust(String userId,int page,int pageNum) {
        int totalNum = studyVideoMapper.selectCountMustPutonByUserId(userId);
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        List<StudyVideo> svl = studyVideoMapper.selectMustPutonByUserId(index,pageNum,userId);
        int size = svl.size();
        List<StudyVideoVO2> list = Lists.newArrayList();
        for(int i=0;i<size;i++) {
            StudyVideo sv = svl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sv.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sv.getUserId());
            List<StudyLabel> sls = studyLabelMapper.selectByVideoId(sv.getVideoId());
            StudyVideoRecord svr = new StudyVideoRecord(sv.getVideoId(), userId, null, null);
            float schedule = studyVideoMapper.selectScheduleByVideoIdAndUserId(svr);
            StudyVideoVO2 svvo2 = new StudyVideoVO2(sv.getVideoId(), sv.getVideoTitle(), sv.getVideoIntroduction(), sv.getCoverImg(), sv.getVideoPath(), uploadUser, updateTime, sls,schedule);
            list.add(svvo2);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }
    
    @Override
    @Transactional
    public ServerResponse getStudyVideosMustPutonByLabelId(String userId,List<Integer> idList,int page,int pageNum) {
        HashMap paramMap = Maps.newHashMap();
        paramMap.put("idList", idList);
        paramMap.put("userId", userId);
        int totalNum = studyVideoMapper.selectCountPutonMustByLabelId(paramMap);
        int totalPage = (int)(totalNum+pageNum-1)/pageNum;
        if(totalPage<page)
            page = totalPage;
        int index = (page-1)*pageNum;
        Map map = Maps.newHashMap();
        map.put("totalPage", totalPage);
        map.put("page", page);
        map.put("pageNum", pageNum);
        paramMap.put("index", index);
        paramMap.put("num",pageNum);
        List<StudyVideo> svl = studyVideoMapper.selectPutonByLabelId(paramMap);
        int size = svl.size();
        List<StudyVideoVO1> list = Lists.newArrayList();
        for(int i=0;i<size;i++) {
            StudyVideo sv = svl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd", sv.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sv.getUserId());
            List<StudyLabel> sls = studyLabelMapper.selectByVideoId(sv.getVideoId());
            StudyVideoVO1 svvo1 = new StudyVideoVO1(sv.getVideoId(), sv.getVideoTitle(), sv.getVideoIntroduction(), sv.getCoverImg(), sv.getVideoPath(), uploadUser, updateTime, sls);
            list.add(svvo1);
        }
        map.put("list", list);
        return ServerResponse.createBySuccess(map);
    }

    @Override
    @Transactional
    public ServerResponse getStudyDcumentDetails(int documentId) {
        StudyDocument sd = studyDocumentMapper.selectByPrimaryKey(documentId);
        String updateTime = DateToString.getDateString("yyyy-MM-dd", sd.getUpdatetime());
        String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
        int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
        List<StudyLabel> sls = studyLabelMapper.selectByDocumentId(sd.getDocumentId());
        StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(documentId, sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes, sls);
        return ServerResponse.createBySuccess(sdvo1);
    }
    
    @Override
    @Transactional
    public ServerResponse getStudyVideoDetails(int videoId) {
        StudyVideo sv = studyVideoMapper.selectByPrimaryKey(videoId);
        String updateTime = DateToString.getDateString("yyyy-MM-dd", sv.getUpdatetime());
        String uploadUser = studyDocumentMapper.getUserNameByUserId(sv.getUserId());
        List<StudyLabel> sls = studyLabelMapper.selectByVideoId(sv.getVideoId());
        StudyVideoVO1 svvo1 = new StudyVideoVO1(sv.getVideoId(), sv.getVideoTitle(), sv.getVideoIntroduction(), sv.getCoverImg(), sv.getVideoPath(), uploadUser, updateTime, sls);
        return ServerResponse.createBySuccess(svvo1);
    }
    
    public ServerResponse deleteStudyDocument(Integer[] documentId) {
    	int j=0;
    	for(int i = 0; i < documentId.length; i++) {
    		int k = studyDocumentMapper.deleteByPrimaryKey(documentId[i]);
    		j = j+k;
    	}
    	if(j == documentId.length)
    		return ServerResponse.createBySuccessMessage("所选文档删除成功");
    	return ServerResponse.createBySuccessMessage("删除文档成功");
    }
    
    public ServerResponse deleteStudyVideo(Integer[] videoId) {
    	int j=0;
    	for(int i = 0; i < videoId.length; i++) {
    		int k = studyVideoMapper.deleteByPrimaryKey(videoId[i]);
    		j = j+k;
    	}
    	if(j == videoId.length)
    		return ServerResponse.createBySuccessMessage("所选视频删除成功");
    	return ServerResponse.createBySuccessMessage("删除视频成功");
    }
    
}
