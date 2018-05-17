package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.UserSignMapper;
import com.xzh.weixin.web.dao.model.UserSignModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuzh on 2018/2/27 20:14.
 */
@Service
public class UserSignService {


    @Resource
    UserSignMapper userSignMapper;

    public ResponseDTO<String> insert(UserSignModel userSignModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int insert = userSignMapper.insert(userSignModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<List<UserSignModel>> selectByUid(String uid) {

        ResponseDTO<List<UserSignModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<UserSignModel> userSignModels = userSignMapper.selectByUid(uid);

            responseDTO.setAttach(userSignModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
