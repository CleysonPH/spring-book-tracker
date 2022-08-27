package dev.cleysonph.booktracker.core.exceptions;

public class BookNotFoundException extends ModelNotFoundException {

    public BookNotFoundException() {
        super("Book not found");
    }

    public BookNotFoundException(Long id) {
        super(String.format("Book with id %d not found", id));
    }

    public BookNotFoundException(String message) {
        super(message);
    }

}
