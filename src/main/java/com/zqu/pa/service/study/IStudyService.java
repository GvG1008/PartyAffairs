package com.zqu.pa.service.study;

import java.util.List;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.study.StudyDocument;
import com.zqu.pa.entity.study.StudyDocumentLabel;
import com.zqu.pa.entity.study.StudyDocumentMust;
import com.zqu.pa.entity.study.StudyLabel;
import com.zqu.pa.entity.study.StudyVideo;
import com.zqu.pa.entity.study.StudyVideoLabel;
import com.zqu.pa.entity.study.StudyVideoMust;

public interface IStudyService {
    public ServerResponse createLabel(String labelName);
    public ServerResponse getLabels();
    public ServerResponse changeLabel(StudyLabel studyLabel);
    
    public ServerResponse uploadStudyDocument(StudyDocument sd,List<StudyDocumentLabel> sdls,List<StudyDocumentMust> sdms);
    public ServerResponse getStudyDocumentsPuton(int page,int pageNum);
    public ServerResponse getStudyDocumentsPutoff();
    public ServerResponse getStudyDocumentsByLabelId(List<Integer> idList);
    public ServerResponse getStudyDocumentsPutonByLabelId(List<Integer> idList,int page,int pageNum);
    public ServerResponse getStudyDocumentsMustPutonByLabelId(String userId,List<Integer> idList,int page,int pageNum);
    public ServerResponse getStudyDocumentMust(String userId,int page,int pageNum);
    public ServerResponse statisticsDownload(String userId,String url);
    public ServerResponse getStudyDcumentDetails(int documentId);
    
    
    public ServerResponse uploadStudyVideo(StudyVideo sv, List<StudyVideoLabel> svls, List<StudyVideoMust> svms);
    public ServerResponse getStudyVideosPuton(int page,int pageNum);
    public ServerResponse getStudyVideosPutonByLabelId(List<Integer> idList,int page,int pageNum);
    public ServerResponse getStudyVideosMustPutonByLabelId(String userId,List<Integer> idList,int page,int pageNum);
    public ServerResponse getStudyVideoMust(String userId,int page,int pageNum);
    public ServerResponse getStudyVideoDetails(int videoId);
    
    public ServerResponse deleteStudyDocument(Integer[] documentId);
    public ServerResponse deleteStudyVideo(Integer[] videoId);
}
