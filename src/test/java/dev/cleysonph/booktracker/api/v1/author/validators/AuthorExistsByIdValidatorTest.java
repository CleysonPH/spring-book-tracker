package dev.cleysonph.booktracker.api.v1.author.validators;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.cleysonph.booktracker.core.repositories.AuthorRepository;

@ExtendWith(MockitoExtension.class)
public class AuthorExistsByIdValidatorTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorExistsByIdValidator authorExistsByIdValidator;

    @Test
    void isValidShouldReturnTrueWhenValueIsNull() {
        var actualResult = authorExistsByIdValidator.isValid(null, null);
        assertTrue(actualResult);
    }

    @Test
    void isValidShouldReturnTrueWhenValueIsAnExistentAuthorId() {
        when(authorRepository.existsById(1L)).thenReturn(true);
        var actualResult = authorExistsByIdValidator.isValid(1L, null);
        assertTrue(actualResult);
    }

    @Test
    void isValidShouldReturnFalseWhenValueIsAnInexistentAuthorId() {
        when(authorRepository.existsById(1L)).thenReturn(false);
        var actualResult = authorExistsByIdValidator.isValid(1L, null);
        assertFalse(actualResult);
    }

}
