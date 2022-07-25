package com.group.foodorderapplicationback.service;

import com.group.foodorderapplicationback.Utility.ImageUtility;
import com.group.foodorderapplicationback.model.Image;
import com.group.foodorderapplicationback.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Override
    public Image findByName(String name) {
        return imageRepository.findByName(name);
    }

    @Override
    public void save(MultipartFile multipartFile) throws IOException {
        imageRepository.save(Image.builder()
                .name(multipartFile.getOriginalFilename())
                .type(multipartFile.getContentType())
                .image(ImageUtility.compressImage(multipartFile.getBytes())).build());
    }

}
