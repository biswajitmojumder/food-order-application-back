package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.Utility.ImageUtility;
import com.group.foodorderapplicationback.model.Image;
import com.group.foodorderapplicationback.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        log.info("DEBUG :: HERE");
        imageService.save(file);
        return ResponseEntity.ok().body(new ImageUploadResponse("Upload successful!"));
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {

        Image image = imageService.findByName(name);

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(image.getType()))
                .body(ImageUtility.decompressImage(image.getImage()));
    }

    @Getter
    @AllArgsConstructor
    public class ImageUploadResponse {
        private String message;
    }
}
