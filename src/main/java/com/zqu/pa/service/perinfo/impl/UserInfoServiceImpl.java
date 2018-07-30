package com.zqu.pa.service.perinfo.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.perinfo.UserListMapper;
import com.zqu.pa.dao.perinfo.UserManageMapper;
import com.zqu.pa.dao.perinfo.UserPartyInfoMapper;
import com.zqu.pa.dao.perinfo.UserPersonInfoMapper;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPartyInfoExample;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfoExample;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.vo.perinfo.GradeClassSortList;
import com.zqu.pa.vo.perinfo.UserCheckList;
import com.zqu.pa.vo.perinfo.UserList;
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
    
    @Autowired
    UserManageMapper userManageDao;

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
    public List<UserList> getUserList(int branchId, int checkState) {

/*        //从数据库获取总记录数，checkState为是否审核
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
*/
        
        List<UserList> list = new ArrayList<UserList>();
        try {
            list = userListDao.getUserBasicList(branchId, checkState);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<UserCheckList> getUserCheckList(int branchId) {
        List<UserCheckList> list = new ArrayList<>();
        try {
            list = userListDao.getUserCheckList(branchId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    @Override
    public String checkUser(int branchId, String userId) {
        
        //1.验证党支部ID是否相同,0为所有党支部权限
        if(branchId==0);
        else {
            UserPartyInfoExample partyExample = new UserPartyInfoExample();
            com.zqu.pa.entity.perinfo.UserPartyInfoExample.Criteria c = partyExample.createCriteria();
            c.andUserIdEqualTo(userId);
            c.andBranchIdEqualTo(branchId);
            List<UserPartyInfo> list = userPartyInfoDao.selectByExample(partyExample);
            if(list==null)
                return "审核失败!";
            if(list.size()==0)
                return "审核所属党支部不同!";
        }
        
        //在个人信息表将审核状态置1，审核人ID填入，账号信息表状态置1
        //2.先获取审核人ID
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null||basicInfo.getUserId()==null)
            return "无法获取当前session信息用户ID";
        String checkId = basicInfo.getUserId();
        
        //个人信息表
        UserPersonInfo userPersonInfo = new UserPersonInfo();
        //审核人
        userPersonInfo.setCheckId(checkId);
        //更新审核状态为1
        userPersonInfo.setCheckState(1);
        UserPersonInfoExample perExample = new UserPersonInfoExample();
        com.zqu.pa.entity.perinfo.UserPersonInfoExample.Criteria criteria = perExample.createCriteria();
        //限制审核前审核状态为0
        criteria.andUserIdEqualTo(userId);
        criteria.andCheckStateEqualTo(0);
        //3.更新个人信息表审核状态
        userPersonInfoDao.updateByExampleSelective(userPersonInfo, perExample);
        
        //4.更新账号表状态
        if(userManageDao.changeUserState(userId)==0) {
            System.out.println("error check");
            throw new RuntimeException("账号状态异常");
        }

        return "审核成功!";
    }

    
    @Override
    public GradeClassSortList getGradeClass(int branchId) {
        GradeClassSortList list = new GradeClassSortList();
        try {
            //获取年级列表
            list.setGradeList(userListDao.getGradeList(branchId));
            //获取班级列表
            list.setClassNameList(userListDao.getClassList(branchId));
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return list;
    }

}
