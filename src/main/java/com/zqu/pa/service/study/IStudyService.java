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
    public ServerResponse getStudyDocumentsPuton();
    public ServerResponse getStudyDocumentsPutoff();
    public ServerResponse getStudyDocumentsByLabelId(List<Integer> idList);
    public ServerResponse getStudyDocumentsPutonByLabelId(List<Integer> idList);
    public ServerResponse getStudyDocumentMust(String userId);
    public ServerResponse statisticsDownload(String userId,String url);
    public ServerResponse uploadStudyVideo(StudyVideo sv, List<StudyVideoLabel> svls, List<StudyVideoMust> svms);
    
}
