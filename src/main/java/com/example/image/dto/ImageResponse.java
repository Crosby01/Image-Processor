package com.example.image.dto;

import java.time.LocalDateTime;

public class ImageResponse {

    private Long id;
    private String originalFilename;
    private String contentType;
    private Long size;
    private LocalDateTime createdAt;

    public ImageResponse(
            Long id,
            String originalFilename,
            String contentType,
            Long size,
            LocalDateTime createdAt) {
        this.id = id;
        this.originalFilename = originalFilename;
        this.contentType = contentType;
        this.size = size;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getSize() {
        return size;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}