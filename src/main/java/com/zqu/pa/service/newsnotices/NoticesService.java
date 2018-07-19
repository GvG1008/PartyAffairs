package com.zqu.pa.service.newsnotices;

import java.util.List;

import com.zqu.pa.vo.newsnotices.HomeList;

public interface NoticesService {

    List<HomeList> getHomeNewsList(int num, int type);
}
