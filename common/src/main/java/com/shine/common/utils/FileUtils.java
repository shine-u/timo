package com.shine.common.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传.
 *
 * @author shineYu
 */
@Slf4j
public class FileUtils {
    
    /**
     * 上传图片到阿里云.
     * @return sss
     * @throws Exception
     * @param file 文件.
     * @param request request.
     * @RequestParam(value = "file", required = false) MultipartFile file
     * 
     */
    
    public static String uploadFile(MultipartFile file, HttpServletRequest request) {

        String contentType = file.getContentType();
        if (!("image/jpeg").equals(contentType) && !("image/png").equals(contentType)) {
            return null;
        }
        String savedDir = request.getContextPath() + "/bbs/image/";
        File savedFileDir = new File(savedDir);
        //如果文件夹不存在则创建
        if(!savedFileDir .exists()){
            savedFileDir .mkdirs();
        }
        String endpoint = "oss-cn-shanghai.aliyuncs.com/";
        String aliFilePath = "idcard/";
        String aliBucketName = "hh-order";
        String accessKeyId = "LTAISMNVG8sR3wQ6";
        String accessKeySecret = "ZQWckDXnl0ZgSJdL3WGCh0iCboMghT";
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUID.randomUUID() + suffixName;
        String tmpFilePath = savedDir + "/" + fileName;
        File newFile = new File(tmpFilePath);
        try {
            file.transferTo(newFile);
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ossClient.putObject(aliBucketName, aliFilePath + fileName, newFile);
            ossClient.shutdown();
            newFile.delete();
            String imgurl = "https://" + aliBucketName + "." + endpoint + aliFilePath + fileName;
            return imgurl;
        } catch (IOException e) {
            log.error(e.toString());
            throw new ServiceException("上传图片失败");
        }

    }

}
