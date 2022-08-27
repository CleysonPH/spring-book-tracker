package dev.cleysonph.booktracker.api.v1.book.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.services.BookService;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
public class BookControllerTest {

    private final static String BOOKS_ROUTE = "/api/v1/books";
    private final static String BOOKR_ROUTE = "/api/v1/books/{id}";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookService bookService;

    @Test
    void whenPOSTInBooksRouteIsCalledThenStatusCodeCreatedShouldBeReturned() throws JsonProcessingException, Exception {
        var bookRequest = BookRequest.builder()
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();
        var expectedBookDetailResponse = BookDetailResponse.builder()
            .id(1L)
            .title("Test")
            .summary("Test")
            .pages(100)
            .isbn("1234567890")
            .coverUrl("http://test.com")
            .authorId(1L)
            .build();

        when(bookService.create(bookRequest)).thenReturn(expectedBookDetailResponse);

        mockMvc.perform(post(BOOKS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookRequest)))
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

}
