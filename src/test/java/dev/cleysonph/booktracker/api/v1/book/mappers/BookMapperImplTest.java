package dev.cleysonph.booktracker.api.v1.book.mappers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.core.models.Author;
import dev.cleysonph.booktracker.core.models.Book;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;

@ExtendWith(MockitoExtension.class)
class BookMapperImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookMapperImpl bookMapperImpl;

    @Test
    void toSummaryResponseShouldThrowExceptionWhenBookIsNull() {
        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toSummaryResponse(null));
        assertEquals("book cannot be null", e.getMessage());
    }

    @Test
    void toSummaryResponseShouldThrowExceptionWhenAuthorIdIsNull() {
        var book = Book.builder()
            .id(1L)
            .title("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .build();
        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toSummaryResponse(book));
        assertEquals("book.authorId cannot be null", e.getMessage());
    }

    @Test
    void toSummaryResponseShouldReturnAnBookSummaryResponseWhenBookIsNotNull() {
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
            .coverUrl("http://test.com")
            .author(author)
            .build();
        var expectedBookSummaryResponse = BookSummaryResponse.builder()
            .id(1L)
            .title("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        var actualBookSummaryResponse = bookMapperImpl.toSummaryResponse(book);

        assertEquals(expectedBookSummaryResponse.getId(), actualBookSummaryResponse.getId());
        assertEquals(expectedBookSummaryResponse.getTitle(), actualBookSummaryResponse.getTitle());
        assertEquals(expectedBookSummaryResponse.getPages(), actualBookSummaryResponse.getPages());
        assertEquals(expectedBookSummaryResponse.getIsbn(), actualBookSummaryResponse.getIsbn());
        assertEquals(expectedBookSummaryResponse.getCoverUrl(), actualBookSummaryResponse.getCoverUrl());
        assertEquals(expectedBookSummaryResponse.getAuthorId(), actualBookSummaryResponse.getAuthorId());
    }

    @Test
    void toModelShouldThrowExceptionWhenBookRequestIsNull() {
        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toModel(null));
        assertEquals("bookRequest cannot be null", e.getMessage());
    }

    @Test
    void toModelShouldReturnAnBookWhenBookRequestIsNotNull() {
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();
        var expectedAuthor = Author.builder()
            .id(1L)
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var expectedBook = Book.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .author(expectedAuthor)
            .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(expectedAuthor));

        var actualBook = bookMapperImpl.toModel(bookRequest);
        assertEquals(expectedBook.getTitle(), actualBook.getTitle());
        assertEquals(expectedBook.getSummary(), actualBook.getSummary());
        assertEquals(expectedBook.getPages(), actualBook.getPages());
        assertEquals(expectedBook.getIsbn(), actualBook.getIsbn());
        assertEquals(expectedBook.getCoverUrl(), actualBook.getCoverUrl());
        assertEquals(expectedBook.getAuthor().getId(), actualBook.getAuthor().getId());
        assertEquals(expectedBook.getAuthor().getName(), actualBook.getAuthor().getName());
        assertEquals(expectedBook.getAuthor().getBirthDate(), actualBook.getAuthor().getBirthDate());
        assertEquals(expectedBook.getAuthor().getDeathDate(), actualBook.getAuthor().getDeathDate());
    }

    @Test
    void toModelShouldThrowExceptionWhenAuthorIdIsNull() {
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .build();
        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toModel(bookRequest));
        assertEquals("bookRequest.authorId cannot be null", e.getMessage());
    }

    @Test
    void toModelShouldThrowExceptionWhenAuthorIdDoesNotExists() {
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toModel(bookRequest));
        assertEquals("Author with id 1 not found", e.getMessage());
    }

    @Test
    void toDetailResponseShouldThrowExceptionWhenBookIsNull() {
        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toDetailResponse(null));
        assertEquals("book cannot be null", e.getMessage());
    }

    @Test
    void toDetailResponseShouldThrowExceptionWhenAuthorIsNull() {
        var book = Book.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .build();
        var e = assertThrows(IllegalArgumentException.class, () -> bookMapperImpl.toDetailResponse(book));
        assertEquals("book.author cannot be null", e.getMessage());
    }

    @Test
    void toDetailResponseShouldReturnAnBookDetailResponseWhenBookIsNotNull() {
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
            .coverUrl("http://test.com")
            .author(author)
            .build();
        var expectedBookDetailResponse = BookSummaryResponse.builder()
            .id(1L)
            .title("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();
        var actualBookDetailResponse = bookMapperImpl.toDetailResponse(book);
        assertEquals(expectedBookDetailResponse.getId(), actualBookDetailResponse.getId());
        assertEquals(expectedBookDetailResponse.getTitle(), actualBookDetailResponse.getTitle());
        assertEquals(expectedBookDetailResponse.getPages(), actualBookDetailResponse.getPages());
        assertEquals(expectedBookDetailResponse.getIsbn(), actualBookDetailResponse.getIsbn());
        assertEquals(expectedBookDetailResponse.getCoverUrl(), actualBookDetailResponse.getCoverUrl());
        assertEquals(expectedBookDetailResponse.getAuthorId(), actualBookDetailResponse.getAuthorId());
    }

}
