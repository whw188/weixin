package com.xzh.weixin.web.web;

import com.alibaba.fastjson.JSONObject;
import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.UserModel;
import com.xzh.weixin.web.service.UserService;
import com.xzh.weixin.web.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/sign")
public class SignController {


    private final static Logger logger = LoggerFactory.getLogger(SignController.class);

    @Resource
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/getUid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<Map<String, String>> getUid(String code) {

        ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        Map<String, String> map = new HashMap<>();
        map.put("openid", "");
        map.put("unionid", "");

        try {
            String read = CommonUtils.getRead(code);
            if (read == null) {
                return responseDTO;
            }
            JSONObject jsonObject = JSONObject.parseObject(read);

            if (jsonObject.containsKey("errcode")) {
                responseDTO.setMsg(read);
            } else {
                String openid = jsonObject.getString("openid");
                String unionid = jsonObject.getString("unionid");
                String session_key = jsonObject.getString("session_key");

                if (openid != null) {
                    map.put("openid", openid);
                }
                if (unionid != null) {
                    map.put("unionid", unionid);
                }
                responseDTO.setAttach(map);
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("getUid error", e);
        }
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/insertOrUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> insertOrUpdate(String uid, String head, String nick) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            ResponseDTO<String> updateUser = userService.updateUser(uid, head, nick);

            if (updateUser.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                return updateUser;
            } else {
                UserModel userModel = new UserModel();
                userModel.setUid(uid);
                userModel.setHead(head);
                userModel.setNick(nick);
                return userService.insert(userModel);
            }
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
        }
        return responseDTO;
    }


}
