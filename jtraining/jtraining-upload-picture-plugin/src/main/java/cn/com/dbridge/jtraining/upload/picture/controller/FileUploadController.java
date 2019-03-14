package cn.com.dbridge.jtraining.upload.picture.controller;

import java.io.File;
import java.nio.file.Path;

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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import cn.com.dbridge.jtraining.framework.base.Result;
import cn.com.dbridge.jtraining.framework.util.FileUtil;
import cn.com.dbridge.jtraining.framework.vo.FileVO;
import cn.com.dbridge.jtraining.upload.picture.service.StorageService;
@Controller
@RequestMapping(value = "/file")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }
    @PostMapping("/upload")
    @ResponseBody
    public Result<FileVO> handelFileUpload(
            @RequestParam("file") MultipartFile file) throws Exception {
        return handle(file);
    }

    public Result<FileVO> handle(MultipartFile file) throws Exception {
		String newFileName = storageService.store(file);
        String name = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        if (name.equals(".pdf")) {
			Path path = storageService.load(newFileName);
            File f1 = path.toFile();
            int page = FileUtil.getTextPages(f1);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile",
                            path.getFileName().toString())
                    .build().toString();
            FileVO fileVO = new FileVO();
            fileVO.setPage(page);
            fileVO.setUrl(url);
            return new Result<FileVO>(HttpStatus.OK.value(), "ファイルがアップロードされた!",
                    fileVO);
        } else if (name.equals(".mp4")) {
			Path path = storageService.load(newFileName);
            File f1 = path.toFile();
            long sencond = FileUtil.getVideoTime(f1);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile",
                            path.getFileName().toString())
                    .build().toString();
            FileVO fileVO = new FileVO();
            fileVO.setSencond(sencond);
            fileVO.setUrl(url);
            return new Result<FileVO>(HttpStatus.OK.value(), "ファイルがアップロードされた!",
                    fileVO);
        } else {
			Path path = storageService.load(newFileName);
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile",
                            path.getFileName().toString())
                    .build().toString();
            FileVO fileVO = new FileVO();
            fileVO.setUrl(url);
            return new Result<FileVO>(HttpStatus.OK.value(), "ファイルがアップロードされた!",
                    fileVO);
        }
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
