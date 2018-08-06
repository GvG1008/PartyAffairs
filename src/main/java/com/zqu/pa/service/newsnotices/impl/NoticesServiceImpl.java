package com.zqu.pa.service.newsnotices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NoticesMapper;
import com.zqu.pa.dao.newsnotices.PublicityListMapper;
import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.entity.newsnotices.NoticesExample;
import com.zqu.pa.entity.newsnotices.NoticesExample.Criteria;
import com.zqu.pa.service.newsnotices.NoticesService;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PageOfList;

@Service
public class NoticesServiceImpl implements NoticesService{

    @Autowired
    NoticesMapper noticesDao;
    
    @Autowired
    PublicityListMapper publicityListDao;

    @Override
    public List<HomeList> getHomeNewsList(int num, int type) {
        return publicityListDao.getNoticesHomeListLimit(num, type);
    }

    @Override
    public PageOfList getMenuInfo(int page, int num, int type) {
        PageOfList info = new PageOfList();
        
        NoticesExample example = new NoticesExample();
        Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo(1);
        criteria.andTypeEqualTo(type);
        
        //总记录条数
        int totalInfoNum = (int) noticesDao.countByExample(example);
        info.setTotalInfoNum(totalInfoNum);
        //总页数
        int totalPageNum = (int)(totalInfoNum+num-1)/num;
        info.setTotalPageNum(totalPageNum);
        
        if(totalPageNum<page)
            page = totalPageNum;
        info.setPageNum(page);
        
        //limit index,num  从第index+1条记录开始，num条记录 搜索相应类型公示
        int index = (page-1)*num;
        info.setList(publicityListDao.getNoticesMenuListLimit(index, num, type));
        
        return info;
    }

    @Override
    public Notices getNoticesInfo(int notices_id, int type, int stateType) {
        Notices notices = new Notices();
        //根据ID搜索公示
        notices = noticesDao.selectByPrimaryKey(notices_id);
        
        if(notices==null)
            return null;
        if(notices.getType()!=type)//搜索公示类型不对应，返回空
            return null;
        if(stateType==1&&notices.getState()!=1)//stateType区别前后台，前台不予显示未审核
            return null;
        else if(stateType==1&&notices.getState()==1) {
            //查看该条公示信息页面，此公示click+1
            Notices notices2 = new Notices();
            notices2.setNoticesId(notices.getNoticesId());
            notices2.setClick(notices.getClick()+1);
            if(noticesDao.updateByPrimaryKeySelective(notices2)==0)
                return null;
            notices.setClick(notices.getClick()+1);
            //前台查看，删除多余信息
            notices.setCoverpath(null);
            notices.setCreatorId(null);
            notices.setLastTime(null);
        }
        return notices;
    }
}
