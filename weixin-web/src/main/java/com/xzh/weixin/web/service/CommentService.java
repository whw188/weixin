package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.CommentMapper;
import com.xzh.weixin.web.dao.UserMapper;
import com.xzh.weixin.web.dao.model.CommentModel;
import com.xzh.weixin.web.dao.model.UserModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuzh on 2018/2/27 20:14.
 */
@Service
public class CommentService {


    @Resource
    CommentMapper commentMapper;


    public ResponseDTO<String> delTread(long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = commentMapper.delTread(rid);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateTread(long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = commentMapper.updateTread(rid);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateAgree(long cmid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = commentMapper.updateAgree(cmid);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<String> delAgree(long cmid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = commentMapper.delAgree(cmid);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<String> insert(CommentModel commentModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int insert = commentMapper.insert(commentModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<List<CommentModel>> selectByRid(long rid) {

        ResponseDTO<List<CommentModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<CommentModel> userModels = commentMapper.selectByRid(rid);
            responseDTO.setAttach(userModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<CommentModel> selectByCmid(long cmid) {

        ResponseDTO<CommentModel> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            CommentModel userModels = commentMapper.selectByCmid(cmid);
            responseDTO.setAttach(userModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
