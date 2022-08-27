package dev.cleysonph.booktracker.core.exceptions;

public class AuthorNotFoundException extends ModelNotFoundException {

    public AuthorNotFoundException() {
        super("Author not found");
    }

    public AuthorNotFoundException(String message) {
        super(message);
    }

    public AuthorNotFoundException(Long authorId) {
        super(String.format("Author with id %d not found", authorId));
    }

}
