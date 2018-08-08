package com.zqu.pa.vo.partyactivity;

import java.util.List;

public class PageOfList {
    
    private int pageNum; //当前页数
    
    private int totalInfoNum; //总共多少条记录
    
    private int totalActiveNum; //报名进行时活动数
    
    private int totalPageNum; //总共多少页
    
    private List<ActivityMenuList> list; //列表信息

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getTotalInfoNum() {
        return totalInfoNum;
    }

    public void setTotalInfoNum(int totalInfoNum) {
        this.totalInfoNum = totalInfoNum;
    }

    
    public int getTotalActiveNum() {
        return totalActiveNum;
    }

    public void setTotalActiveNum(int totalActiveNum) {
        this.totalActiveNum = totalActiveNum;
    }

    public int getTotalPageNum() {
        return totalPageNum;
    }

    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }

    public List<ActivityMenuList> getList() {
        return list;
    }

    public void setList(List<ActivityMenuList> list) {
        this.list = list;
    }
}
