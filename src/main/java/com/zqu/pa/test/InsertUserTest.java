package com.zqu.pa.test;

import java.util.Date;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.dao.perinfo.UserManageMapper;
import com.zqu.pa.dao.perinfo.UserPartyInfoMapper;
import com.zqu.pa.dao.perinfo.UserPersonInfoMapper;
import com.zqu.pa.entity.perinfo.UserPartyInfo;
import com.zqu.pa.entity.perinfo.UserPersonInfo;
import com.zqu.pa.utils.DateToString;
import com.zqu.pa.vo.userInfo.UserLoginInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@Transactional
public class InsertUserTest {

    @Autowired
    UserManageMapper userManageDao;
    
    @Autowired
    UserPartyInfoMapper userPartyInfoDao;
    
    @Autowired
    UserPersonInfoMapper userPerInfoDao;
    
    /**
     * 添加用户测试
     */
    @Test
    @Rollback(false)
    public void insertUser()  {
        String userId;
        String password;
        int roleId;
        int user_state;

        //党员信息
        Integer branchId;
        String name;
        String nameFormer;
        String sex;
        String national;
        String nativePlace;
        String birthPlace;
        String placeRegistered;
        String homeAddress;
        String tel;
        String familyBackground;
        String birthDate;
        String politicalStatus;
        String idCard;
        String maritalStatus;
        String organizationUnit;
        String personalIdentity;
        String educationalBackground;
        String professional;
        String graduateSchool;
        String timeApplicationforparty;
        String timeIntoparty;
        String timePositive;
        String typeDevelopment;
        String totot;
        String outUnit;
        
        //个人信息
        String nickname;
        String grade;
        String className;
        String imgHead;
        String profile;
        String email;
        String birthday;
        String createTime;
        String lastTime;
        Integer checkState;
        String createId;
        String checkId;
        
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        UserPartyInfo userPartyInfo = new UserPartyInfo();
        UserPersonInfo userPersonInfo = new UserPersonInfo();
        
        for(int i=20;i<=100;i++)
        {
            userId = "100"+String.valueOf(i);
            //MD5：盐+密码+次数
            Md5Hash md5Hash = new Md5Hash(userId,userId,1);
            password = md5Hash.toString();
            roleId = 1;
            user_state = 0;
            
            branchId = 3;
            name = userId +"号小狗";
            nameFormer = userId+"号肉食者";
            sex = "女";
            national = "汉族";
            nativePlace = "广东肇庆";
            birthPlace = "广东肇庆";
            placeRegistered = "广东肇庆";
            homeAddress = "广东肇庆市端州区肇庆学院主校区";
            tel = "10086-"+userId;
            familyBackground = "搬砖";
            birthDate="1999/10/"+String.valueOf(i);
            politicalStatus="党员";
            idCard="4xxxxx199910"+String.valueOf(i)+"xxxx";
            maritalStatus="未婚";
            //
            organizationUnit="肇庆学院计网络工程学院";
            personalIdentity="大学生";
            educationalBackground="大学本科";
            //
            professional="网络工程";
            graduateSchool="清华中学";
            timeApplicationforparty="2015/09/10";
            timeIntoparty="xxxx/xx/xx";
            timePositive="xxxx/xx/xx";
            typeDevelopment="团员推优";
            totot=null;
            outUnit=null;
            
            nickname = "老虎"+String.valueOf(i)+"号";
            grade = "2015";
            //
            className = "2015级网络工程1班";
            imgHead = null;
            profile = null;
            email = "xxxxxxx@qq.com";
            birthday = birthDate;
            createTime = DateToString.getDateString("yyyy/MM/dd HH:mm:ss",new Date());
            lastTime = DateToString.getDateString("yyyy/MM/dd HH:mm:ss",new Date());
            checkState = 0;
            createId = "123";
            checkId = "123";
            
            //注入
            userLoginInfo.setUserId(userId);
            userLoginInfo.setPassword(password);
            userLoginInfo.setRoleId(roleId);
            userLoginInfo.setState(user_state);
            
            userPersonInfo.setUserId(userId);
            userPersonInfo.setName(name);
            userPersonInfo.setNickname(nickname);
            userPersonInfo.setClassName(className);
            userPersonInfo.setBirthday(birthday);
            userPersonInfo.setGrade(grade);
            userPersonInfo.setImgHead(imgHead);
            userPersonInfo.setProfile(profile);
            userPersonInfo.setEmail(email);
            userPersonInfo.setBirthday(birthday);
            userPersonInfo.setCreateId(createId);
            userPersonInfo.setCreateTime(createTime);
            userPersonInfo.setLastTime(new Date());
            userPersonInfo.setCheckState(checkState);
            userPersonInfo.setCheckId(checkId);
            userPersonInfo.setSex(sex);
            
            userPartyInfo.setUserId(userId);
            userPartyInfo.setBranchId(branchId);
            userPartyInfo.setName(name);
            userPartyInfo.setNameFormer(nameFormer);
            userPartyInfo.setSex(sex);
            userPartyInfo.setNational(national);
            userPartyInfo.setNativePlace(nativePlace);
            userPartyInfo.setBirthPlace(birthPlace);
            userPartyInfo.setPlaceRegistered(placeRegistered);
            userPartyInfo.setHomeAddress(homeAddress);
            userPartyInfo.setTel(tel);
            userPartyInfo.setFamilyBackground(familyBackground);
            userPartyInfo.setBirthDate(birthDate);
            userPartyInfo.setPoliticalStatus(politicalStatus);
            userPartyInfo.setIdCard(idCard);
            userPartyInfo.setMaritalStatus(maritalStatus);
            userPartyInfo.setOrganizationUnit(organizationUnit);
            userPartyInfo.setPersonalIdentity(personalIdentity);
            userPartyInfo.setEducationalBackground(educationalBackground);
            userPartyInfo.setProfessional(professional);
            userPartyInfo.setGraduateSchool(graduateSchool);
            userPartyInfo.setTimeApplicationforparty(timeApplicationforparty);
            userPartyInfo.setTimeIntoparty(timeIntoparty);
            userPartyInfo.setTimePositive(timePositive);
            userPartyInfo.setTypeDevelopment(typeDevelopment);
            userPartyInfo.setTotot(totot);
            userPartyInfo.setOutUnit(outUnit);
           
            
            try {
                userManageDao.insertUserLoginInfo(userLoginInfo);
                userPartyInfoDao.insertSelective(userPartyInfo);
                userPerInfoDao.insertSelective(userPersonInfo);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
