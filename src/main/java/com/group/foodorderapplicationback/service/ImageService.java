package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image findByName(String name);
    void save(MultipartFile multipartFile) throws IOException;
    Resource load(String filename);
}
