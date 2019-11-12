package com.shine.api.servcice;

import com.google.common.collect.Lists;
import com.shine.api.common.entity.FileInfo;
import com.shine.api.common.utils.Const;
import com.shine.api.common.utils.FTPUtil;
import com.shine.api.common.utils.ServerResponse;
import com.shine.api.repository.FileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件服务.
 *
 * @author shineYu
 * @Date 2019/5/28 15:54
 */
@Slf4j
@Service
public class FileService {

    /**
     * 文件Dao.
     */
    @Autowired
    FileRepository fileRepository;

    /**
     * 文件上传.
     *
     * @param file 文件
     * @param path 保存路径
     * @return
     */
    private String upload(MultipartFile file, String path) {
        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf("."));
        String uploadFileName = UUID.randomUUID().toString() + fileExtensionName;
        log.info("\n开始上传文件，文件名为：{}，上传的路径：{}，新文件名：{}",
                fileName, path, uploadFileName);
        File fileDir = new File(path);
        //判断是否存在目录
        if (!fileDir.exists()) {
            //设置可写权限
            fileDir.setWritable(true);
            //创建目录（-R）
            fileDir.mkdirs();
        }

        File finalFile = new File(path, uploadFileName);

        try {
            //上传到目录下
            file.transferTo(finalFile);
            //上传ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(finalFile));
            //上传ftp后删除文件
            finalFile.delete();
        } catch (IOException e) {
            log.error("上传文件异常", e);
            throw new RuntimeException("上传文件异常");
        }

        return finalFile.getName();

    }

    /**
     * 文件上传.
     *
     * @param file    文件
     * @param request request
     * @return ServerResponse 通用返回对象
     */
    public ServerResponse upload(MultipartFile file, HttpServletRequest request) {
        //获取项目上下文路径
        String path = request.getSession().getServletContext().getRealPath(Const.PATH_FILE);
        //上传
        String targetFileName = null;
        try {
            targetFileName = upload(file, path);
        } catch (Exception e) {
            log.error(e.toString());
            throw new RuntimeException("文件上传失败");
        }
        String url = Const.FILE_SERVER_PATH + targetFileName;

        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(System.currentTimeMillis());
        fileInfo.setName(file.getOriginalFilename());
        fileInfo.setPath(Const.FILE_PATH + targetFileName);
        fileInfo.setUrl(url);
        fileInfo.setSize(file.getSize());
        fileInfo.setStatus(Const.StatusEnum.SUCCESS.getCode());
        fileInfo.setType(targetFileName.substring(targetFileName.lastIndexOf(".")));

        FileInfo result = fileRepository.save(fileInfo);

        return result != null ? ServerResponse.success("入库成功", result)
                : ServerResponse.errorMessage("入库异常");
    }

}
