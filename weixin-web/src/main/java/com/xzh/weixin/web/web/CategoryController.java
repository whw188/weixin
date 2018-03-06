package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.dao.model.CategoryModel;
import com.xzh.weixin.web.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author wei.wang@fengjr.com
 * @version 2015年12月18日 上午10:30:51
 */

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Resource
    CategoryService categoryService;


    @ResponseBody
    @RequestMapping(value = "/getAll", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<CategoryModel>> getAll() {

        ResponseDTO<List<CategoryModel>> responseDTO = categoryService.selectAll();
        return responseDTO;
    }
}
