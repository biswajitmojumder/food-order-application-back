package com.group.foodorderapplicationback.controller;

import com.group.foodorderapplicationback.model.Image;
import com.group.foodorderapplicationback.service.ImageService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            Image image = imageService.save(file);
            return ResponseEntity.ok()
                .body(new ImageUploadResponse("Uploaded the file successfully: " + file.getOriginalFilename(), image));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                .body(new ImageUploadResponse("Couldn't upload the file: " + file.getOriginalFilename(), null));
        }
    }

    @GetMapping("/image/get")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@RequestParam String name) {
        Resource file = imageService.load(name);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @Getter
    @AllArgsConstructor
    public class ImageUploadResponse {
        private String message;
        private Image image;
    }
}
