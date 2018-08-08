package com.zqu.pa.service.perinfo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.common.UserInfoDefault;
import com.zqu.pa.dao.perinfo.UserListMapper;
import com.zqu.pa.dao.perinfo.UserManageMapper;
import com.zqu.pa.dao.perinfo.UserPartyInfoMapper;
import com.zqu.pa.dao.perinfo.UserPersonInfoMapper;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPartyInfoExample;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfoExample;
import com.zqu.pa.service.perinfo.UserInfoService;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.vo.perinfo.AllUserInfo;
import com.zqu.pa.vo.perinfo.Branch;
import com.zqu.pa.vo.perinfo.GradeClassSortList;
import com.zqu.pa.vo.perinfo.Role;
import com.zqu.pa.vo.perinfo.UserCheckList;
import com.zqu.pa.vo.perinfo.UserList;
import com.zqu.pa.vo.perinfo.UserListInfo;
import com.zqu.pa.vo.userInfo.UserBasicInfo;
import com.zqu.pa.vo.userInfo.UserLoginInfo;

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

/*
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
    }*/

    
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

    @Transactional
    @Override
    public String checkUserByBatch(int branchId, String userId) {
        //获取userId的list
        List<String> userIds = new ArrayList<>();
        int index = 0;
        int i=0;
        for( ; i<userId.length(); i++) {
            if(userId.substring(i, i+1).equals("&")) {
                userIds.add(userId.substring(index, i));
                index = i+1;
            }
        }
        if(i>index)
            userIds.add(userId.substring(index, i));
        
        if(userIds==null||userIds.size()==0)
            return "审核失败!";

        //1.验证党支部ID是否相同,0为所有党支部权限
        if(branchId==0);
        else {
            //在当前userIds列表里查询党支部ID相同的userId列表
            List<String> list = userManageDao.checkBranchByBatch(userIds, branchId);
            if(list==null)
                return "审核失败";
            if(userIds.size()!=list.size())
                return "审核存在所属党支部不同";
        }
        
        //在个人信息表将审核状态置1，审核人ID填入，账号信息表状态置1
        //2.先获取审核人ID
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null||basicInfo.getUserId()==null)
            return "无法获取当前session信息用户ID";
        String checkId = basicInfo.getUserId();
        
        //3.更新个人信息表审核状态
        int result = 0;
        try {
            result = userManageDao.updateCheckUserByBatch(checkId, userIds);
        }catch (Exception e) {
            System.out.println("更新个人信息状态表失败");
            e.printStackTrace();
        }
        if(result==0)
            throw new RuntimeException("更新个人信息状态表失败");
        
        //4.更新账号表状态
        try {
            result = userManageDao.batchChangeUserState(userIds);    
        }catch (Exception e) {
            System.out.println("更新账号表状态失败");
            e.printStackTrace();
        }
        if(result==0)
            throw new RuntimeException("更新个人信息状态表失败");
        return "审核成功!";
    }

    @Transactional
    @Override
    public String deleteUser(int branchId, String userId) {
        //获取userId的list
        List<String> userIds = new ArrayList<>();
        int index = 0;
        int i=0;
        for( ; i<userId.length(); i++) {
            if(userId.substring(i, i+1).equals("&")) {
                userIds.add(userId.substring(index, i));
                index = i+1;
            }
        }
        if(i>index)
            userIds.add(userId.substring(index, i));
        
        if(userIds==null||userIds.size()==0)
            return "删除失败!";
        
        //1.验证党支部ID是否相同,0为所有党支部权限
        if(branchId==0);
        else {
            //在当前userIds列表里查询党支部ID相同的userId列表
            List<String> list = userManageDao.checkBranchByBatch(userIds, branchId);
            if(list==null)
                return "删除失败!";
            if(userIds.size()!=list.size())
                return "无法删除不同党支部的人员";
        }
        
        //2.逻辑删除账号信息表state置-2
        int result = 0;
        try {
            result = userManageDao.deleteUserLogin(userIds);
        }catch (Exception e) {
            System.out.println("删除账号信息失败");
            e.printStackTrace();
        }
        if(result==0)
            throw new RuntimeException("删除账号信息失败");
        
        //3.逻辑删除个人信息表，is_delete字段置1
        try {
            result = userManageDao.deleteUserPersonInfo(userIds);
        }catch (Exception e) {
            System.out.println("删除个人信息表失败");
            e.printStackTrace();
        }
        if(result==0)
            throw new RuntimeException("删除个人信息表失败");
        
        //4.逻辑删除党员信息表，is_delete字段置1
        try {
            result = userManageDao.deleteUserPartyInfo(userIds);
        }catch (Exception e) {
            System.out.println("删除党员信息表失败");
            e.printStackTrace();
        }
        if(result==0)
            throw new RuntimeException("删除党员信息表失败");
        
        return "删除成功!";
    }

    @Transactional
    @Override
    public String insertNewUser(AllUserInfo user) {

        UserLoginInfo userLoginInfo = new UserLoginInfo();
        UserPartyInfo userPartyInfo = new UserPartyInfo();
        UserPersonInfo userPersonInfo = new UserPersonInfo();
        
        //获取当前用户userId
        String createId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(createId==null)
            return "无法获取当前session信息";
        
        //注入账号信息表
        userLoginInfo.setUserId(user.getUserId());
        String password = user.getPassword();
        if(password==null||password.equals("")) {
            //密码初始为账号id
            password = user.getUserId();
        }
        //MD5：盐+密码+次数
        Md5Hash md5Hash = new Md5Hash(password,password,1);
        password = md5Hash.toString();
        userLoginInfo.setPassword(password);
        userLoginInfo.setRoleId(user.getRoleId());
        userLoginInfo.setState(0);
        
        //注入个人信息表
        userPersonInfo.setUserId(user.getUserId());
        userPersonInfo.setName(user.getName());
        userPersonInfo.setNickname(null);
        userPersonInfo.setClassName(user.getClassName());
        userPersonInfo.setBirthday(null);//生日：xxxx/xx/xx
        userPersonInfo.setGrade(user.getGrade());
        if(user.getSex()==null)
            userPersonInfo.setImgHead(null);//头像路径
        else if(user.getSex().equals("男"))
            userPersonInfo.setImgHead(UserInfoDefault.IMG_HEAD_MALE);//头像路径
        else if(user.getSex().equals("女"))
            userPersonInfo.setImgHead(UserInfoDefault.IMG_HEAD_FEMALE);//头像路径
        else
            userPersonInfo.setImgHead(null);//头像路径
        userPersonInfo.setProfile(null);
        userPersonInfo.setEmail(null);
        userPersonInfo.setCreateId(createId);
        userPersonInfo.setCreateTime(DateToString.getDateString("yyyy/MM/dd HH:mm:ss",new Date()));
        userPersonInfo.setLastTime(new Date());
        userPersonInfo.setCheckState(0);
        userPersonInfo.setCheckId(null);
        userPersonInfo.setSex(user.getSex());
        
        //注入党内信息表
        userPartyInfo.setUserId(user.getUserId());
        userPartyInfo.setBranchId(user.getBranchId());
        userPartyInfo.setName(user.getName());
        userPartyInfo.setNameFormer(user.getNameFormer());
        userPartyInfo.setSex(user.getSex());
        userPartyInfo.setNational(user.getNational());
        userPartyInfo.setNativePlace(user.getNativePlace());
        userPartyInfo.setBirthPlace(user.getBirthPlace());
        userPartyInfo.setPlaceRegistered(user.getPlaceRegistered());
        userPartyInfo.setHomeAddress(user.getHomeAddress());
        userPartyInfo.setTel(user.getTel());
        userPartyInfo.setFamilyBackground(user.getFamilyBackground());
        userPartyInfo.setBirthDate(user.getBirthDate());
        userPartyInfo.setPoliticalStatus(user.getPoliticalStatus());
        userPartyInfo.setIdCard(user.getIdCard());
        userPartyInfo.setMaritalStatus(user.getMaritalStatus());
        userPartyInfo.setOrganizationUnit(user.getOrganizationUnit());
        userPartyInfo.setPersonalIdentity(user.getPersonalIdentity());
        userPartyInfo.setEducationalBackground(user.getEducationalBackground());
        userPartyInfo.setProfessional(user.getProfessional());
        userPartyInfo.setGraduateSchool(user.getGraduateSchool());
        userPartyInfo.setTimeApplicationforparty(user.getTimeApplicationforparty());
        userPartyInfo.setTimeIntoparty(user.getTimeIntoparty());
        userPartyInfo.setTimePositive(user.getTimePositive());
        userPartyInfo.setTypeDevelopment(user.getTypeDevelopment());
        userPartyInfo.setTotot(user.getTotot());
        userPartyInfo.setOutUnit(user.getOutUnit());
        
        int result1,result2,result3;
        result1 = userManageDao.insertUserLoginInfo(userLoginInfo);
        result2 = userPartyInfoDao.insert(userPartyInfo);
        result3 = userPersonInfoDao.insert(userPersonInfo);
        if(result1==0||result2==0||result3==0)
            throw new RuntimeException("未添加新用户");
        
        return "创建用户成功!";
    }

    @Override
    public List<Branch> getBranchList() {

        return userListDao.getBranchList();
    }

    @Override
    public List<Role> getRoleList() {
        
        return userListDao.getRoleList();
    }

    @Override
    public int updateImgHead(String fullPath , String userId) {

        //判断是否为修改当前用户头像
        if(userId==null)
            userId = (String)SecurityUtils.getSubject().getSession().getAttribute("userId");
        if(userId==null)
            return 0;
        
        UserPersonInfo info = new UserPersonInfo();
        info.setUserId(userId);
        info.setImgHead(fullPath);
        return userPersonInfoDao.updateByPrimaryKeySelective(info);
    }
}
