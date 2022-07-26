package com.group.foodorderapplicationback.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void init();
    String save(MultipartFile file, String folderName, boolean randomFileName);
    Resource load(String filename, String folderName);
}
