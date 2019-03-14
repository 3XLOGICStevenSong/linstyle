package cn.com.dbridge.jtraining.upload.picture.service.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import cn.com.dbridge.jtraining.upload.picture.service.StorageService;
import cn.com.dbridge.lifecare.framework.exception.StorageException;
import cn.com.dbridge.lifecare.framework.exception.StorageFileNotFoundException;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;
    
    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
    
    @Override
	public String store(MultipartFile file) {
		String fileName = String.valueOf(System.currentTimeMillis() + new Random().nextInt(999));
		//+ file.getOriginalFilename()
        try {
            if (file.isEmpty()) {
				throw new StorageException("存储的文件为空 " + fileName);
            }
			Files.copy(file.getInputStream(),
					this.rootLocation.resolve(fileName));
        } catch (IOException e) {
			throw new StorageException("存储文件失败 " + fileName, e);
        }
		return fileName;
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(path -> this.rootLocation.relativize(path));
        } catch (IOException e) {
            throw new StorageException("读取文件失败", e);
        }

    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
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
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    /**
     * 初始化上传目录
     */
    @Override
    public void init() {
        try {
            if( !Files.exists(rootLocation, LinkOption.values())) {
                Files.createDirectory(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("初始化目录失败", e);
        }
    }
}
