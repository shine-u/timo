package com.shine.api.common.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 文件详情.
 *
 * @author shineYu
 * @Date 2019/5/27 17:30
 */
@Data
@Entity
@Table(name = "sys_file")
public class FileInfo {

    /**
     * 文件编号.
     */
    @Id
    private Long id;
    /**
     * 文件名称.
     */
    private String name;
    /**
     * 文件类型.
     */
    private String type;
    /**
     * 文件大小.
     */
    private Long size;
    /**
     * 文件路径.
     */
    private String path;
    /**
     * md5.
     */
    private String md5;
    /**
     * sha1.
     */
    private String sha1;
    /**
     * 文件url.
     */
    private String url;
    /**
     * 状态.
     */
    private Integer status;

}
