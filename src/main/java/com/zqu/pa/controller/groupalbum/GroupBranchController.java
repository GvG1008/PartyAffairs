package com.zqu.pa.controller.groupalbum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.groupalbum.GroupBranch;
import com.zqu.pa.service.groupalbum.GroupBranchService;

@Controller
@RequestMapping("/groupbranch")
public class GroupBranchController {

    @Autowired
    private GroupBranchService groupBranchService;
    
    /**
     * 返回团支部列表
     * @return
     */
    @ResponseBody
    @RequestMapping("")
    public ServerResponse listGroupBranch() {
        
        List<GroupBranch> listGroup = new ArrayList<>();
        try {
            listGroup = groupBranchService.listGroupBranch();
        } catch(Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }      
        return ServerResponse.createBySuccess(listGroup);
    }
}
