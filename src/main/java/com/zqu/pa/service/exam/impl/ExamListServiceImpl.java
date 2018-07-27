package com.zqu.pa.service.exam.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.exam.ExamInfoMapper;
import com.zqu.pa.dao.exam.ExamRoleMapper;
import com.zqu.pa.dao.exam.ExamScoreMapper;
import com.zqu.pa.entity.exam.ExamInfo;
import com.zqu.pa.entity.exam.ExamInfoExample;
import com.zqu.pa.entity.exam.ExamRoleExample;
import com.zqu.pa.entity.exam.ExamRoleKey;
import com.zqu.pa.entity.exam.ExamScore;
import com.zqu.pa.entity.exam.ExamScoreExample;
import com.zqu.pa.entity.exam.ExamScoreKey;
import com.zqu.pa.service.exam.ExamListService;
import com.zqu.pa.utils.DateUtil;
import com.zqu.pa.vo.exam.ResponseExamList;
import com.zqu.pa.vo.exam.ResponseNowExamList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class ExamListServiceImpl implements ExamListService {

    @Autowired
    private ExamInfoMapper examInfoMapper;
    
    /*@Autowired
    private ExamInfo examInfo;*/
    
    @Autowired
    private ExamRoleMapper examRoleMapper;    
    
    @Autowired
    private ExamScoreMapper examScoreMapper;
    
    @Override
    public List<ResponseExamList> getFinishExamList() {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        String userId = basicInfo.getUserId();
        int branchId = basicInfo.getBranchId();
        int roleId = basicInfo.getRoleId();
        List<Integer> listExamId = listExamRole(roleId);
        if (listExamId == null || listExamId.isEmpty())
            return null;
        
        //查出对应考试信息
        ExamInfoExample example = new ExamInfoExample();
        //已经结束的考试
        int finish = -1;
        example.createCriteria().andExamIdIn(listExamId).andBranchIdEqualTo(branchId).andFinishEqualTo(finish);       
        List<ExamInfo> listExamInfo = examInfoMapper.selectByExample(example);
        if (listExamInfo == null || listExamInfo.isEmpty())
            return null;
        List<ResponseExamList> listResponseExamList = new ArrayList<ResponseExamList>();
        
        for (int i = 0; i < listExamInfo.size(); i++) {
            ResponseExamList rel = new ResponseExamList();
            ExamInfo e = listExamInfo.get(i);
            rel.setExamId(e.getExamId());
            rel.setExamTitle(e.getExamTitle());
            Integer pass = isPass(e.getExamId(), userId, e.getPassScore());
            rel.setPass(pass);
            rel.setScore(getScore(e.getExamId(), userId));
            rel.setStartTime(DateUtil.formatTime(e.getStartTime()));
            rel.setEndTime(DateUtil.formatTime(e.getEndTime()));
            listResponseExamList.add(rel);         
        }       
        return listResponseExamList;
    }

    @Override
    public void setExamFinish() {

        //当前时间大于考试结束时间，将finish字段置为-1
        Long time = new Date().getTime();
        ExamInfoExample example = new ExamInfoExample();
        example.createCriteria().andEndTimeLessThanOrEqualTo(time);
        ExamInfo examInfo = new ExamInfo();
        examInfo.setFinish(-1);
        examInfoMapper.updateByExampleSelective(examInfo, example);
        
        //当前时间处于考试时间段内，考试正在进行将finish字段置为1
        ExamInfoExample example1 = new ExamInfoExample();
        example1.createCriteria().andStartTimeLessThanOrEqualTo(time)
            .andEndTimeGreaterThanOrEqualTo(time);
        ExamInfo examInfo1 = new ExamInfo();
        examInfo1.setFinish(1);
        examInfoMapper.updateByExampleSelective(examInfo1, example1);
        //finish默认为0，当前时间小于考试开始时间，考试未开始
    }

    @Override
    public List<Integer> listExamRole(int roleId) {
        
        ExamRoleExample example = new ExamRoleExample();
        example.createCriteria().andRoleIdEqualTo(roleId);       
        List<ExamRoleKey> temp = examRoleMapper.selectByExample(example);
        if (temp == null || temp.isEmpty())
            return null;
        List<Integer> listExamId = new ArrayList<Integer>();
        for (int i = 0; i < temp.size(); i++)
            listExamId.add(temp.get(i).getExamId());
        return listExamId;
    }

    @Override
    public Integer isPass(Integer examId, String userId, Integer passScore) {
        
        ExamScoreKey examScoreKey = new ExamScoreKey();
        examScoreKey.setExamId(examId);
        examScoreKey.setUserId(userId);
        ExamScore examScore = examScoreMapper.selectByPrimaryKey(examScoreKey);
        //返回-1表示没参加考试，1表示及格，0表示不及格
        if (examScore == null) {
            return -1;
        }
        if (examScore.getScore() >= passScore)
            return 1;
        else
            return 0;
    }

    @Override
    public Integer getScore(Integer examId, String userId) {
        
        ExamScoreKey examScoreKey = new ExamScoreKey();
        examScoreKey.setExamId(examId);
        examScoreKey.setUserId(userId);
        ExamScore examScore = examScoreMapper.selectByPrimaryKey(examScoreKey);
        if (examScore == null)
            return null;
        return examScore.getScore();
    }

    @Override
    public List<ResponseNowExamList> getUnFinishExamList() {
        
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        String userId = basicInfo.getUserId();
        int branchId = basicInfo.getBranchId();
        int roleId = basicInfo.getRoleId();
        List<Integer> listExamId = listExamRole(roleId);
        if (listExamId == null || listExamId.isEmpty())
            return null;
        //查出对应考试信息
        ExamInfoExample example = new ExamInfoExample();
        List<Integer> finish = new ArrayList<Integer>();
        //还未开始的考试
        finish.add(0);
        //正在进行的考试
        finish.add(1);
        example.createCriteria().andExamIdIn(listExamId).andBranchIdEqualTo(branchId).andFinishIn(finish);       
        List<ExamInfo> listExamInfo = examInfoMapper.selectByExample(example);
        if (listExamInfo == null || listExamInfo.isEmpty())
            return null;
        List<ResponseNowExamList> responseNowExamList = new ArrayList<ResponseNowExamList>();
        for (int i = 0; i < listExamInfo.size(); i++) {
            ExamInfo e = listExamInfo.get(i);
            ResponseNowExamList rnel = getResponseNowExamList(e, userId);
            responseNowExamList.add(rnel);            
        }
        return responseNowExamList;
    }

    @Override
    public ResponseNowExamList getResponseNowExamList(ExamInfo e, String userId) {
        
        ResponseNowExamList rnel = new ResponseNowExamList();
        rnel.setExamId(e.getExamId());
        rnel.setExamTitle(e.getExamTitle());
        rnel.setExamPeriod(e.getExamPeriod());
        rnel.setPassScore(e.getPassScore());
        rnel.setSingleQuantity(e.getSingleQuantity());
        rnel.setMultipleQuantity(e.getMultipleQuantity());
        rnel.setStatus(e.getFinish());
        rnel.setStartTime(DateUtil.formatTime(e.getStartTime()));
        rnel.setEndTime(DateUtil.formatTime(e.getEndTime()));
        rnel.setScore(getScore(e.getExamId(), userId));
        rnel.setPass(isPass(e.getExamId(), userId, e.getPassScore()));
        return rnel;
    }

    @Override
    public ExamInfo getExamInfo(Integer examId) {
        
        ExamInfo e = examInfoMapper.selectByPrimaryKey(examId);
        return e;
    }
    
    

}
