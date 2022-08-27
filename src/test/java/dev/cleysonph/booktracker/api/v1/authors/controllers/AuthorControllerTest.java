package dev.cleysonph.booktracker.api.v1.authors.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

import java.time.LocalDate;
import java.util.List;

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
    void whenPOSTInAuthorsRouteIsCalledThenStatusCodeCreatedShouldBeReturned() throws Exception {
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
    void whenPOSTInAuthorsRouteIsCalledWithoutRequiredFieldThenStatusCodeBadRequestShouldBeReturned() throws Exception {
        var authorRequest = AuthorRequest.builder()
            .name("Test")
            .build();

        mockMvc.perform(MockMvcRequestBuilders.post(AUTHORS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(authorRequest)))
            .andExpect(status().isBadRequest());
    }

    @Test
    void whenGETInAuthorsRouteIsCalledThenStatusCodeOkShouldBeReturned() throws Exception {
        var expectedAuthorsResponse = List.of(
            AuthorResponse.builder()
                .id(1L)
                .name("Test")
                .birthDate(LocalDate.of(1996, 1, 1))
                .deathDate(LocalDate.of(2020, 1, 1))
                .build()
        );

        when(authorService.findAll()).thenReturn(expectedAuthorsResponse);

        mockMvc.perform(MockMvcRequestBuilders.get(AUTHORS_ROUTE)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(expectedAuthorsResponse.get(0).getId().intValue())))
            .andExpect(jsonPath("$[0].name", is(expectedAuthorsResponse.get(0).getName())))
            .andExpect(jsonPath("$[0].birthDate", is(expectedAuthorsResponse.get(0).getBirthDate().toString())))
            .andExpect(jsonPath("$[0].deathDate", is(expectedAuthorsResponse.get(0).getDeathDate().toString())));
    }

}
