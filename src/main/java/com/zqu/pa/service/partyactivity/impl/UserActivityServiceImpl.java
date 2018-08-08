package com.zqu.pa.service.partyactivity.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.partyactivity.PartyActivityListMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityMapper;
import com.zqu.pa.entity.partyactivity.PartyActivityExample;
import com.zqu.pa.entity.partyactivity.PartyActivityExample.Criteria;
import com.zqu.pa.service.partyactivity.UserActivityService;
import com.zqu.pa.vo.partyactivity.PageOfList;

@Service
public class UserActivityServiceImpl implements UserActivityService {

    @Autowired
    PartyActivityMapper partyActivityDao;
    
    @Autowired
    PartyActivityListMapper partyActivityListDao;
    
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

}
