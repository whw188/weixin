package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.BarrageMapper;
import com.xzh.weixin.web.dao.model.BarrageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuzh on 2018/2/27 20:14.
 */
@Service
public class BarrageService {

    @Resource
    BarrageMapper barrageMapper;

    public ResponseDTO<String> insert(BarrageModel barrageModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int insert = barrageMapper.insert(barrageModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<List<BarrageModel>> selectByUid(Long rid) {

        ResponseDTO<List<BarrageModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<BarrageModel> barrageMappers = barrageMapper.selectByRid(rid);
            responseDTO.setAttach(barrageMappers);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
