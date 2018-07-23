package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.entity.newsnotices.Notices;
import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PageOfList;

public interface NoticesService {

    //根据条数和公示类型号，返回公示信息列表
    List<HomeList> getHomeNewsList(int num, int type);
    
    //根据页数，每页数量，公示类型 返回公示列表页面信息
    PageOfList getMenuInfo(int page, int num, int type);

    //根据type区分公示类型，stateType分为前台和后台获取，根据ID获取公示详细信息
    Notices getNoticesInfo(int notices_id, int type, int stateType);
}
