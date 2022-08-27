package dev.cleysonph.booktracker.api.v1.authors.services;

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

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorServiceImpl;
import dev.cleysonph.booktracker.core.exceptions.AuthorNotFoundException;
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

    @Test
    void findAllShouldReturnAListOfAuthorResponse() {
        var authors = List.of(
            Author.builder()
                .id(1L)
                .name("Test")
                .birthDate(LocalDate.of(1996, 1, 1))
                .deathDate(LocalDate.of(2020, 1, 1))
                .build()
        );
        var expectedAuthorsResponse = List.of(
            AuthorResponse.builder()
                .id(1L)
                .name("Test")
                .birthDate(LocalDate.of(1996, 1, 1))
                .deathDate(LocalDate.of(2020, 1, 1))
                .build()
        );

        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.toResponse(authors.get(0))).thenReturn(expectedAuthorsResponse.get(0));

        var actualAuthorsResponse = authorService.findAll();

        assertEquals(expectedAuthorsResponse.size(), actualAuthorsResponse.size());
        assertEquals(expectedAuthorsResponse.get(0).getId(), actualAuthorsResponse.get(0).getId());
        assertEquals(expectedAuthorsResponse.get(0).getName(), actualAuthorsResponse.get(0).getName());
        assertEquals(expectedAuthorsResponse.get(0).getBirthDate(), actualAuthorsResponse.get(0).getBirthDate());
        assertEquals(expectedAuthorsResponse.get(0).getDeathDate(), actualAuthorsResponse.get(0).getDeathDate());
    }

    @Test
    void findByIdShouldReturnAnAuthorResponseWhenAnValidIdIsGiven() {
        var author = Author.builder()
            .id(1L)
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

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorMapper.toResponse(author)).thenReturn(expectedAuthorResponse);

        var actualAuthorResponse = authorService.findById(1L);

        assertEquals(expectedAuthorResponse.getId(), actualAuthorResponse.getId());
        assertEquals(expectedAuthorResponse.getName(), actualAuthorResponse.getName());
        assertEquals(expectedAuthorResponse.getBirthDate(), actualAuthorResponse.getBirthDate());
        assertEquals(expectedAuthorResponse.getDeathDate(), actualAuthorResponse.getDeathDate());
    }

    @Test
    void findByIdShouldThrowAnExceptionWhenAnInvalidIdIsGiven() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        var e = assertThrows(AuthorNotFoundException.class, () -> authorService.findById(1L));
        assertEquals("Author with id 1 not found", e.getMessage());
    }

    @Test
    void deleteByIdShouldDeleteAnAuthorWhenAnValidIdIsGiven() {
        var author = Author.builder()
            .id(1L)
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        authorService.deleteById(1L);

        verify(authorRepository, times(1)).delete(author);
    }

    @Test
    void deleteByIdShouldThrowAnExceptionWhenAnInvalidIdIsGiven() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        var e = assertThrows(AuthorNotFoundException.class, () -> authorService.deleteById(1L));
        assertEquals("Author with id 1 not found", e.getMessage());
    }

}
