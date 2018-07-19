package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.vo.newsnotices.HomeList;

public interface NoticesService {

    //根据条数和公示类型号，返回公示信息列表
    List<HomeList> getHomeNewsList(int num, int type);
}
