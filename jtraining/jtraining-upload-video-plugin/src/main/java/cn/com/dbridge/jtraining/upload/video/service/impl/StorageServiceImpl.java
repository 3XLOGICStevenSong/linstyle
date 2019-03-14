package cn.com.dbridge.jtraining.upload.video.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import cn.com.dbridge.jtraining.framework.exception.StorageFileNotFoundException;
import cn.com.dbridge.jtraining.framework.util.JedisUtil;
import cn.com.dbridge.jtraining.upload.video.param.MultipartFileParam;
import cn.com.dbridge.jtraining.upload.video.service.StorageService;
import cn.com.dbridge.jtraining.upload.video.utils.Constants;
import cn.com.dbridge.jtraining.upload.video.utils.FileMD5Util;

@Service
public class StorageServiceImpl implements StorageService {

    private final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);
    // 保存文件的根目录
    private Path rootPaht;

    //这个必须与前端设定的值一致
    @Value("${breakpoint.upload.chunkSize}")
    private long CHUNK_SIZE;

    @Value("${breakpoint.upload.dir}")
    private String finalDirPath;

    @Autowired
    public StorageServiceImpl(@Value("${breakpoint.upload.dir}") String location) {
        this.rootPaht = Paths.get(location);
    }

    @Override
    public void deleteAll() {
        logger.info("开发初始化清理数据，start");
        FileSystemUtils.deleteRecursively(rootPaht.toFile());
        JedisUtil.delKey(Constants.FILE_UPLOAD_STATUS);
        JedisUtil.delKey(Constants.FILE_MD5_KEY);
        logger.info("开发初始化清理数据，end");
    }

    @Override
    public void deleteFile(String file) {
        logger.info("开发初始化清理数据，start");
        File file_temp = new File(finalDirPath,file);
        FileSystemUtils.deleteRecursively(file_temp);
        JedisUtil.delKey(Constants.FILE_UPLOAD_STATUS);
        JedisUtil.delKey(Constants.FILE_MD5_KEY);
        logger.info("开发初始化清理数据，end");
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("不能读取文件: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("不能读取文件: " + filename, e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootPaht.resolve(filename);
    }
    @Override
    public void init() {
        try {
            Files.createDirectory(rootPaht);
        } catch (FileAlreadyExistsException e) {
            logger.error("文件夹已经存在了，不用再创建。");
        } catch (IOException e) {
            logger.error("初始化root文件夹失败。", e);
        }
    }

    @Override
    public FileVO uploadFileByMappedByteBuffer(MultipartFileParam param)
            throws Exception {
        String fileName = param.getName();

        //上传文件完整目录
        //String uploadDirPath = finalDirPath + param.getMd5();
        String uploadDirPath = finalDirPath;

        //临时文件名
        String tempFileName = fileName + "_tmp";
        //创建目录
        File tmpDir = new File(uploadDirPath);
        File tmpFile = new File(uploadDirPath, tempFileName);
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        //创建文件
        RandomAccessFile tempRaf = new RandomAccessFile(tmpFile, "rw");
        FileChannel fileChannel = tempRaf.getChannel();
        //---写入该分片数据
        //--计算分片position
        long offset = CHUNK_SIZE * param.getChunk();
        //将上传文件转化为字节
        byte[] fileData = param.getFile().getBytes();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, offset, fileData.length);
        mappedByteBuffer.put(fileData);
        // 释放
        FileMD5Util.freedMappedByteBuffer(mappedByteBuffer);
        //关闭Channel
        fileChannel.close();
        tempRaf.close();
        //检查并修改文件上传进度
        boolean isOk = checkAndSetUploadProgress(param, uploadDirPath);
        FileVO fileVO =null;
        if (isOk) {
            //完整上传
            boolean flag = renameFile(tmpFile, fileName);
            String name = fileName
                    .substring(fileName.lastIndexOf("."));
            if (name.equals(".pdf")) {
                File f1 =new File(uploadDirPath,fileName);
                int page = FileUtil.getTextPages(f1);
                fileVO = new FileVO();
                fileVO.setPage(page);
                fileVO.setName(fileName);
            } else {
                File f1 =new File(uploadDirPath,fileName);
                long sencond = FileUtil.getVideoTime(f1);
                fileVO = new FileVO();
                fileVO.setSencond(sencond);
                fileVO.setName(fileName);
            }   
            logger.info("upload complete !!" + flag + " name=" + fileName);  
        }
        return fileVO;
    }

    /**
     * 检查并修改文件上传进度
     *
     * @param param
     * @param uploadDirPath 上传完整目录结构
     * @return
     * @throws IOException
     */
    private boolean checkAndSetUploadProgress(MultipartFileParam param, String uploadDirPath) throws IOException {
        logger.info("======================================================================================");
        String fileName = param.getName();
        //创建上传需要的配置文件[文件名.conf]
        File confFile = new File(uploadDirPath, fileName + ".conf");
        RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");
        //把该分段标记为 true 表示完成
        //logger.info("set part " + param.getChunk() + " complete");
        accessConfFile.setLength(param.getChunks());
        accessConfFile.seek(param.getChunk());
        accessConfFile.write(Byte.MAX_VALUE);

        //completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
        byte[] completeList = FileUtils.readFileToByteArray(confFile);
        byte isComplete = Byte.MAX_VALUE;
        for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
            //与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
            isComplete = (byte) (isComplete & completeList[i]);
          //  logger.info("check part " + i + " complete?:" + completeList[i]);
        }
        accessConfFile.close();
        if (isComplete == Byte.MAX_VALUE) {
            JedisUtil.setHashObject(Constants.FILE_UPLOAD_STATUS, param.getMd5(), "true");
            JedisUtil.setObject(Constants.FILE_MD5_KEY + param.getMd5(), uploadDirPath + "/" + fileName);
            return true;
        } else {
            if (!JedisUtil.hasHashKey(Constants.FILE_UPLOAD_STATUS, param.getMd5())) {
                JedisUtil.setHashObject(Constants.FILE_UPLOAD_STATUS, param.getMd5(), "false");
          }
          if (JedisUtil.exists(Constants.FILE_MD5_KEY + param.getMd5())) {
              JedisUtil.setObject(Constants.FILE_MD5_KEY + param.getMd5(), uploadDirPath + "/" + fileName + ".conf");
          }
            return false;
        }
    }

    /**
     * 文件重命名
     *
     * @param toBeRenamed   将要修改名字的文件
     * @param toFileNewName 新的名字
     * @return
     */
    public boolean renameFile(File toBeRenamed, String toFileNewName) {
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            logger.info("File does not exist: " + toBeRenamed.getName());
            return false;
        }
        String p = toBeRenamed.getParent();
        File newFile = new File(p + File.separatorChar + toFileNewName);
        //修改文件名
        return toBeRenamed.renameTo(newFile);
    }
}
