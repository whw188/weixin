package com.xzh.weixin.web.web;

import com.alibaba.fastjson.JSONObject;
import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.OrderModel;
import com.xzh.weixin.web.dao.model.ResourceModel;
import com.xzh.weixin.web.dao.model.UserModel;
import com.xzh.weixin.web.dao.model.UserSignModel;
import com.xzh.weixin.web.service.OrderService;
import com.xzh.weixin.web.service.ResourceService;
import com.xzh.weixin.web.service.UserService;
import com.xzh.weixin.web.service.UserSignService;
import com.xzh.weixin.web.utils.CommonUtils;
import com.xzh.weixin.web.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/user")
public class UserController {


    private final static Logger logger = LoggerFactory.getLogger(UserController.class);


    @Resource
    UserService userService;

    @Resource
    ResourceService resourceService;

    @Resource
    OrderService orderService;

    @Resource
    UserSignService userSignService;


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
    @RequestMapping(value = "/getUserInfo", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<UserModel> getUserInfo(String uid) {

        ResponseDTO<UserModel> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            ResponseDTO<UserModel> listResponseDTO = userService.selectByUid(uid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                ResponseDTO<List<UserSignModel>> signs = userSignService.selectByUid(uid);
                if (signs.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                    List<UserSignModel> attach = signs.getAttach();
                    UserModel userModel = listResponseDTO.getAttach();

                    if (userModel != null) {
                        if (attach == null || attach.size() == 0 || !DateUtils.isToday(attach.get(0).getCreateTime())) {
                            userModel.setSignStatus(1);
                        } else {
                            userModel.setSignStatus(0);
                        }
                    }
                }
            }
            return listResponseDTO;
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
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

    @ResponseBody
    @RequestMapping(value = "/pay", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> insertOrUpdate(String uid, long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {


            ResponseDTO<UserModel> listResponseDTO = userService.selectByUid(uid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                UserModel userModel = listResponseDTO.getAttach();

                Integer coin = userModel.getCoin();

                ResponseDTO<ResourceModel> resourceModelResponseDTO = resourceService.selectById(rid);

                if (resourceModelResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                    ResourceModel attach1 = resourceModelResponseDTO.getAttach();

                    Integer price = attach1.getPrice();
                    if (coin > price) {
                        ResponseDTO<String> responseDTO1 = userService.addCoin(uid, -price);

                        if (responseDTO1.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                            OrderModel orderModel = new OrderModel();
                            orderModel.setPrice(price);
                            orderModel.setUid(uid);
                            orderModel.setRid(rid);
                            ResponseDTO<String> insert = orderService.insert(orderModel);

                            return insert;
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
        }
        return responseDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/checkUserCoin", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<Map<String, Object>> checkUserCoin(String uid, long rid) {

        ResponseDTO<Map<String, Object>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {


            Map<String, Object> result = new HashMap<>();

            responseDTO.setAttach(result);

            result.put("status", "less");
            result.put("rid", String.valueOf(rid));


            ResponseDTO<List<OrderModel>> listResponseDTO1 = orderService.selectByUid(uid, rid);

            if (listResponseDTO1.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {


                List<OrderModel> attach = listResponseDTO1.getAttach();

                if (attach != null && attach.size() > 0) {

                    result.put("status", "paied");
                    result.put("price", 0);

                    responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
                    return responseDTO;
                }
            }


            ResponseDTO<UserModel> listResponseDTO = userService.selectByUid(uid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                UserModel userModel = listResponseDTO.getAttach();

                Integer coin = userModel.getCoin();

                ResponseDTO<ResourceModel> resourceModelResponseDTO = resourceService.selectById(rid);

                if (resourceModelResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                    ResourceModel attach1 = resourceModelResponseDTO.getAttach();

                    Integer price = attach1.getPrice();
                    result.put("price", price);
                    result.put("summary", attach1.getSummary());

                    if (coin > price) {
                        result.put("status", "more");
                    } else {
                        result.put("status", "less");
                    }
                    responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
                    return responseDTO;
                }
            }
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
        }
        return responseDTO;
    }

}
