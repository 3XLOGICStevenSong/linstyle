/*
 * Copyright (c) 2019. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package cn.com.dbridge.lifecare;

import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;
import cn.com.dbridge.lifecare.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @ClassName:  RemoveImgSchedule
 * @Description: 定时删除垃圾图片
 * @author: 陈健飞
 * @date:   2018年12月6日 下午2:20:25
 *
 * @Copyright: 2018 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@Component
@Slf4j
public class RemoveImgSchedule {
    @Autowired
    private StorageProperties storageProperties;
    @Autowired
    private UserService userService;

    /**
     * 每天零点执行定时任务
     * @throws IOException
     */
    @Scheduled(cron = "0 0 0 * * *")
    public void reportCurrentTime() throws IOException {
        log.info("清理服务器垃圾文件执行...");
        List<String> allImgs = userService.getAllImgs();
        String location = storageProperties.getLocation();
        Files.walkFileTree(Paths.get(location), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(!CollectionUtils.isEmpty(allImgs) && !allImgs.contains(file.getFileName().toString())){
                    log.info("清理垃圾文件:{}",file.getFileName());
                    Files.delete(file);
                }
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
