package com.zqu.pa.controller.groupalbum;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    @RequestMapping(value = "", method = RequestMethod.GET)
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
    
    /**
     * 创建一个团支部并关联管理员账号
     * @param groupName 党支部名称
     * @param userId 管理员账号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{groupName}/{userId}", method = RequestMethod.POST)
    public ServerResponse createGroupBranch(@PathVariable String groupName, @PathVariable String userId) {
        
        return groupBranchService.createGroupBranch(groupName, userId);
    }
    
    /**
     * 更新团支部信息
     * @param groupId 团支部ID
     * @param groupBranch 接收更新的团支部名称或管理员账号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{groupId}", method = RequestMethod.PUT)
    public ServerResponse updateGroupName(@PathVariable Integer groupId, GroupBranch groupBranch) {
        
        return groupBranchService.updateGroupBranch(groupId, groupBranch);
    }
    
}
