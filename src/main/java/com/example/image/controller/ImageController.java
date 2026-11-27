package com.example.image.controller;

import com.example.image.dto.ImageResponse;
import com.example.image.entity.Image;
import com.example.image.service.ImageService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ImageResponse> uploadImage(
            @RequestParam("file") MultipartFile file,
            Authentication authentication) throws IOException {

        Image image = imageService.uploadImage(
                file,
                authentication.getName());

        ImageResponse response = new ImageResponse(
                image.getId(),
                image.getOriginalFilename(),
                image.getContentType(),
                image.getSize(),
                image.getCreatedAt());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}