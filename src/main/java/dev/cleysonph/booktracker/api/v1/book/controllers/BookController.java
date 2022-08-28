package dev.cleysonph.booktracker.api.v1.book.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.routes.BookRoutes;
import dev.cleysonph.booktracker.api.v1.book.services.BookService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(BookRoutes.CREATE_BOOK_ROUTE)
    public BookDetailResponse create(@Valid @RequestBody BookRequest bookRequest) {
        return bookService.create(bookRequest);
    }

}
