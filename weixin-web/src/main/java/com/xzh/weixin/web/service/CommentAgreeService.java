package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.CommentAgreeMapper;
import com.xzh.weixin.web.dao.ShelfMapper;
import com.xzh.weixin.web.dao.model.CommentAgreeModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xuzh on 2018/2/27 20:14.
 */
@Service
public class CommentAgreeService {

    @Resource
    CommentAgreeMapper commentAgreeMapper;

    public ResponseDTO<String> insert(CommentAgreeModel commentAgreeModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int insert = commentAgreeMapper.insert(commentAgreeModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateAgree(String uid, long rid, int agree) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = commentAgreeMapper.updateAgree(uid, rid, agree);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateTread(String uid, long rid, int shelf) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateShelf = commentAgreeMapper.updateShelf(uid, rid, shelf);
            if (updateShelf > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<CommentAgreeModel> selectByUidAndCmid(String uid, long cmid) {

        ResponseDTO<CommentAgreeModel> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            CommentAgreeModel commentAgreeModel = commentAgreeMapper.selectByUidAndCmid(uid, cmid);
            responseDTO.setAttach(commentAgreeModel);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<CommentAgreeModel>> selectByUid(String uid) {

        ResponseDTO<List<CommentAgreeModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<CommentAgreeModel> commentAgreeModels = commentAgreeMapper.selectByUid(uid);
            responseDTO.setAttach(commentAgreeModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
