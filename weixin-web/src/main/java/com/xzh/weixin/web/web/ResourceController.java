package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.ResourceModel;
import com.xzh.weixin.web.dao.model.ShelfModel;
import com.xzh.weixin.web.service.ResourceService;
import com.xzh.weixin.web.service.ShelfService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wei.wang@fengjr.com
 * @version 2015年12月18日 上午10:30:51
 */

@Controller
@RequestMapping("/resource")
public class ResourceController {

    @Resource
    ResourceService resourceService;

    @Resource
    ShelfService shelfService;

    @ResponseBody
    @RequestMapping(value = "/recommond", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> recommond(String uid) {

        ResponseDTO<List<ResourceModel>> recommond = resourceService.recommond(uid);
        return recommond;
    }


    @ResponseBody
    @RequestMapping(value = "/addAgree", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> addAgree(long rid, String uid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResponseDTO<ShelfModel> listResponseDTO = shelfService.selectByUidAndRid(uid, rid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                ShelfModel attach = listResponseDTO.getAttach();
                if (attach != null) {
                    if (attach.getAgree() != 1) {
                        shelfService.updateAgree(uid, rid, 1);
                        resourceService.updateAgree(rid);
                    }
                } else {
                    ShelfModel shelfModel = new ShelfModel();
                    shelfModel.setUid(uid);
                    shelfModel.setRid(rid);
                    shelfModel.setAgree(1);
                    shelfService.insert(shelfModel);
                    resourceService.updateAgree(rid);
                }
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/delAgree", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> delAgree(long rid, String uid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResponseDTO<ShelfModel> listResponseDTO = shelfService.selectByUidAndRid(uid, rid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                ShelfModel attach = listResponseDTO.getAttach();
                if (attach != null && attach.getAgree() != 0) {
                    shelfService.updateAgree(uid, rid, 0);
                }
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/updateView", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateView(long rid) {

        ResponseDTO<String> responseDTO = resourceService.updateView(rid);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/addShelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateShelf(long rid, String uid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResponseDTO<ShelfModel> listResponseDTO = shelfService.selectByUidAndRid(uid, rid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                ShelfModel attach = listResponseDTO.getAttach();
                if (attach != null) {
                    if (attach.getShelf() != 1) {
                        shelfService.updateShelf(uid, rid, 1);
                        resourceService.updateShelf(rid);
                    }
                } else {
                    ShelfModel shelfModel = new ShelfModel();
                    shelfModel.setUid(uid);
                    shelfModel.setRid(rid);
                    shelfModel.setShelf(1);
                    shelfService.insert(shelfModel);
                    resourceService.updateShelf(rid);
                }
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/delShelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> delShelf(long rid, String uid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResponseDTO<ShelfModel> listResponseDTO = shelfService.selectByUidAndRid(uid, rid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                ShelfModel attach = listResponseDTO.getAttach();
                if (attach != null && attach.getShelf() != 0) {
                    shelfService.updateShelf(uid, rid, 0);
                }
                responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/selectByIdAndUid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<Map<String, Object>> selectByIdAndUid(long rid, String uid) {

        ResponseDTO<Map<String, Object>> result = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);

        try {
            ResponseDTO<ResourceModel> responseDTO = resourceService.selectById(rid);

            if (responseDTO.getCode() != ReturnCode.ACTIVE_SUCCESS.code()) {
                result.setReturnCode(responseDTO.nowReturnCode());
                return result;
            }
            Map<String, Object> map = new HashMap<>(3);
            Integer agree = 0;
            Integer shelf = 0;
            ResponseDTO<ShelfModel> listResponseDTO = shelfService.selectByUidAndRid(uid, rid);

            if (listResponseDTO.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {

                ShelfModel shelfModel = listResponseDTO.getAttach();
                if (shelfModel != null) {
                    agree = shelfModel.getAgree();
                    shelf = shelfModel.getShelf();
                }
            }
            map.put("isAgree", agree);
            map.put("isShelf", shelf);
            map.put("data", responseDTO.getAttach());

            result.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            result.setAttach(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByCid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> selectByCid(long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = resourceService.selectByCid(cid);
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
    public ResponseDTO<List<ResourceModel>> selectByTitleAndCid(String keyWord, long cid) {

        ResponseDTO<List<ResourceModel>> responseDTO = resourceService.selectByTitleAndCid(keyWord, cid);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/updateStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateStatus(String uid, long rid, int status) {

        ResponseDTO<String> result = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        if ("oDA605CZh1lIRB_JSDhm9RB7vtsc".equals(uid)) {
            result = resourceService.updateStatus(rid, status);
        }
        return result;
    }


    @ResponseBody
    @RequestMapping(value = "/selectStatus", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ResourceModel>> selectStatus(String uid, int status) {

        ResponseDTO<List<ResourceModel>> result = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        if ("oDA605CZh1lIRB_JSDhm9RB7vtsc".equals(uid)) {
            result = resourceService.selectByStatus(status);
        }
        return result;
    }

}
