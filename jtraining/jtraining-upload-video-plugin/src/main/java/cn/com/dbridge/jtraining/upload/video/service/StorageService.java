package cn.com.dbridge.jtraining.upload.video.service;

import java.io.IOException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;

import cn.com.dbridge.jtraining.upload.video.param.MultipartFileParam;
import cn.com.dbridge.jtraining.upload.video.service.impl.FileVO;

public interface StorageService {

    /**
     * 删除全部数据
     */
    void deleteAll();

    /**
     * 删除单个文件
     * @param file
     */
     void deleteFile(String file);

    Resource loadAsResource(String filename);
    Path load(String filename);
    /**
     * 初始化方法
     */
    void init();
    /**
     * 上传文件方法2
     * 处理文件分块，基于MappedByteBuffer来实现文件的保存
     *
     * @param param
     * @throws IOException
     * @throws Exception 
     */
    FileVO uploadFileByMappedByteBuffer(MultipartFileParam param)
            throws IOException, Exception;

}
