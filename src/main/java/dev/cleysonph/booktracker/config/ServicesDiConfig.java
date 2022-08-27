package dev.cleysonph.booktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorService;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorServiceImpl;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;

@Configuration
public class ServicesDiConfig {

    @Bean
    public AuthorService authorService(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        return new AuthorServiceImpl(authorMapper, authorRepository);
    }

}
