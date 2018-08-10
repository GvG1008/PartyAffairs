package com.zqu.pa.service.partyactivity.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.dao.partyactivity.PartyActivityManageMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityRoleMapper;
import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.service.partyactivity.ActivityManageService;
import com.zqu.pa.vo.partyactivity.ActivityManageMenu;

@Service
public class ActivityManageServiceImpl implements ActivityManageService {

    @Autowired
    PartyActivityManageMapper partyActivityManageDao;
    
    @Autowired
    PartyActivityMapper partyActivityDao;
    
    @Autowired
    PartyActivityRoleMapper partyActivityRoleDao;
    
    @Override
    public List<ActivityManageMenu> getActivityMenuList(int branchId) {
        
        List<ActivityManageMenu> info = partyActivityManageDao.getActivityList(branchId);
        if(info==null||info.size()==0)
            return null;
        
        Long nowTime = new Date().getTime();
        for(int i=0 ; i<info.size() ; i++) {
            //获取单条信息处理
            ActivityManageMenu a = info.get(i);

            if(a.getRegistrationStart().longValue()>nowTime.longValue())
                a.setActivityState("报名未开始");
            else if(a.getRegistrationEnd().longValue()>nowTime.longValue())
                a.setActivityState("报名进行中");
            else
                a.setActivityState("报名已结束");
            
            if(a.getActivityStart().longValue()>nowTime.longValue())
                a.setActivityState(a.getActivityState()+",活动未开始");
            else if(a.getActivityEnd().longValue()>nowTime.longValue())
                a.setActivityState(a.getActivityState()+",活动进行中");
            else
                a.setActivityState(a.getActivityState()+",活动已结束");
        }
        
        return info;
    }

    @Transactional
    @Override
    public String insertActivity(PartyActivity pA, Integer[] roleIds) {
        
        //插入添加新活动
        partyActivityDao.insertSelective(pA);
        if(pA.getActivityId()==null){
            throw new RuntimeException();
        }
        if(partyActivityManageDao.insertAcRoleBatch(pA.getActivityId(), roleIds)==roleIds.length)
            return "创建活动成功";
        else 
            throw new RuntimeException();
    }

}
