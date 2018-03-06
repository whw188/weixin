package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.dao.model.ResourceModel;
import com.xzh.weixin.web.service.ResourceService;
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
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    ResourceService resourceService;

    @ResponseBody
    @RequestMapping(value = "/recommond", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> recommond(String uid) {

        ResponseDTO<List<ResourceModel>> recommond = resourceService.recommond(uid);
        return recommond;
    }


    @ResponseBody
    @RequestMapping(value = "/updateAgree", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateAgree(long id) {

        ResponseDTO<String> responseDTO = resourceService.updateAgree(id);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/updateView", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateView(long id) {

        ResponseDTO<String> responseDTO = resourceService.updateView(id);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/updateShelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateShelf(long id) {

        ResponseDTO<String> responseDTO = resourceService.updateShelf(id);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectById", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<ResourceModel> selectById(long id) {

        ResponseDTO<ResourceModel> responseDTO = resourceService.selectById(id);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByCid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> selectByCid(long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = resourceService.selectByCategoryId(cid);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByUid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> selectByUid(String uid) {

        ResponseDTO<List<ResourceModel>> responseDTO = resourceService.selectByUid(uid);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByTitleOrAuthor", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> selectByTitleOrAuthor(String keyWord) {

        ResponseDTO<List<ResourceModel>> responseDTO = resourceService.selectByTitleOrAuthor(keyWord);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByTitleAndCid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> selectByTitleAndCid(String title, long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = resourceService.selectByTitleAndCid(title, cid);
        return responseDTO;
    }
}
