package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.dao.model.BarrageModel;
import com.xzh.weixin.web.service.BarrageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/shelf")
public class BarrageController {

    @Resource
    BarrageService barrageService;


    @ResponseBody
    @RequestMapping(value = "/selectBarrageByRid", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<List<BarrageModel>> selectByUid(Long rid) {

        ResponseDTO<List<BarrageModel>> responseDTO = barrageService.selectByUid(rid);
        return responseDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> insert(Long rid, String uid, String text, String color, Integer time) {

        BarrageModel barrageModel = new BarrageModel();
        barrageModel.setRid(rid);
        barrageModel.setUid(uid);
        barrageModel.setText(text);
        barrageModel.setColor(color);
        barrageModel.setTime(time);
        ResponseDTO<String> insert = barrageService.insert(barrageModel);
        return insert;
    }
}
