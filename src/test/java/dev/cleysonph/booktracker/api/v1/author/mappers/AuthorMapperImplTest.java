package dev.cleysonph.booktracker.api.v1.author.mappers;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.core.models.Author;

class AuthorMapperImplTest {

    private AuthorMapperImpl authorMapper;

    @BeforeEach
    public void setUp() {
        authorMapper = new AuthorMapperImpl();
    }

    @Test
    void toResponseShouldThrowExceptionWhenAuthorIsNull() {
        var e = assertThrows(IllegalArgumentException.class, () -> authorMapper.toResponse(null));
        assertEquals("author cannot be null", e.getMessage());
    }

    @Test
    void toResponseShouldReturnAnAuthorResponseWhenAuthorIsNotNull() {
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
        var actualAuthorResponse = authorMapper.toResponse(author);
        assertEquals(expectedAuthorResponse.getId(), actualAuthorResponse.getId());
        assertEquals(expectedAuthorResponse.getName(), actualAuthorResponse.getName());
        assertEquals(expectedAuthorResponse.getBirthDate(), actualAuthorResponse.getBirthDate());
        assertEquals(expectedAuthorResponse.getDeathDate(), actualAuthorResponse.getDeathDate());
    }

    @Test
    void toModelShouldThrowExceptionWhenAuthorResponseIsNull() {
        var e = assertThrows(IllegalArgumentException.class, () -> authorMapper.toModel(null));
        assertEquals("authorRequest cannot be null", e.getMessage());
    }

    @Test
    void toModelShouldReturnAnAuthorWhenAuthorRequestIsNotNull() {
        var authorRequest = AuthorRequest.builder()
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var expectedAuthor = Author.builder()
            .name("Test")
            .birthDate(LocalDate.of(1996, 1, 1))
            .deathDate(LocalDate.of(2020, 1, 1))
            .build();
        var actualAuthor = authorMapper.toModel(authorRequest);
        assertNull(actualAuthor.getId());
        assertEquals(expectedAuthor.getName(), actualAuthor.getName());
        assertEquals(expectedAuthor.getBirthDate(), actualAuthor.getBirthDate());
        assertEquals(expectedAuthor.getDeathDate(), actualAuthor.getDeathDate());
    }

}
