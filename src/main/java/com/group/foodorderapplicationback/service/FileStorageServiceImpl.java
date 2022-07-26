package com.group.foodorderapplicationback.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
@Slf4j
public class FileStorageServiceImpl implements FileStorageService {
    private String rootPath = "Uploads";
    private final Path root = Paths.get(rootPath);

    @Override
    public void init() {
        try {
            if(Files.exists(root)) {
                return;
            }
            Files.createDirectory(root);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public String save(MultipartFile file, String folderName, boolean randomFileName) {

        String fileName;

        if(randomFileName) {
            fileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(file.getOriginalFilename());
        }
        else {
            fileName = file.getOriginalFilename();
        }

        try {
            String newPathString = rootPath + "/" + folderName;
            Path newPath = Paths.get(newPathString);

            if(!Files.exists(newPath)) {
                Files.createDirectory(newPath);
            }

            Files.copy(file.getInputStream(), newPath.resolve(fileName));
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }

        return fileName;
    }

    @Override
    public Resource load(String filename, String folderName) {
        try {
            String newPathString = folderName + "/" + filename;

            log.info("DEBUG :: PATH: " + newPathString);

            Path file = root.resolve(newPathString);

            log.info("DEBUG :: URI :: " + file.toUri());

            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Couldn't read the file!");
            }
        } catch (MalformedURLException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

}
