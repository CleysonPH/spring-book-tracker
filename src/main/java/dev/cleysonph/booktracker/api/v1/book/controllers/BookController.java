package dev.cleysonph.booktracker.api.v1.book.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
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

    @GetMapping(BookRoutes.FIND_ALL_BOOKS_ROUTE)
    public List<BookSummaryResponse> findAll() {
        return bookService.findAll();
    }

    @GetMapping(BookRoutes.FIND_BOOK_BY_ID_ROUTE)
    public BookDetailResponse findById(@PathVariable Long bookId) {
        return bookService.findById(bookId);
    }

    @DeleteMapping(BookRoutes.DELETE_BOOK_BY_ID_ROUTE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long bookId) {
        bookService.deleteById(bookId);
    }

    @PutMapping(BookRoutes.UPDATE_BOOK_BY_ID_ROUTE)
    public BookDetailResponse updateById(@PathVariable Long bookId, @Valid @RequestBody BookRequest bookRequest) {
        return bookService.updateById(bookId, bookRequest);
    }

}
