package dev.cleysonph.booktracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorService;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorServiceImpl;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapper;
import dev.cleysonph.booktracker.api.v1.book.services.BookService;
import dev.cleysonph.booktracker.api.v1.book.services.BookServiceImpl;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;
import dev.cleysonph.booktracker.core.repositories.BookRepository;

@Configuration
public class ServicesDiConfig {

    @Bean
    public AuthorService authorService(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        return new AuthorServiceImpl(authorMapper, authorRepository);
    }

    @Bean
    public BookService bookService(BookMapper bookMapper, BookRepository bookRepository) {
        return new BookServiceImpl(bookMapper, bookRepository);
    }

}
