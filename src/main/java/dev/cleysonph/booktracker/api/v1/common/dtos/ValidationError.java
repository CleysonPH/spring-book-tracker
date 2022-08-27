package dev.cleysonph.booktracker.api.v1.common.dtos;

import java.util.List;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String message;

    public static ValidationError of(FieldError fieldError) {
        return ValidationError.builder()
            .field(fieldError.getField())
            .message(fieldError.getDefaultMessage())
            .build();
    }

    public static List<ValidationError> of(MethodArgumentNotValidException exeption) {
        return exeption.getBindingResult().getFieldErrors().stream()
            .map(ValidationError::of)
            .toList();
    }

}
