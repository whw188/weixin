package com.xzh.weixin.web.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Author: wei.wang
 * @Date: 2018/5/17 13:54
 */
public class CommonUtils {


    private final static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    public static String mkFilePath(String savePath, String fileName) {
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

    public static String getRead(String code) {

        try {
            StringBuilder url_path = new StringBuilder("https://api.weixin.qq.com/sns/jscode2session?appid=wx1b0f3fc231dd589b&secret=dcdf2bf17dcc4d3f1189520cd28fcea0&");
            url_path.append("js_code=").append(code).append("&grant_type=authorization_code");

            URL url = new URL(url_path.toString());
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));

            String s = br.readLine();
            br.close();
            logger.info("code:{} ,  result:{}", code, s);
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
