package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.model.Image;
import com.group.foodorderapplicationback.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final FileStorageService fileStorageService;

    @Override
    public Image findByName(String name) {
        return imageRepository.findByName(name);
    }

    @Override
    public Image findByResourceName(String uuid) {
        return imageRepository.findByResourceName(uuid);
    }

    @Override
    public Image save(MultipartFile multipartFile){

        String resourceLocation = fileStorageService.save(multipartFile, "Images", true);

        return imageRepository.save(Image.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .resourceName(resourceLocation)
                .build());
    }

    @Override
    public Resource load(String filename) {
        return fileStorageService.load(filename, "Images");
    }

}
