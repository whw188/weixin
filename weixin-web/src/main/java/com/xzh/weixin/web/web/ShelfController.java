package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.dao.model.ShelfModel;
import com.xzh.weixin.web.service.ShelfService;
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
@RequestMapping("/shelf")
public class ShelfController {

    @Resource
    ShelfService shelfService;

    @ResponseBody
    @RequestMapping(value = "/updateAgree", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateAgree(String uid, String rid) {

        ResponseDTO<String> responseDTO = shelfService.updateAgree(uid, rid, 1);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/updateShelf", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> updateShelf(String uid, String rid) {

        ResponseDTO<String> responseDTO = shelfService.updateShelf(uid, rid, 1);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByUidAndRid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ShelfModel>> selectByUidAndRid(String uid, String rid) {

        ResponseDTO<List<ShelfModel>> responseDTO = shelfService.selectByUidAndRid(uid, rid);
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/selectByUid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<ShelfModel>> selectByUid(String uid) {

        ResponseDTO<List<ShelfModel>> responseDTO = shelfService.selectByUid(uid);
        return responseDTO;
    }
}
