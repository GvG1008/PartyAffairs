package com.zqu.pa.service.partyactivity.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.dao.partyactivity.PartyActivityManageMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityRoleMapper;
import com.zqu.pa.dao.partyactivity.PartyActivityUserMapper;
import com.zqu.pa.entity.partyactivity.PartyActivity;
import com.zqu.pa.entity.partyactivity.PartyActivityExample;
import com.zqu.pa.entity.partyactivity.PartyActivityUser;
import com.zqu.pa.entity.partyactivity.PartyActivityUserExample;
import com.zqu.pa.entity.partyactivity.PartyActivityUserExample.Criteria;
import com.zqu.pa.service.partyactivity.ActivityManageService;
import com.zqu.pa.utils.StringTimeToLong;
import com.zqu.pa.vo.partyactivity.ActivityInfo;
import com.zqu.pa.vo.partyactivity.ActivityManageMenu;
import com.zqu.pa.vo.partyactivity.ApplyMsg;
import com.zqu.pa.vo.userInfo.UserBasicInfo;

@Service
public class ActivityManageServiceImpl implements ActivityManageService {

    @Autowired
    PartyActivityManageMapper partyActivityManageDao;
    
    @Autowired
    PartyActivityMapper partyActivityDao;
    
    @Autowired
    PartyActivityRoleMapper partyActivityRoleDao;
    
    @Autowired
    PartyActivityUserMapper partyActivityUserDao;
    
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

    @Override
    public ServerResponse<String> deleteActivityBatch(String activityId) {
        //获取当前session里的当前用户所属党支部
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息");
        
        List<Integer> Ids = this.StringToListId(activityId);
        if(Ids==null||Ids.size()==0)
            return ServerResponse.createByErrorMessage("删除失败!");
        int result=0;
        try {
            result = partyActivityManageDao.deleteActivityBatch(basicInfo.getBranchId(),Ids);
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("删除失败!");
        }
        if(result==0)
            return ServerResponse.createByErrorMessage("删除失败!");
        return ServerResponse.createBySuccessMessage("成功删除"+result+"个，失败"+(Ids.size()-result)+"个");
    }

    /**
     * 根据Id字符串返回Id的list
     * @param Id
     * @return
     */
    public List<Integer> StringToListId(String Id) {
        if(Id==null||Id.equals(""))
            return null;
        
        List<Integer> Ids = new ArrayList<>();
        
        try {
            int index=0;
            int i=0;
            for( ; i<Id.length() ; i++) {
                if(Id.substring(i, i+1).equals("&")) {
                    if(i==0) {
                        index = i+1;
                        continue;
                    }
                    Ids.add(Integer.parseInt(Id.substring(index, i)));
                    index = i+1;
                }
            }
            if(i>index)
                Ids.add(Integer.parseInt(Id.substring(index, i)));
        } catch (NumberFormatException x) {
            return null;
        }
        return Ids;
    }

    @Override
    public ServerResponse<String> updateActivityInfo(ActivityInfo info) {
        if(info.getActivityId()==null)
            return ServerResponse.createByErrorMessage("修改失败!");
        
        PartyActivity pa = new PartyActivity();
        pa.setActivityId(info.getActivityId());
        pa.setName(info.getName());
        pa.setContent(info.getContent());
        pa.setAddress(info.getAddress());
        pa.setNum(info.getNum());
        pa.setReleaseUnit(info.getUnit());
        
        Long registrationStart = StringTimeToLong.convertTimeToLong(info.getRegistrationStart());//将时间"yyyy-MM-dd hh:mm:ss"字符串转换为long型
        Long registrationEnd = StringTimeToLong.convertTimeToLong(info.getRegistrationEnd());
        Long activityStart = StringTimeToLong.convertTimeToLong(info.getActivityStart());
        Long activityEnd = StringTimeToLong.convertTimeToLong(info.getActivityEnd());
        if(registrationStart.longValue()==0L)
            registrationStart=null;
        if(registrationEnd.longValue()==0L)
            registrationEnd=null;
        if(activityStart.longValue()==0L)
            activityStart=null;
        if(activityEnd.longValue()==0L)
            activityEnd=null;
        pa.setRegistrationStart(registrationStart);
        pa.setRegistrationEnd(registrationEnd);
        pa.setActivityStart(activityStart);
        pa.setActivityEnd(activityEnd);
        
        int result = 0;
        try {
            result = partyActivityDao.updateByPrimaryKeySelective(pa);
        }catch (Exception e) {
            return ServerResponse.createByErrorMessage("修改失败!");
        }
        if(result==0)
            return ServerResponse.createByErrorMessage("修改失败!");
        return ServerResponse.createBySuccessMessage("修改成功!");
    }

    @Transactional
    @Override
    public ServerResponse<List<ApplyMsg>> getactivityApplyList(Integer activityId, Integer checkState) {
        
        List<ApplyMsg> listInfo = partyActivityManageDao.getActivityApply(activityId,checkState);
        if(listInfo==null)
            return ServerResponse.createByErrorMessage("获取列表失败");
        if(listInfo.size()==0)
            return ServerResponse.createBySuccess("获取列表为空", listInfo);
        
        return ServerResponse.createBySuccess("获取列表成功", listInfo);
    }

    @Override
    public ServerResponse<String> checkApply(Integer activityId, String userId) {
        //获取userId的list
        List<String> userIds = new ArrayList<>();
        int index = 0;
        int i=0;
        for( ; i<userId.length(); i++) {
            if(userId.substring(i, i+1).equals("&")) {
                if(i==0) {
                    index = i+1;
                    continue;
                }
                userIds.add(userId.substring(index, i));
                index = i+1;
            }
        }
        if(i>index)
            userIds.add(userId.substring(index, i));
        
        if(userIds==null||userIds.size()==0)
            return ServerResponse.createByErrorMessage("用户ID出错");
        
        //验证当前活动是否未结束
        PartyActivity partyActivity = partyActivityDao.selectByPrimaryKey(activityId);
        Long nowTime = new Date().getTime();
        if(partyActivity==null) {
            return ServerResponse.createByErrorMessage("该活动不存在");
        }else if(partyActivity.getIsDelete()==1) {
            return ServerResponse.createByErrorMessage("该活动已取消");
        }else if(partyActivity.getActivityEnd().longValue()<=nowTime.longValue()) {
            return ServerResponse.createByErrorMessage("该活动已结束");
        }
        
        //验证活动人数是否已经满员
        PartyActivityUserExample example = new PartyActivityUserExample();
        Criteria criteria = example.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        criteria.andCheckStateEqualTo(1);
        int activityCount = (int)partyActivityUserDao.countByExample(example);
        if(activityCount+userIds.size()>partyActivity.getNum()) {
            return ServerResponse.createByErrorMessage("活动人数超出,审核失败");
        }
        
        //获取当前session用户的ID
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null||basicInfo.getUserId()==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息用户ID");
        String checkId = basicInfo.getUserId();
        
        int result = partyActivityManageDao.passApplyByBatch(checkId, activityId, userIds);
        
        return ServerResponse.createBySuccessMessage("成功审核"+result+",失败"+(userIds.size()-result));
    }

    @Transactional
    @Override
    public ServerResponse<String> revokeApply(Integer activityId, String userId) {
        //获取userId的list
        List<String> userIds = new ArrayList<>();
        int index = 0;
        int i=0;
        for( ; i<userId.length(); i++) {
            if(userId.substring(i, i+1).equals("&")) {
                if(i==0) {
                    index = i+1;
                    continue;
                }
                userIds.add(userId.substring(index, i));
                index = i+1;
            }
        }
        if(i>index)
            userIds.add(userId.substring(index, i));
        
        if(userIds==null||userIds.size()==0)
            return ServerResponse.createByErrorMessage("用户ID出错");
        
        //验证当前活动是否未结束
        PartyActivity partyActivity = partyActivityDao.selectByPrimaryKey(activityId);
        Long nowTime = new Date().getTime();
        if(partyActivity==null) {
            return ServerResponse.createByErrorMessage("该活动不存在");
        }else if(partyActivity.getIsDelete()==1) {
            return ServerResponse.createByErrorMessage("该活动已取消");
        }else if(partyActivity.getActivityEnd().longValue()<=nowTime.longValue()) {
            return ServerResponse.createByErrorMessage("该活动已结束");
        }
        
        //获取当前session用户的ID
        UserBasicInfo basicInfo = (UserBasicInfo)SecurityUtils.getSubject().getSession().getAttribute("basicInfo");
        if(basicInfo==null||basicInfo.getUserId()==null)
            return ServerResponse.createByErrorMessage("无法获取当前session信息用户ID");
        String checkId = basicInfo.getUserId();
        
        int result = partyActivityManageDao.revokeApplyByBatch(checkId, activityId, userIds);
        
        return ServerResponse.createBySuccessMessage("成功审核"+result+",失败"+(userIds.size()-result));
    }

}
