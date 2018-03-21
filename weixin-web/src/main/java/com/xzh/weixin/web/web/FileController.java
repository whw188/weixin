package com.xzh.weixin.web.web;

import com.xzh.weixin.web.common.ResponseDTO;
import com.xzh.weixin.web.common.ReturnCode;
import com.xzh.weixin.web.dao.model.ResourceModel;
import com.xzh.weixin.web.service.ResourceService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


/**
 * @author wei.wang@fengjr.com
 * @version 2015年12月18日 上午10:30:51
 */

@Controller
@RequestMapping("/file")
public class FileController {


    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    private final static String BASE_DIR = "/export/upload/";
    private final static String UTF8 = "UTF-8";
    private static File file = null;

    static {
        try {
            file = new File(BASE_DIR);
            if (!file.exists() && !file.isDirectory()) {
                System.out.println("目录或文件不存在！");
                logger.error("目录或文件不存在！");
                file.mkdir();
            }
        } catch (Exception e) {
            logger.error("初始化 保存目录失败 ... ", e);
        }
    }

    @Resource
    ResourceService resourceService;

    @RequestMapping(value = "/uploadPage", method = {RequestMethod.GET, RequestMethod.POST})
    public String page() {
        return "upload";
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseDTO<String> upload(HttpServletRequest request, @QueryParam(value = "userName") String userName) {

        ResponseDTO<String> responseDTO = new ResponseDTO<>(ReturnCode.ACTIVE_FAILURE);
        List<FileItem> list = null;
        try {
            // 判断提交上来的数据是否是上传表单的数据
            if (!ServletFileUpload.isMultipartContent(request)) {
                //按照传统方式获取数据
                responseDTO.setMsg("请设置表单提交类型为 MultipartContent");
                return responseDTO;
            }
            // 使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            // 此时 已经生成临时文件   每个 表单项  对应一个文件
            list = getServletFileUpload().parseRequest(request);
            HashMap<String, String> params = new HashMap<>();
            List<FileItem> files = new ArrayList<>();
            for (FileItem item : list) {
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString(UTF8);
                    params.put(name, value);
                } else {
                    files.add(item);
                }
            }

            String uid = params.get("uid");
            String title = params.get("title");
            String type = params.get("type");
            String author = params.get("author");
            String summary = params.get("summary");
            if (StringUtils.isBlank(uid) ||
                    StringUtils.isBlank(title) ||
                    StringUtils.isBlank(type) ||
                    StringUtils.isBlank(author) ||
                    StringUtils.isBlank(summary)) {
                responseDTO.setReturnCode(ReturnCode.ERROR_PARAMS);
                return responseDTO;
            }
            long categoryId;
            try {
                categoryId = Long.parseLong(params.get("categoryId"));
            } catch (Exception e) {
                logger.error("params check exception :", e);
                responseDTO.setReturnCode(ReturnCode.ERROR_PARAMS);
                return responseDTO;
            }

            ResourceModel resourceModel = new ResourceModel();

            resourceModel.setUid(uid);
            resourceModel.setTitle(title);
            resourceModel.setSummary(summary);
            resourceModel.setAuthor(author);
            resourceModel.setType(type);
            resourceModel.setCid(categoryId);

            String saveFileId = null;
            File file = null;
            String savePathStr = null;
            String fileName = null;
            try {
                FileItem item = files.get(0);
                fileName = item.getName();
                logger.info(fileName);
                if (fileName != null && !"".equals(fileName.trim())) {
                    //得到文件保存的名称
                    String fileId = UUID.randomUUID().toString();
                    //得到文件保存的路径
                    savePathStr = mkFilePath(BASE_DIR, fileId);

                    logger.info("保存路径为:" + savePathStr);
                    file = new File(savePathStr + File.separator + fileId);
                    FileUtils.copyInputStreamToFile(item.getInputStream(), file);
                    logger.info("保存成功:" + fileId);
                    saveFileId = fileId;
                }
            } catch (Exception e) {
                if (file != null && file.exists()) {
                    FileUtils.forceDelete(file);
                    FileUtils.deleteDirectory(new File(savePathStr));
                }
                logger.error("保存文件失败:" + fileName, e);
            }

            if (saveFileId != null) {
                resourceModel.setFileId(saveFileId);
                resourceModel.setFileName(fileName);
                ResponseDTO insert = resourceService.insert(resourceModel);

                if (insert.getCode() == ReturnCode.ACTIVE_SUCCESS.code()) {
                    responseDTO.setReturnCode(ReturnCode.ACTIVE_SUCCESS);
                    logger.info("上传成功:" + saveFileId);
                } else {
                    FileUtils.forceDelete(file);
                    FileUtils.deleteDirectory(new File(savePathStr));
                    responseDTO.setMsg("存储信息失败");
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            responseDTO.setMsg("单个文件超出最大值！！！");
            logger.error("单个文件超出最大值！！！", e);
        } catch (FileUploadBase.SizeLimitExceededException e) {
            logger.error("上传文件的总的大小超出限制的最大值！！！", e);
            responseDTO.setMsg("上传文件的总的大小超出限制的最大值！！！");
        } catch (FileUploadException e) {
            logger.error("文件上传失败", e);
            responseDTO.setMsg("文件上传失败");
        } catch (Exception e) {
            logger.error("upload error ", e);
        } finally {
            //删除处理文件上传时生成的临时文件
            if (list != null) {
                for (FileItem fileItem : list) {
                    fileItem.delete();
                }
            }
        }
        return responseDTO;
    }

    @ResponseBody
    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
    public void download(HttpServletResponse response, long rid) {

        ResponseDTO<ResourceModel> resourceModelResponseDTO = resourceService.selectById(rid);
        response.setCharacterEncoding(UTF8);
        response.setContentType("multipart/form-data");

        if (resourceModelResponseDTO.getCode() != ReturnCode.ACTIVE_SUCCESS.code()) {
            String msg = "  未查询到对应资源：" + rid;
            logger.info(msg);
            try {
                response.sendError(404, "fid no exist");
            } catch (Exception e) {
                logger.error(" response.sendError exception ", e);
            }
            return;
        }

        ResourceModel resourceModel = resourceModelResponseDTO.getAttach();

        String fileId = resourceModel.getFileId();
        String fileName = resourceModel.getFileName();
        OutputStream os = null;
        FileInputStream in = null;
        try {
            String path = mkFilePath(BASE_DIR, fileId);
            File file = new File(path + File.separator + fileId);

            if (!file.exists()) {
                String msg = fileId + "资源已被删除！！";
                logger.info(msg);
                response.sendError(404, "file no exist");
                return;
            }
            //设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1"));

            in = new FileInputStream(path + File.separator + fileId);
            FileChannel readChannel = in.getChannel();
            os = response.getOutputStream();

            ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
            while (true) {
                buffer.clear();
                int len = readChannel.read(buffer);
                if (len < 0) {
                    break;
                }
                buffer.flip();
                os.write(buffer.array());
            }
            os.flush();
            try {
                resourceService.updateView(rid);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ServletFileUpload getServletFileUpload() {

        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
        diskFileItemFactory.setSizeThreshold(1024 * 1024 * 10);
        //设置上传时生成的临时文件的保存目录
        diskFileItemFactory.setRepository(file);
        //2、创建一个文件上传解析器
        ServletFileUpload fileUpload = new ServletFileUpload(diskFileItemFactory);
        //解决上传文件名的中文乱码
        fileUpload.setHeaderEncoding(UTF8);
//        //监听文件上传进度
//        fileUpload.setProgressListener(new ProgressListener() {
//            @Override
//            public void update(long pBytesRead, long pContentLength, int arg2) {
//                logger.info("文件大小为：" + pContentLength + " ,当前已处理：" + pBytesRead + " ,处理进度：" + pBytesRead * 100 / pContentLength + "%");
//            }
//        });
        //设置上传单个文件的大小的最大值，目前是设置为10G
        long fileSize = 1024L * 1024L * 1024L * 10L;
        fileUpload.setFileSizeMax(fileSize);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10g
        fileUpload.setSizeMax(fileSize * 1);

        return fileUpload;
    }

    private String mkFilePath(String savePath, String fileName) {
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode = fileName.hashCode();
        int dir1 = hashcode & 0xf;
        int dir2 = (hashcode & 0xf0) >> 4;
        //构造新的保存目录
        String dir = savePath + dir1 + "/" + dir2;
        //File既可以代表文件也可以代表目录
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }
}
