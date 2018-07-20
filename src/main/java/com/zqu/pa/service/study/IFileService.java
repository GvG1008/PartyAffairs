package com.zqu.pa.service.study;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件管理业务层接口
 * @author Lee
 *
 */
public interface IFileService {
    String upload(MultipartFile file, String path);
}
