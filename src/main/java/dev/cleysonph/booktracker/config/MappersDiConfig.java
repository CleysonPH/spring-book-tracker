package dev.cleysonph.booktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapperImpl;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapper;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapperImpl;

@Configuration
public class MappersDiConfig {

    @Bean
    public AuthorMapper authorMapper() {
        return new AuthorMapperImpl();
    }

    @Bean
    public BookMapper bookMapper() {
        return new BookMapperImpl();
    }

}
