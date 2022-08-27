package dev.cleysonph.booktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapperImpl;

@Configuration
public class MappersDiConfig {

    @Bean
    public AuthorMapper authorMapper() {
        return new AuthorMapperImpl();
    }

}
