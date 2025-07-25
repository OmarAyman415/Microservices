package com.example.user_service.security.configs;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor

public class AppConfig {


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}