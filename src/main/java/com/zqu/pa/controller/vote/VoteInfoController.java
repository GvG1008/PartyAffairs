package com.zqu.pa.controller.vote;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.entity.vote.VoteInfo;
import com.zqu.pa.service.vote.VoteInfoService;

@Controller
@RequestMapping("/voteinfo")
public class VoteInfoController {

    @Autowired
    private VoteInfoService voteInfoService;
    
    /**
     * 创建一个投票
     * @param voteInfo 投票基本信息
     * @param choice_user 投票选项和参与人员键值对 {"choice":[], "voteUser":[]}
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ServerResponse createVote(VoteInfo voteInfo, 
            @RequestBody Map<String, Object> choice_user) {
        
        ServerResponse result = null;
        try {
            result = voteInfoService.createVote(voteInfo, choice_user);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }       
        return result;
    }
}
