package com.zqu.pa.service.perinfo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.perinfo.UserPartyInfoMapper;
import com.zqu.pa.dao.perinfo.UserPersonInfoMapper;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.vo.perinfo.UserListInfo;

@Service
public class UserInfoServiceImpl implements UserInfoService {
    
    @Autowired
    UserPersonInfoMapper userPersonInfoDao;

    @Autowired
    UserPartyInfoMapper userPartyInfoDao;

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
        
        
        return null;
    }

}
