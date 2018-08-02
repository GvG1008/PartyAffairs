package com.zqu.pa.dao.newsnotices;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zqu.pa.vo.newsnotices.HomeList;
import com.zqu.pa.vo.newsnotices.PublicityInfo;

public interface PublicityManageMapper {
    
    List<PublicityInfo> getNewsManageList(@Param("state")int state);
}
