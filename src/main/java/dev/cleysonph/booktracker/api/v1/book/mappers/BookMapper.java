package dev.cleysonph.booktracker.api.v1.book.mappers;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.core.models.Book;

public interface BookMapper {

    Book toModel(BookRequest bookRequest);
    BookDetailResponse toDetailResponse(Book book);
    BookSummaryResponse toSummaryResponse(Book book);

}
