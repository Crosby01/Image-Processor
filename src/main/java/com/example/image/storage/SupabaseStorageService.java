package com.example.image.storage;

import com.example.image.config.SupabaseConfig;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.UUID;

@Service
public class SupabaseStorageService {

    private final SupabaseConfig supabaseConfig;
    private final RestClient restClient;

    public SupabaseStorageService(SupabaseConfig supabaseConfig) {
        this.supabaseConfig = supabaseConfig;
        this.restClient = RestClient.create();
    }

    public String upload(MultipartFile file) throws IOException {

        String storageKey = UUID.randomUUID().toString();

        String uploadUrl = UriComponentsBuilder
                .fromUriString(supabaseConfig.getSupabaseUrl())
                .path("/storage/v1/object/")
                .pathSegment(supabaseConfig.getSupabaseBucket())
                .pathSegment(storageKey)
                .build()
                .encode()
                .toUriString();

        restClient.post()
                .uri(uploadUrl)
                .header("apikey", supabaseConfig.getSupabaseSecretKey())
                .header("Authorization",
                        "Bearer " + supabaseConfig.getSupabaseSecretKey())
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(file.getBytes())
                .retrieve()
                .toBodilessEntity();

        return storageKey;
    }
}