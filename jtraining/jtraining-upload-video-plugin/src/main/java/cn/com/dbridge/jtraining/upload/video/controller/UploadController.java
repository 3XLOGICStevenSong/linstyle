package cn.com.dbridge.jtraining.upload.video.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.dbridge.jtraining.framework.util.JedisUtil;
import cn.com.dbridge.jtraining.upload.video.param.MultipartFileParam;
import cn.com.dbridge.jtraining.upload.video.service.StorageService;
import cn.com.dbridge.jtraining.upload.video.service.impl.FileUtil;
import cn.com.dbridge.jtraining.upload.video.service.impl.FileVO;
import cn.com.dbridge.jtraining.upload.video.utils.Constants;
import cn.com.dbridge.jtraining.upload.video.vo.ResultStatus;
import cn.com.dbridge.jtraining.upload.video.vo.ResultVo;

/**
 * @ClassName:  UploadController
 * @Description:上传视频
 * @author: 陈健飞
 * @date:   2019年1月9日 下午1:51:45
 * 
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *	注意：本内容仅限于必捷必信息技术有限公司   内部传阅，禁止外泄以及用于其他的商业目
 */
@RestController
@RequestMapping(value = "/upload")
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private StorageService storageService;

    @Value("${breakpoint.upload.dir}")
    private String finalDirPath;
    /**
     * 秒传判断，断点判断
     *
     * @return
     * @throws Exception 
     */
    @PostMapping(value = "/checkFileMd5")
    public ResultVo<Object> checkFileMd5(String md5) throws Exception {
        
        //获取上传文件状态
        Object processingObj = JedisUtil.getHashObject(Constants.FILE_UPLOAD_STATUS, md5);
        if (processingObj == null) {
            //服务器不存在该文件，直接返回
            return new ResultVo<>(ResultStatus.NO_HAVE);
        }
        String processingStr = processingObj.toString();
        boolean processing = Boolean.parseBoolean(processingStr);
        //String value = stringRedisTemplate.opsForValue().get(Constants.FILE_MD5_KEY + md5);
        String value = null;
        String[] a=null;
        try {
            Set<String> key = JedisUtil
                    .getAllHashKey(Constants.FILE_UPLOAD_STATUS);
            a = key.toArray(new String[key.size()]);
                    value=(String) JedisUtil
                .getObject(Constants.FILE_MD5_KEY + md5);

        } finally {
            if (value == null) {
                JedisUtil.delHashKey(Constants.FILE_UPLOAD_STATUS, a);
                return new ResultVo<>(ResultStatus.NO_HAVE);
            }
        }
        FileVO fileVO = null;
        if (processing) {
            String name = value.substring(value.lastIndexOf("."));
            String fileName = value.substring(value.lastIndexOf("/") + 1);
            if (name.equals(".pdf")) {
                File f1 = new File(value);
                int page = FileUtil.getTextPages(f1);
                fileVO = new FileVO();
                fileVO.setPage(page);
                fileVO.setName(fileName);
            } else {
                File f1 = new File(value);
                long sencond = FileUtil.getVideoTime(f1);
                fileVO = new FileVO();
                fileVO.setSencond(sencond);
                fileVO.setName(fileName);
            }
            //服务器存在该文件，并且为完整文件
            return new ResultVo<>(ResultStatus.IS_HAVE, fileVO);
        } else {
            //服务器存在，但是为非完整文件
            File confFile = new File(value);
            byte[] completeList = FileUtils.readFileToByteArray(confFile);
            List<String> missChunkList = new LinkedList<>();
            for (int i = 0; i < completeList.length; i++) {
                if (completeList[i] != Byte.MAX_VALUE) {
                    missChunkList.add(i + "");
                }
            }
            return new ResultVo<>(ResultStatus.ING_HAVE, missChunkList);
        }
    }

    /**
     * 上传文件
     *
     * @param param
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/fileUpload")
    public ResultVo<FileVO> fileUpload(MultipartFileParam param,
            HttpServletRequest request) throws Exception {
        long start = System.currentTimeMillis();
        logger.info("fileUpload:{}",param);
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        FileVO fileVO = null;
        if (isMultipart) {
            logger.info("上传文件start。");
            try {
                fileVO = new FileVO();
                fileVO = storageService
                        .uploadFileByMappedByteBuffer(param);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("文件上传失败。{}", param.toString());
            }
            logger.info("上传文件end。");
        }
        logger.info("上传方法执行时间:{}",System.currentTimeMillis()-start);
        return new ResultVo<FileVO>(ResultStatus.OK, "ファイルがアップロードされた!",
                fileVO);
    }

    /**
     * 删除文件
     * @param fileName 文件名称
     * @return
     */
    @PostMapping(value = "/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam("fileName") String fileName) {
        logger.info("deleteFile:{}",fileName);
        if(StringUtils.isNoneEmpty(fileName)){
            storageService.deleteFile(fileName);
        }
        return ResponseEntity.ok().body("删除成功。");
    }

    @GetMapping("/files/show/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> showFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().body(file);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment;filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
