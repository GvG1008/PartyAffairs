package com.zqu.pa.service.newsnotices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zqu.pa.dao.newsnotices.NoticesMapper;
import com.zqu.pa.entity.newsnotices.NoticesExample;
import com.zqu.pa.entity.newsnotices.NoticesExample.Criteria;
import com.zqu.pa.service.newsnotices.NoticesService;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PageOfList;

@Service
public class NoticesServiceImpl implements NoticesService{

    @Autowired
    NoticesMapper noticesDao;
    
    @Override
    public List<HomeList> getHomeNewsList(int num, int type) {
        return noticesDao.getHomeListLimit(num, type);
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
        info.setList(noticesDao.getMenuListLimit(index, num, type));
        
        return info;
    }
}
