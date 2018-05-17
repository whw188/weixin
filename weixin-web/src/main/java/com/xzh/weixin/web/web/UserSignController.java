package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.UserSignModel;
import com.xzh.weixin.web.service.UserService;
import com.xzh.weixin.web.service.UserSignService;
import com.xzh.weixin.web.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/userSign")
public class UserSignController {

    private final static Logger logger = LoggerFactory.getLogger(UserSignController.class);

    @Resource
    UserService userService;
    @Resource
    UserSignService userSignService;


    @ResponseBody
    @RequestMapping(value = "/sign", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> sign(String uid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {

            ResponseDTO<List<UserSignModel>> listResponseDTO = userSignService.selectByUid(uid);
            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                List<UserSignModel> attach = listResponseDTO.getAttach();

                if (attach == null || attach.size() == 0 || !DateUtils.isToday(attach.get(0).getCreateTime())) {
                    UserSignModel userSignModel = new UserSignModel();
                    userSignModel.setUid(uid);
                    ResponseDTO<String> insert = userSignService.insert(userSignModel);
                    if (insert.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                        userService.addCoin(uid, 2);
                        responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
                    } else {
                        return insert;
                    }
                } else {
                    return responseDTO;
                }
            }
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
        }
        return responseDTO;
    }
}
