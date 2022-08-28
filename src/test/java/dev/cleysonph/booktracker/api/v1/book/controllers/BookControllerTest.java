package dev.cleysonph.booktracker.api.v1.book.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.api.v1.book.services.BookService;
import dev.cleysonph.booktracker.core.exceptions.BookNotFoundException;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    private final static String BOOKS_ROUTE = "/api/v1/books";
    private final static String BOOK_ROUTE = "/api/v1/books/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @MockBean
    private AuthorRepository authorRepository;

    @Test
    void whenPOSTInBooksRouteIsCalledThenStatusCodeCreatedShouldBeReturned() throws JsonProcessingException, Exception {
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("8550804606")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();
        var expectedBookDetailResponse = BookDetailResponse.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("8550804606")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        when(authorRepository.existsById(1L)).thenReturn(true);
        when(bookService.create(bookRequest)).thenReturn(expectedBookDetailResponse);

        mockMvc.perform(post(BOOKS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)))
            .andDo(print())
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(expectedBookDetailResponse.getId().intValue())))
            .andExpect(jsonPath("$.title", is(expectedBookDetailResponse.getTitle())))
            .andExpect(jsonPath("$.summary", is(expectedBookDetailResponse.getSummary())))
            .andExpect(jsonPath("$.pages", is(expectedBookDetailResponse.getPages())))
            .andExpect(jsonPath("$.isbn", is(expectedBookDetailResponse.getIsbn())))
            .andExpect(jsonPath("$.coverUrl", is(expectedBookDetailResponse.getCoverUrl())))
            .andExpect(jsonPath("$.authorId", is(expectedBookDetailResponse.getAuthorId().intValue())));
    }

    @Test
    void whenPOSTInBooksRoutesWithInvalidDataThenStatusCodeBadRequestShouldBeReturned() throws JsonProcessingException, Exception {
        var bookRequest = BookRequest.builder().build();

        mockMvc.perform(post(BOOKS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETInBooksRouteIsCalledThenStatusCodeOkShouldBeReturned() throws Exception {
        var expectedBooksSummaryResponse = List.of(
            BookSummaryResponse.builder()
                .id(1L)
                .title("Test")
                .pages(100)
                .isbn("8550804606")
                .coverUrl("http://test.com")
                .authorId(1L)
                .build()
        );

        when(bookService.findAll()).thenReturn(expectedBooksSummaryResponse);

        mockMvc.perform(get(BOOKS_ROUTE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(expectedBooksSummaryResponse.get(0).getId().intValue())))
            .andExpect(jsonPath("$[0].title", is(expectedBooksSummaryResponse.get(0).getTitle())))
            .andExpect(jsonPath("$[0].pages", is(expectedBooksSummaryResponse.get(0).getPages())))
            .andExpect(jsonPath("$[0].isbn", is(expectedBooksSummaryResponse.get(0).getIsbn())))
            .andExpect(jsonPath("$[0].coverUrl", is(expectedBooksSummaryResponse.get(0).getCoverUrl())))
            .andExpect(jsonPath("$[0].authorId", is(expectedBooksSummaryResponse.get(0).getAuthorId().intValue())));
    }

    @Test
    void whenGETInBookRouteIsCalledWithInvalidBookIdThenStatusCodeNotFoundShouldBeReturned() throws Exception {
        when(bookService.findById(1L)).thenThrow(new BookNotFoundException(1L));

        mockMvc.perform(get(BOOK_ROUTE, 1L))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message", is("Book with id 1 not found")));
    }

    @Test
    void whenGETInBookRouteIsCalledWithValidBookIdThenStatusCodeOkShouldBeReturned() throws Exception {
        var expectedBookDetailResponse = BookDetailResponse.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("8550804606")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        when(bookService.findById(1L)).thenReturn(expectedBookDetailResponse);

        mockMvc.perform(get(BOOK_ROUTE, 1L))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(expectedBookDetailResponse.getId().intValue())))
            .andExpect(jsonPath("$.title", is(expectedBookDetailResponse.getTitle())))
            .andExpect(jsonPath("$.summary", is(expectedBookDetailResponse.getSummary())))
            .andExpect(jsonPath("$.pages", is(expectedBookDetailResponse.getPages())))
            .andExpect(jsonPath("$.isbn", is(expectedBookDetailResponse.getIsbn())))
            .andExpect(jsonPath("$.coverUrl", is(expectedBookDetailResponse.getCoverUrl())))
            .andExpect(jsonPath("$.authorId", is(expectedBookDetailResponse.getAuthorId().intValue())));
    }

    @Test
    void whenDELETEInBookRouteIsCalledWithInvalidBookIdThenStatusCodeNotFoundShouldBeReturned() throws Exception {
        doThrow(new BookNotFoundException(1L)).when(bookService).deleteById(1L);

        mockMvc.perform(delete(BOOK_ROUTE, 1L))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message", is("Book with id 1 not found")));
    }

    @Test
    void whenDELETEInBookRouteIsCalledWithValidBookIdThenStatusCodeNoContentShouldBeReturned() throws Exception {
        doNothing().when(bookService).deleteById(1L);

        mockMvc.perform(delete(BOOK_ROUTE, 1L))
            .andExpect(status().isNoContent());
    }

    @Test
    void whenPUTInBookRouteIsCalledWithInvalidBookIdThenStatusCodeNotFoundShouldBeReturned() throws JsonProcessingException, Exception {
        var bookRequest = BookRequest.builder().build();

        when(bookService.updateById(1L, bookRequest)).thenThrow(new BookNotFoundException(1L));

        mockMvc.perform(put(BOOK_ROUTE, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)))
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.message", is("Book with id 1 not found")));
    }

    @Test
    void whenPUTInBookRouteIsCalledWithInvalidDataThenStatusCodeBadRequestShouldBeReturned() throws JsonProcessingException, Exception {
        var bookRequest = BookRequest.builder().build();

        mockMvc.perform(put(BOOK_ROUTE, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.message", is("Validation failed")));
    }

    @Test
    void whenPUTInBookRouteIsCalledWithValidDataThenStatusCodeOkShouldBeReturned() throws Exception {
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("8550804606")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        var expectedBookDetailResponse = BookDetailResponse.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("8550804606")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        when(authorRepository.existsById(1L)).thenReturn(true);
        when(bookService.updateById(1L, bookRequest)).thenReturn(expectedBookDetailResponse);

        mockMvc.perform(put(BOOK_ROUTE, 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(expectedBookDetailResponse.getId().intValue())))
            .andExpect(jsonPath("$.title", is(expectedBookDetailResponse.getTitle())))
            .andExpect(jsonPath("$.summary", is(expectedBookDetailResponse.getSummary())))
            .andExpect(jsonPath("$.pages", is(expectedBookDetailResponse.getPages())))
            .andExpect(jsonPath("$.isbn", is(expectedBookDetailResponse.getIsbn())))
            .andExpect(jsonPath("$.coverUrl", is(expectedBookDetailResponse.getCoverUrl())))
            .andExpect(jsonPath("$.authorId", is(expectedBookDetailResponse.getAuthorId().intValue())));
    }

}
