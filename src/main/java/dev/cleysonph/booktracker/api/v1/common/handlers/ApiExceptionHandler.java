package dev.cleysonph.booktracker.api.v1.common.handlers;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import dev.cleysonph.booktracker.api.v1.common.dtos.ErrorResponse;
import dev.cleysonph.booktracker.core.exceptions.ModelNotFoundException;

@RestControllerAdvice(basePackages = "dev.cleysonph.booktracker.api.v1")
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFoundException(ModelNotFoundException exception, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var errorResponse = ErrorResponse.builder()
            .status(status.value())
            .reason(status.getReasonPhrase())
            .error(exception.getClass().getSimpleName())
            .message(exception.getMessage())
            .path(((ServletWebRequest)request).getRequest().getRequestURI())
            .timestamp(LocalDateTime.now())
            .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

}
