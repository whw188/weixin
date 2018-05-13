package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.RecommondMapper;
import com.xzh.weixin.web.dao.model.RecomondModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuzh on 2018/2/27 20:14.
 */
@Service
public class RecommondService {

    @Resource
    RecommondMapper recommondMapper;

    public ResponseDTO<String> insert(RecomondModel recomondModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int insert = recommondMapper.insert(recomondModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> update(String uid, String search) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = recommondMapper.update(uid, search);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<List<RecomondModel>> selectByUid(String uid) {

        ResponseDTO<List<RecomondModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<RecomondModel> recomondModels = recommondMapper.selectByUid(uid);
            responseDTO.setAttach(recomondModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
