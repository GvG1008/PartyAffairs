package com.zqu.pa.controller.vote;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zqu.pa.common.ServerResponse;
import com.zqu.pa.service.vote.VoteResultService;
import com.zqu.pa.vo.vote.ResponseVoteSortChoiceResult;
import com.zqu.pa.vo.vote.ResponseVoteChoiceResult;

@Controller
@RequestMapping("/voteresult")
public class VoteResultController {

    @Autowired
    private VoteResultService voteResultService;
    
    /**
     * 记录用户投票结果
     * @param voteId 投票ID
     * @param abandon 是否弃选
     * @param choice 选中结果数组（排序类型投票要求排序）
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/{voteId}/{abandon}", method = RequestMethod.POST)
    public ServerResponse recordVote(@PathVariable("voteId") Long voteId, 
            @PathVariable("abandon") Integer abandon, @RequestBody(required = false) List<Long> choice) {
        
        ServerResponse result = null;
        try {
            result = voteResultService.insertVoteResult(voteId, abandon, choice);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }
    
    /**
     * 投票单选/多选结果，每个选项被选中次数count
     * @param voteId 投票ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "choice/{voteId}", method = RequestMethod.GET)
    public ServerResponse<ResponseVoteChoiceResult> getChoiceResult(@PathVariable("voteId") Long voteId) {
        
        ServerResponse result = null;
        try {
            result = voteResultService.getChoiceResult(voteId);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }
    
    /**
     * 投票排序结果，每个选项被选排名第一次数count1，被选排名第二次数count2
     * @param voteId 投票ID
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "sortchoice/{voteId}", method = RequestMethod.GET)
    public ServerResponse<ResponseVoteSortChoiceResult> getSortChoiceResult(@PathVariable("voteId") Long voteId) {
        
        ServerResponse result = null;
        try {
            result = voteResultService.getSortChoiceResult(voteId);
        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
        return result;
    }
}
