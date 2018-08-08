package com.zqu.pa.service.partyactivity;

import com.zqu.pa.vo.partyactivity.PageOfList;

public interface UserActivityService {

    //根据页数和每页记录数，和所属党支部返回对应活动列表信息
    PageOfList getMenuInfo(int page, int num, int branchId);

}
