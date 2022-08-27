package dev.cleysonph.booktracker.api.v1.author.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapperImpl;

@Configuration
public class AuthorDiConfigs {

    @Bean
    public AuthorMapper authorMapper() {
        return new AuthorMapperImpl();
    }

}
