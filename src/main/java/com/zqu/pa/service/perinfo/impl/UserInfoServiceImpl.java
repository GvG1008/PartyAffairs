package com.zqu.pa.service.perinfo.impl;

import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.perinfo.UserListMapper;
import com.zqu.pa.dao.perinfo.UserPartyInfoMapper;
import com.zqu.pa.dao.perinfo.UserPersonInfoMapper;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPartyInfoExample;
import com.zqu.pa.entity.perinfo.UserPartyInfoExample.Criteria;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.vo.perinfo.UserListInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    @Autowired
    UserPersonInfoMapper userPersonInfoDao;

    @Autowired
    UserPartyInfoMapper userPartyInfoDao;
    
    @Autowired
    UserListMapper userListDao;

    @Override
    public UserPersonInfo getUserPersonInfo(String userId) {
        
        UserPersonInfo info = new UserPersonInfo();
        info = userPersonInfoDao.selectByPrimaryKey(userId);
        
        return info;
    }

    @Override
    public UserPartyInfo getUserPartyInfo(String userId) {
        
        UserPartyInfo info = new UserPartyInfo();
        info = userPartyInfoDao.selectByPrimaryKey(userId);
        
        return info;
    }

    @Override
    public int updateByUserPerson(UserPersonInfo info) {
        
        return userPersonInfoDao.updateByPrimaryKeySelective(info);
    }

    @Override
    public int updateByUserParty(UserPartyInfo info) {
        
        return userPartyInfoDao.updateByPrimaryKeySelective(info);
    }

    @Override
    public UserListInfo getUserList(int branchId, int page, int num) {
        UserListInfo info = new UserListInfo();

        //总记录数
        int totalInfoNum = userListDao.countByBranch(branchId, 1);
        info.setTotalInfoNum(totalInfoNum);
        //总页数
        int totalPageNum = (int)(totalInfoNum+num-1)/num;
        info.setTotalPageNum(totalPageNum);
        
        if(totalPageNum<page)
            page = totalPageNum;
        else if(page<=0)
            page = 1;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录
        int index = (page-1)*num;
        info.setList(userListDao.getUserBasicList(branchId, index, num));
        
        return info;
    }

}
