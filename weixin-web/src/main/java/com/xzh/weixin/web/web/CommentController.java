package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.CommentModel;
import com.xzh.weixin.web.dao.model.UserModel;
import com.xzh.weixin.web.service.CommentService;
import com.xzh.weixin.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/comment")
public class CommentController {

    private final static Logger logger = LoggerFactory.getLogger(CommentController.class);

    @Resource
    UserService userService;
    @Resource
    CommentService commentService;


    @ResponseBody
    @RequestMapping(value = "/get", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<Map>> get(long rid) {

        ResponseDTO<List<Map>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {

            ResponseDTO<List<CommentModel>> listResponseDTO = commentService.selectByRid(rid);
            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                List<Map> result = new ArrayList<>();

                List<CommentModel> attach = listResponseDTO.getAttach();
                for (CommentModel commentModel : attach) {

                    HashMap<String, String> map = new HashMap<>();

                    String fromNick = "";
                    String fromHead = "";
                    try {
                        ResponseDTO<UserModel> listResponseDTO1 = userService.selectByUid(commentModel.getFromUid());
                        UserModel userModel = listResponseDTO1.getAttach();
                        fromNick = userModel.getNick();
                        fromHead = userModel.getHead();
                    } catch (Exception e) {
                        logger.error("userService.selectByUid uid : {} ", commentModel.getFromUid(), e);
                    }
                    map.put("fromId", commentModel.getFromUid());
                    map.put("fromNick", fromNick);
                    map.put("fromHead", fromHead);

                    String toNick = "";
                    String toHead = "";
                    try {
                        ResponseDTO<UserModel> listResponseDTO1 = userService.selectByUid(commentModel.getToUid());
                        UserModel userModel = listResponseDTO1.getAttach();
                        toNick = userModel.getNick();
                        toHead = userModel.getHead();
                    } catch (Exception e) {
                        logger.error("userService.selectByUid uid : {} ", commentModel.getToUid(), e);
                    }
                    map.put("toId", commentModel.getToUid());
                    map.put("toNick", toNick);
                    map.put("toHead", toHead);

                    map.put("content", commentModel.getContent());
                    map.put("rid", String.valueOf(rid));
                    result.add(map);
                }
                responseDTO.setAttach(result);
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
        }
        return responseDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/set", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> set(String fromUid, String toUid, String content, long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {

            CommentModel commentModel = new CommentModel();

            commentModel.setContent(content);
            commentModel.setRid(rid);
            commentModel.setFromUid(fromUid);
            commentModel.setToUid(toUid);

            ResponseDTO<String> insert = commentService.insert(commentModel);

            return insert;
        } catch (Exception e) {
            logger.error("insertOrUpdate error", e);
        }
        return responseDTO;
    }


}
