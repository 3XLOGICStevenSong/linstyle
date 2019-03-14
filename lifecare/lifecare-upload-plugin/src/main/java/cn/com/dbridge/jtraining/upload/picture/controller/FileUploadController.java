package cn.com.dbridge.jtraining.upload.picture.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.com.dbridge.jtraining.upload.picture.service.StorageService;
import cn.com.dbridge.jtraining.upload.picture.service.impl.StorageProperties;
import cn.com.dbridge.lifecare.framework.base.Result;
import cn.com.dbridge.lifecare.framework.vo.FileVO;
@Controller
@RequestMapping(value = "/api/file")
public class FileUploadController {

    private final StorageService storageService;
    @Autowired
    private StorageProperties storageProperties;
    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @PostMapping("/upload")
    @ResponseBody
    public Result<FileVO> handelFileUpload(@RequestParam("file") MultipartFile file) throws Exception {
        return handle(file);
    }

    public Result<FileVO> handle(MultipartFile file) throws Exception {
		String newFileName = storageService.store(file);
        FileVO fileVO = new FileVO();
        String path = storageProperties.getShowPath() + newFileName;
        fileVO.setUrl(path);
        fileVO.setImgName(newFileName);
        return new Result<FileVO>(HttpStatus.OK.value(), "上传图片成功",fileVO);
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
