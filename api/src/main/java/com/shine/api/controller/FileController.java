package com.shine.api.controller;

import com.shine.api.common.utils.ServerResponse;
import com.shine.api.servcice.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件控制器.
 *
 * @author shineYu
 * @Date 2019/5/28 14:12
 */
@Slf4j
@RestController
@RequestMapping("/api/file")
public class FileController {

    /**
     * 文件服务.
     */
    @Autowired
    FileService fileService;

    /**
     * 文件上传.
     *
     * @param file    文件
     * @param request request
     * @return ServerResponse 通用返回对象
     */
    @PostMapping("/upload")
    public ServerResponse upload(MultipartFile file, HttpServletRequest request) {
        return fileService.upload(file, request);
    }

}
