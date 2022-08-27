package dev.cleysonph.booktracker.api.v1.authors.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorServiceImpl;
import dev.cleysonph.booktracker.core.models.Author;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceImplTest {

    @Mock
    private AuthorMapper authorMapper;

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void createShouldReturnAnAuthorResponseWhenAnAuthorRequestIsGiven() {
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

        var authorToCreate = Author.builder()
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var createdAuthor = Author.builder()
            .id(1L)
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();

        when(authorMapper.toModel(authorRequest)).thenReturn(authorToCreate);
        when(authorRepository.save(authorToCreate)).thenReturn(createdAuthor);
        when(authorMapper.toResponse(createdAuthor)).thenReturn(expectedAuthorResponse);

        var actualAuthorResponse = authorService.create(authorRequest);

        assertEquals(expectedAuthorResponse.getId(), actualAuthorResponse.getId());
        assertEquals(expectedAuthorResponse.getName(), actualAuthorResponse.getName());
        assertEquals(expectedAuthorResponse.getBirthDate(), actualAuthorResponse.getBirthDate());
        assertEquals(expectedAuthorResponse.getDeathDate(), actualAuthorResponse.getDeathDate());
    }

}
