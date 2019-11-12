package com.shine.api.repository;

import com.shine.api.common.entity.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 文件Dao.
 *
 * @author shineYu
 * @Date 2019/5/28 14:48
 */
public interface FileRepository extends JpaRepository<FileInfo,Integer> {

    /**
     * 通过文件名获取文件.
     *
     * @param fileName 文件名
     * @return FileInfo 结果
     */
    FileInfo getByName(String fileName);

}
