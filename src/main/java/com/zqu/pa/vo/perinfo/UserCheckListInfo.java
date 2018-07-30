package com.zqu.pa.vo.perinfo;

import java.util.List;

public class UserCheckListInfo {
    //当前页数
    private int pageNum;
    //总共多少条记录
    private int totalInfoNum;
    //总共多少页
    private int totalPageNum;
    //列表信息
    List<UserCheckList> list;
    
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
    
    public int getTotalPageNum() {
        return totalPageNum;
    }
    
    public void setTotalPageNum(int totalPageNum) {
        this.totalPageNum = totalPageNum;
    }
    
    public List<UserCheckList> getList() {
        return list;
    }
    
    public void setList(List<UserCheckList> list) {
        this.list = list;
    }
}
