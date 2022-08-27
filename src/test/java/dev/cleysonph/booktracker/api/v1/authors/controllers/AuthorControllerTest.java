package dev.cleysonph.booktracker.api.v1.authors.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.cleysonph.booktracker.api.v1.author.controllers.AuthorController;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

    private final static String AUTHORS_ROUTE = "/api/v1/authors";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthorService authorService;

    @Test
    void whenPOSTIsCalledThenStatusCodeCreatedShouldBeReturned() throws Exception {
        var authorRequest = AuthorRequest.builder()
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var expectedAuthorResponse = AuthorResponse.builder()
            .id(1L)
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();

        when(authorService.create(authorRequest)).thenReturn(expectedAuthorResponse);

        mockMvc.perform(MockMvcRequestBuilders.post(AUTHORS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorRequest)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(expectedAuthorResponse.getId().intValue())))
            .andExpect(jsonPath("$.name", is(expectedAuthorResponse.getName())))
            .andExpect(jsonPath("$.birthDate", is(expectedAuthorResponse.getBirthDate().toString())))
            .andExpect(jsonPath("$.deathDate", is(expectedAuthorResponse.getDeathDate().toString())));
    }

    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenStatusCodeBadRequestShouldBeReturned() throws Exception {
        var authorRequest = AuthorRequest.builder()
            .name("Test")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(AUTHORS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorRequest)))
            .andExpect(status().isBadRequest());
    }

}
