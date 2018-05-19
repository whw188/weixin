package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.dao.model.CommentAgreeModel;
import com.xzh.weixin.web.service.CommentAgreeService;
import com.xzh.weixin.web.service.ResourceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/commentAgree")
public class CommentAgreeController {

    @Resource
    CommentAgreeService commentAgreeService;


    @ResponseBody
    @RequestMapping(value = "/updateAgree", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateAgree(String uid, long cmid) {

        ResponseDTO<String> responseDTO = commentAgreeService.updateAgree(uid, cmid, 1);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/updateTread", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateTread(String uid, long cmid) {

        ResponseDTO<String> responseDTO = commentAgreeService.updateTread(uid, cmid, 1);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByUidAndCmid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<CommentAgreeModel> selectByUidAndCmid(String uid, long cmid) {

        ResponseDTO<CommentAgreeModel> responseDTO = commentAgreeService.selectByUidAndCmid(uid, cmid);
        return responseDTO;
    }

}
