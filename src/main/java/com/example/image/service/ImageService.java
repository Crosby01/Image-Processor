package com.example.image.service;

import com.example.image.entity.Image;
import com.example.image.entity.User;
import com.example.image.repository.ImageRepository;
import com.example.image.repository.UserRepository;
import com.example.image.storage.SupabaseStorageService;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final SupabaseStorageService storageService;

    public ImageService(
            ImageRepository imageRepository,
            UserRepository userRepository,
            SupabaseStorageService storageService) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    public Image uploadImage(MultipartFile file, String userEmail)
            throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("Image file cannot be empty");
        }

        if (file.getContentType() == null ||
                !file.getContentType().startsWith("image/")) {
            throw new IllegalArgumentException("Only image files are allowed");
        }

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        String storageKey = storageService.upload(file);

        Image image = new Image();
        image.setOriginalFilename(file.getOriginalFilename());
        image.setStorageKey(storageKey);
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setUser(user);

        return imageRepository.save(image);
    }
}