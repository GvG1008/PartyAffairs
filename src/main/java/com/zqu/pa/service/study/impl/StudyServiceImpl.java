package com.zqu.pa.service.study.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.google.common.collect.Lists;
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
import com.zqu.pa.service.study.IStudyService;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.vo.StudyDocumentVO1;

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
    public ServerResponse getStudyDocumentsPuton() {
        List<StudyDocument> sdl = studyDocumentMapper.selectPutOn();
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd HH:mm:ss", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes);
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
            String updateTime = DateToString.getDateString("yyyy-MM-dd HH:mm:ss", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes);
            list.add(sdvo1);
        }
        return ServerResponse.createBySuccess(list);
    }

    @Override
    @Transactional
    public ServerResponse getStudyDocumentsPutonByLabelId(List<Integer> idList) {
        List<StudyDocument> sdl = studyDocumentMapper.selectPutonByLabelId(idList);
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd HH:mm:ss", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes);
            list.add(sdvo1);
        }
        return ServerResponse.createBySuccess(list);
    }

    @Override
    @Transactional
    public ServerResponse getStudyDocumentMust(String userId) {
        List<Integer> documentIdList = studyDocumentMustMapper.selectDocumentIdByUserId(userId);
        List<StudyDocument> sdl = Lists.newArrayList();
        int size1 = documentIdList.size();
        for (int i = 0; i < size1; i++) {
            int documentId = documentIdList.get(i);
            StudyDocument sd = studyDocumentMapper.selectByPrimaryKey(documentId);
            sdl.add(sd);
        }
        int size = sdl.size();
        List<StudyDocumentVO1> list = Lists.newArrayList();
        for (int i = 0; i < size; i++) {
            StudyDocument sd = sdl.get(i);
            String updateTime = DateToString.getDateString("yyyy-MM-dd HH:mm:ss", sd.getUpdatetime());
            String uploadUser = studyDocumentMapper.getUserNameByUserId(sd.getUserId());
            int downloadTimes = studyDocumentStatisticsMapper.selectTimeSumByDocumentId(sd.getDocumentId());
            StudyDocumentVO1 sdvo1 = new StudyDocumentVO1(sd.getDocumentId(), sd.getDocumentTitle(), sd.getDocumentIntroduction(), sd.getCoverImg(), sd.getFilePath(), updateTime, uploadUser, downloadTimes);
            list.add(sdvo1);
        }
        return ServerResponse.createBySuccess(list);
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

}
