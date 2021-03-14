package com.lx.povertyalleviation.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lx
 * @version 1.0
 * @date 2021-3-14 17:44
 */
public class FileUploadUtil {
    /**
     * 上传文件方法
     * @return文件存储的相对路径
     */
    public static String uploadFile(MultipartFile upload, String parentName, HttpServletRequest request){
        //获取文件的原始名字
        String originalFilename = upload.getOriginalFilename();
        //获取项目在服务器上的路径
        //磁盘路径\target\video_manager\WEB-INF/
        String basePath = request.getServletContext().getRealPath("/WEB-INF/");
        //获取存放文件的父路径   video/或者img/
        String parentPath = parentName+"/";
        File parentFile = new File(basePath+parentPath);
        if(!parentFile.exists()){
            //如果文件夹不存在，直接创建
            // mkdir:/video/video1/ 没有video，直接video1会失败
            //mkdirs：复合目录我都可以创建
            parentFile.mkdirs();
        }
        Date date = new Date();
        SimpleDateFormat sdformat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
        //通过日期生成文件名(不会重复)
        String fileName = sdformat.format(date);
        //文件上传
        //磁盘路径\target\video_manager\WEB-INF/video/20200519113957893.mp4
        try {
            upload.transferTo(new File(basePath+parentPath+fileName+getSuffix(originalFilename)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // video/20200519113957893.mp4
        return parentPath+fileName+getSuffix(originalFilename);
    }
    /**
     * 根据文件名称发挥文件后缀
     * xxx.mp4
     */
    public static String getSuffix(String originalFilename){
        //获取最后一个.的位置
        int lastIndexOf = originalFilename.lastIndexOf(".");
        //在原始名字上截取后缀
        return originalFilename.substring(lastIndexOf);
    }
}
