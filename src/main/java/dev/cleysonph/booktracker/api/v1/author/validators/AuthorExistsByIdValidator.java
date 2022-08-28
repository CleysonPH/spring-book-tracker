package dev.cleysonph.booktracker.api.v1.author.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import dev.cleysonph.booktracker.core.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorExistsByIdValidator implements ConstraintValidator<AuthorExistsById, Long> {

    private final AuthorRepository authorRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return authorRepository.existsById(value);
    }

}
