package com.zqu.pa.service.partyactivity.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.partyactivity.PartyActivityListMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityUserMapper;
import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.entity.partyactivity.PartyActivityExample;
import com.zqu.pa.entity.partyactivity.PartyActivityExample.Criteria;
import com.zqu.pa.entity.partyactivity.PartyActivityUser;
import com.zqu.pa.entity.partyactivity.PartyActivityUserKey;
import com.zqu.pa.service.partyactivity.UserActivityService;
import com.zqu.pa.vo.partyactivity.PageOfList;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    PartyActivityMapper partyActivityDao;
    
    @Autowired
    PartyActivityListMapper partyActivityListDao;
    
    @Autowired
    PartyActivityUserMapper partyActivityUserDao;
    
    @Override
    public PageOfList getMenuInfo(int page, int num, int branchId) {
        if(num<=0)
            return null;
        
        PageOfList info = new PageOfList();
        
        PartyActivityExample example = new PartyActivityExample();
        Criteria criteria = example.createCriteria();
        criteria.andIsDeleteNotEqualTo(1);//过滤删除项
        if(branchId!=0)
            criteria.andBranchIdEqualTo(branchId);//条件
        
        //总记录条数
        int totalInfoNum = (int)partyActivityDao.countByExample(example);
        info.setTotalInfoNum(totalInfoNum);
        
        criteria.andRegistrationEndGreaterThan(new Date().getTime());
        //报名进行时活动数
        int totalActiveNum = (int)partyActivityDao.countByExample(example);
        info.setTotalActiveNum(totalActiveNum);
        
        //总页数
        int totalPageNum = (int)(totalInfoNum+num-1)/num;
        info.setTotalPageNum(totalPageNum);
        
        //页数合法化
        if(totalPageNum<page)
            page = totalPageNum;
        if(page<=0)
            page = 1;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录
        int index = (page-1)*num;
        info.setList(partyActivityListDao.getActivityMenuListLimit(index, num, branchId));
        
        return info;
    }

    @Override
    public Map getApplyResult(int activityId) {
        Map info = Maps.newHashMap();
        
        //获取当前session里的当前用户信息
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null) {
            info.put("State", -1);
            info.put("Msg", "无法获取当前session信息");
            return info;
        }
        String userId = basicInfo.getUserId();
        int roleId = basicInfo.getRoleId();
        int branchId = basicInfo.getBranchId();
        
        //验证是否报过名
        int applyState = this.getIsApply(activityId, userId);
        if(applyState==-1) {
            info.put("State", 0);
            info.put("Msg", "已成功报名，报名结果:审核未通过");
            return info;
        }else if(applyState==0) {
            info.put("State", 0);
            info.put("Msg", "已成功报名，请等待审核..");
            return info;
        }else if(applyState==1) {
            info.put("State", 0);
            info.put("Msg", "已成功报名，报名结果:审核已通过，成功参加本次活动!");
            return info;
        }
        
        //验证用户是否拥有报名权限
        PartyActivity activity = partyActivityDao.selectByPrimaryKey(activityId);
        List<Integer> roleList = partyActivityListDao.getActivityRoleList(activityId);
        if(activity==null||roleList==null||roleList.size()==0) {
            info.put("State", -2);
            info.put("Msg", "该活动不存在");
            return info;
        }
        if(activity.getBranchId()!=0&&branchId!=0&&activity.getBranchId()!=branchId) {
            info.put("State", -3);
            info.put("Msg", "与活动所属对应党支部不同，无法报名");
            return info;
        }
        int flag = 0;
        for( Integer role : roleList) { //验证角色身份是否有报名权限
            if(role==roleId) {
                flag = 1;
                break;
            }
        }
        if(flag==0) {
            info.put("State", -3);
            info.put("Msg", "您不在活动人员范围内，无法报名");
            return info;
        }
        
        //验证活动报名截止日期是否到期
        if(activity.getRegistrationEnd()<new Date().getTime()) {
            info.put("State", -4);
            info.put("Msg", "报名已截止");
            return info;
        }
        
        info.put("State", 1);
        info.put("Msg", "可以进行报名");
        return info;
    }

    @Override
    public int getIsApply(int activityId, String userId) {
        PartyActivityUserKey key = new PartyActivityUserKey();
        key.setActivityId(activityId);
        key.setUserId(userId);

        PartyActivityUser userApplyInfo = partyActivityUserDao.selectByPrimaryKey(key);
        if(userApplyInfo==null) //没有报名
            return -2;
        else if(userApplyInfo.getCheckState()==0) //已报名未审核
            return 0;
        else if(userApplyInfo.getCheckState()==1) //报名通过审核
            return 1;
        else if(userApplyInfo.getCheckState()==-1) //报名审核不通过
            return -1;
        return -2;
    }

}
