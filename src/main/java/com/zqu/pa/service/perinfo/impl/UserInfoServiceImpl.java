package com.zqu.pa.service.perinfo.impl;

import org.apache.shiro.SecurityUtils;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.perinfo.UserListMapper;
import com.zqu.pa.dao.perinfo.UserPartyInfoMapper;
import com.zqu.pa.dao.perinfo.UserPersonInfoMapper;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPartyInfoExample;
import com.zqu.pa.entity.perinfo.UserPartyInfoExample.Criteria;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.vo.perinfo.UserListInfo;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

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
    public UserListInfo getUserList(int branchId, int page, int num, int checkState) {
        UserListInfo info = new UserListInfo();

        //从数据库获取总记录数，checkState为是否审核
        int totalInfoNum = userListDao.countByBranch(branchId, checkState);
        info.setTotalInfoNum(totalInfoNum);
        //计算总页数
        int totalPageNum = (int)(totalInfoNum+num-1)/num;
        info.setTotalPageNum(totalPageNum);
        
        //若请求页数超出总页数，则搜索最后一页，<=0则第一页
        if(totalPageNum<page)
            page = totalPageNum;
        else if(page<=0)
            page = 1;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录，checkState为是否审核
        int index = (page-1)*num;
        try {
            info.setList(userListDao.getUserBasicList(branchId, index, num,checkState));
        }catch (Exception e) {
            
            e.printStackTrace();
        }
        
        return info;
    }

    @Override
    public String checkUser(int branchId, String userId) {
        
        //在个人信息表将审核状态置1，审核人ID填入，账号信息表状态置1
        //先获取审核人ID
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null||basicInfo.getUserId()==null)
            return "无法获取当前session信息用户ID";
        String checkId = basicInfo.getUserId();
        

        
        
        return "审核成功!";
    }
}
