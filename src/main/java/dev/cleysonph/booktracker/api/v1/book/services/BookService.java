package dev.cleysonph.booktracker.api.v1.book.services;

import java.util.List;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;

public interface BookService {

    BookDetailResponse create(BookRequest bookRequest);
    List<BookSummaryResponse> findAll();
    BookDetailResponse findById(Long id);
    BookDetailResponse updateById(Long id, BookRequest bookRequest);
    void deleteById(Long id);

}
