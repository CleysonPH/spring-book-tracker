package dev.cleysonph.booktracker.api.v1.book.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapper;
import dev.cleysonph.booktracker.core.exceptions.BookNotFoundException;
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

    @Test
    void findAllShouldReturnAListOfBookSummaryResponse() {
        var author = Author.builder()
            .id(1L)
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var books = List.of(
            Book.builder()
                .id(1L)
                .title("Test")
                .summary("Test")
                .pages(100)
                .isbn("1234567890")
                .coverUrl("https://example.com")
                .author(author)
                .build()
        );
        var expectedBooksSummaryResponse = List.of(
            BookSummaryResponse.builder()
                .id(1L)
                .title("Test")
                .pages(100)
                .isbn("1234567890")
                .coverUrl("https://example.com")
                .authorId(1L)
                .build()
        );

        when(bookRepository.findAll()).thenReturn(books);
        when(bookMapper.toSummaryResponse(books.get(0))).thenReturn(expectedBooksSummaryResponse.get(0));

        var actualBooksSummaryResponse = bookService.findAll();

        assertEquals(expectedBooksSummaryResponse.size(), actualBooksSummaryResponse.size());
        assertEquals(expectedBooksSummaryResponse.get(0).getId(), actualBooksSummaryResponse.get(0).getId());
        assertEquals(expectedBooksSummaryResponse.get(0).getTitle(), actualBooksSummaryResponse.get(0).getTitle());
        assertEquals(expectedBooksSummaryResponse.get(0).getPages(), actualBooksSummaryResponse.get(0).getPages());
        assertEquals(expectedBooksSummaryResponse.get(0).getIsbn(), actualBooksSummaryResponse.get(0).getIsbn());
        assertEquals(expectedBooksSummaryResponse.get(0).getCoverUrl(), actualBooksSummaryResponse.get(0).getCoverUrl());
        assertEquals(expectedBooksSummaryResponse.get(0).getAuthorId(), actualBooksSummaryResponse.get(0).getAuthorId());
    }

    @Test
    void findByIdShouldThrowAnExceptionWhenAnInvalidIdIsGiven() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(BookNotFoundException.class, () -> bookService.findById(1L));
        assertEquals("Book with id 1 not found", exception.getMessage());
    }

    @Test
    void findByIdShouldReturnBookDetailResponseWhenAValidIdIsGiven() {
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
        var expectedBookDetailResponse = BookDetailResponse.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("https://example.com")
            .authorId(1L)
            .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDetailResponse(book)).thenReturn(expectedBookDetailResponse);

        var actualBookDetailResponse = bookService.findById(1L);

        assertEquals(expectedBookDetailResponse.getId(), actualBookDetailResponse.getId());
        assertEquals(expectedBookDetailResponse.getTitle(), actualBookDetailResponse.getTitle());
        assertEquals(expectedBookDetailResponse.getSummary(), actualBookDetailResponse.getSummary());
        assertEquals(expectedBookDetailResponse.getPages(), actualBookDetailResponse.getPages());
        assertEquals(expectedBookDetailResponse.getIsbn(), actualBookDetailResponse.getIsbn());
        assertEquals(expectedBookDetailResponse.getCoverUrl(), actualBookDetailResponse.getCoverUrl());
        assertEquals(expectedBookDetailResponse.getAuthorId(), actualBookDetailResponse.getAuthorId());
    }

    @Test
    void deleteByIdShouldThrowAnExceptionWhenAnInvalidIdIsGiven() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());
        var exception = assertThrows(BookNotFoundException.class, () -> bookService.deleteById(1L));
        assertEquals("Book with id 1 not found", exception.getMessage());
    }

    @Test
    void deleteByIdShouldDeleteBookWhenAValidIdIsGiven() {
        var book = Book.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("https://example.com")
            .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        bookService.deleteById(1L);
        verify(bookRepository, times(1)).delete(book);
    }

}
