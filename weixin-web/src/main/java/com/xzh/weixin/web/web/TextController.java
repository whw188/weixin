package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.ResourceModel;
import com.xzh.weixin.web.service.ResourceService;
import com.xzh.weixin.web.service.UserService;
import com.xzh.weixin.web.utils.CommonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;
import java.util.UUID;

/**
 * @author xuzh
 * @version 2018年3月21日19:59:16
 */

@Controller
@RequestMapping("/txt")
public class TextController {


    private final static Logger logger = LoggerFactory.getLogger(TextController.class);
    private final static String BASE_DIR = "/export/upload/";
    private final static String UTF8 = "UTF-8";

    @Resource
    ResourceService resourceService;

    @Resource
    UserService userService;


    @RequestMapping(value = "/uploadPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String page() {
        return "uploadTxt";
    }


    @ResponseBody
    @RequestMapping(value = "/uploadTxt", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> uploadTxt(String uid, String title, String type, String author, String summary, long categoryId, int price, String content) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            if (StringUtils.isBlank(uid) ||
                    StringUtils.isBlank(title) ||
                    StringUtils.isBlank(author) ||
                    StringUtils.isBlank(content) ||
                    StringUtils.isBlank(summary)) {

                responseDTO.setReturnCode(ReturnCode.ERROR_PARAMS);
                return responseDTO;
            }
            if (StringUtils.isBlank(type)) {
                type = "txt";
            }
            ResourceModel resourceModel = new ResourceModel();

            resourceModel.setUid(uid);
            resourceModel.setTitle(title);
            resourceModel.setSummary(summary);
            resourceModel.setAuthor(author);
            resourceModel.setType(type);
            resourceModel.setCid(categoryId);
            resourceModel.setPrice(price);

            if ("ask".equals(type)) {
                resourceModel.setStatus(1);
            } else {
                resourceModel.setStatus(0);
            }


            File file = null;
            String savePathStr = null;
            String fileId = UUID.randomUUID().toString();
            boolean flag = false;
            try {
                savePathStr = CommonUtils.mkFilePath(BASE_DIR, fileId);
                logger.info("保存路径为:" + savePathStr);
                file = new File(savePathStr + File.separator + fileId);
                FileUtils.writeByteArrayToFile(file, content.getBytes(UTF8));
                logger.info("保存成功:" + file.getPath());
                flag = true;
            } catch (Exception e) {
                if (file != null && file.exists()) {
                    FileUtils.forceDelete(file);
                    FileUtils.deleteDirectory(new File(savePathStr));
                }
                logger.error("保存文件失败:", e);
            }

            if (flag) {
                resourceModel.setFileId(fileId);
                resourceModel.setFileName(fileId);
                ResponseDTO insert = resourceService.insert(resourceModel);

                if (insert.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                    responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
                    logger.info("上传成功:" + file.getPath());

                    userService.addCoin(uid, 5);
                    logger.info("奖励积分：" + 5);

                } else {
                    FileUtils.forceDelete(file);
                    FileUtils.deleteDirectory(new File(savePathStr));
                    responseDTO.setMsg("存储信息失败");
                }
            }
        } catch (Throwable e) {
            logger.error("getuid error", e);
        }
        return responseDTO;
    }


    @ResponseBody
    @RequestMapping(value = "/downloadTxt", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> downloadTxt(long rid) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        try {
            ResponseDTO<ResourceModel> resourceModelResponseDTO = resourceService.downloadRid(rid);

            if (resourceModelResponseDTO.getCode() != ReturnCode.ACTIVE_SUCCESS.code()) {
                String msg = "  未查询到对应资源：" + rid;
                logger.info(msg);
                return responseDTO;
            }

            ResourceModel resourceModel = resourceModelResponseDTO.getAttach();
            String fileId = resourceModel.getFileId();
            String path = CommonUtils.mkFilePath(BASE_DIR, fileId);
            FileInputStream fileInputStream = new FileInputStream(path + File.separator + fileId);

            int available = fileInputStream.available();
            byte[] bytes = new byte[available];
            fileInputStream.read(bytes);
            String s = new String(bytes, UTF8);

            responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
            responseDTO.setAttach(s);
        } catch (Throwable e) {
            logger.error("getuid error", e);
        }
        return responseDTO;
    }
}
