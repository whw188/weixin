package com.xzh.weixin.web.web;

import com.alibaba.fastjson.JSONObject;
import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.BarrageModel;
import com.xzh.weixin.web.service.BarrageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
    BarrageService barrageService;


    @ResponseBody
    @RequestMapping(value = "/getUid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<Map<String, String>> getUid(String code) {

        ResponseDTO<Map<String, String>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        Map<String, String> map = new HashMap<>();
        map.put("openid", "");
        map.put("unionid", "");

        try {
            String read = getRead(code);
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
            logger.error("getuid error", e);
        }
        return responseDTO;
    }

    public static void main(String[] args) {
        getRead("061GClmr06ocWd1i3hnr09gCmr0GClm1");
    }

    private static String getRead(String code) {

        try {
            StringBuilder url_path = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?appid=wx1b0f3fc231dd589b&secret=dcdf2bf17dcc4d3f1189520cd28fcea0&");
            url_path.append("js_code=").append(code).append("&grant_type=authorization_code");

            URL url = new URL(url_path.toString());
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

            String s = br.readLine();
            br.close();
            logger.info("code:{} ,  result:{}", code, s);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
