package dev.cleysonph.booktracker.api.v1.book.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapper;
import dev.cleysonph.booktracker.core.models.Author;
import dev.cleysonph.booktracker.core.models.Book;
import dev.cleysonph.booktracker.core.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createShouldReturnABookDetailResponseWhenABookRequestIsGiven() {
        var author = Author.builder()
            .id(1L)
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var book = Book.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("https://example.com")
            .author(author)
            .build();
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("https://test.com")
            .authorId(1L)
            .build();
        var expectedBookResponse = BookDetailResponse.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("https://example.com")
            .authorId(1L)
            .build();

        when(bookMapper.toModel(bookRequest)).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(bookMapper.toDetailResponse(book)).thenReturn(expectedBookResponse);

        var actualBookResponse = bookService.create(bookRequest);

        assertEquals(expectedBookResponse.getId(), actualBookResponse.getId());
        assertEquals(expectedBookResponse.getTitle(), actualBookResponse.getTitle());
        assertEquals(expectedBookResponse.getSummary(), actualBookResponse.getSummary());
        assertEquals(expectedBookResponse.getPages(), actualBookResponse.getPages());
        assertEquals(expectedBookResponse.getIsbn(), actualBookResponse.getIsbn());
        assertEquals(expectedBookResponse.getCoverUrl(), actualBookResponse.getCoverUrl());
        assertEquals(expectedBookResponse.getAuthorId(), actualBookResponse.getAuthorId());
    }

}
