package dev.cleysonph.booktracker.api.v1.author.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AuthorExistsByIdValidator.class)
public @interface AuthorExistsById {

    String message() default "Author with id ${validatedValue} does not exists";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default { };

}
