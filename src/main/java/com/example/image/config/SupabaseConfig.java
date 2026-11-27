package com.example.image.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SupabaseConfig {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.secret-key}")
    private String supabaseSecretKey;

    @Value("${supabase.bucket}")
    private String supabaseBucket;

    public String getSupabaseUrl() {
        return supabaseUrl;
    }

    public String getSupabaseSecretKey() {
        return supabaseSecretKey;
    }

    public String getSupabaseBucket() {
        return supabaseBucket;
    }
}