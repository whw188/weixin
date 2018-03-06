package com.xzh.weixin.web.service;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.CategoryMapper;
import com.xzh.weixin.web.dao.model.CategoryModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangwei on 2018/2/27 20:14.
 */
@Service
public class CategoryService {

    @Resource
    CategoryMapper categoryMapper;


    public ResponseDTO<List<CategoryModel>> selectAll() {

        ResponseDTO<List<CategoryModel>> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            List<CategoryModel> resourceModels = categoryMapper.selectAll();
            responseDTO.setAttach(resourceModels);
            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseDTO.setReturnCode(ReturnCode.ACTIVE_EXCEPTION);
        }
        return responseDTO;
    }
}
