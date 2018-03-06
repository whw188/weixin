package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.ResourceMapper;
import com.xzh.weixin.web.dao.model.ResourceModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangwei on 2018/2/27 20:14.
 */
@Service
public class ResourceService {

    @Resource
    ResourceMapper resourceMapper;


    public ResponseDTO<List<ResourceModel>> recommond(String uid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> recommond = resourceMapper.recommond();
            responseDTO.setAttach(recommond);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }


    public ResponseDTO<String> insert(ResourceModel resourceModel) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            int insert = resourceMapper.insert(resourceModel);
            if (insert > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateAgree(long id) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateAgree = resourceMapper.updateAgree(id);
            if (updateAgree > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateView(long id) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateView = resourceMapper.updateView(id);
            if (updateView > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<String> updateShelf(long id) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            int updateShelf = resourceMapper.updateShelf(id);
            if (updateShelf > 0) {
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<ResourceModel> selectById(long id) {

        ResponseDTO<ResourceModel> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResourceModel resourceModel = resourceMapper.selectById(id);
            if (resourceModel != null) {
                responseDTO.setAttach(resourceModel);
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByCategoryId(long categoryId) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByCategoryId(categoryId);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByTitleOrAuthor(String keyWord) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByTitleOrAuthor(keyWord);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByTitleAndCid(String title, long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByTitleAndCid(title, cid);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }

    public ResponseDTO<List<ResourceModel>> selectByUid(String uid) {

        ResponseDTO<List<ResourceModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<ResourceModel> resourceModels = resourceMapper.selectByUid(uid);
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
