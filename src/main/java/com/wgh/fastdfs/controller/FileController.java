package com.wgh.fastdfs.controller;

import com.wgh.fastdfs.config.FastDFSClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author WGH
 * @Date 2022-07-28 23:27
 * @Description
 */

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FastDFSClientUtil fastDFSClientUtil;

    @Value("${fileServerUrl}")
    private String fileServerUrl;

    @PostMapping("/upload")
    public String uploadFile(MultipartFile file) throws Exception {
        try {
            if (file == null) {
                throw new Exception("文件不存在!");
            }
            String fileId = fastDFSClientUtil.uploadFile(file);
            return fileServerUrl.concat(fileId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "文件上传失败";
    }

}
