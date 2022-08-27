package dev.cleysonph.booktracker.api.v1.book.mappers;

import java.util.Objects;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.core.exceptions.AuthorNotFoundException;
import dev.cleysonph.booktracker.core.models.Author;
import dev.cleysonph.booktracker.core.models.Book;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookMapperImpl implements BookMapper {

    private final AuthorRepository authorRepository;

    @Override
    public Book toModel(BookRequest bookRequest) {
        if (Objects.isNull(bookRequest)) {
            throw new IllegalArgumentException("bookRequest cannot be null");
        }

        if (bookRequest.getAuthorId() == null) {
            throw new IllegalArgumentException("bookRequest.authorId cannot be null");
        }

        return Book.builder()
            .title(bookRequest.getTitle())
            .summary(bookRequest.getSummary())
            .pages(bookRequest.getPages())
            .isbn(bookRequest.getIsbn())
            .coverUrl(bookRequest.getCoverUrl())
            .author(getAuthor(bookRequest.getAuthorId()))
            .build();
    }

    @Override
    public BookDetailResponse toDetailResponse(Book book) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BookSummaryResponse toSummaryResponse(Book book) {
        // TODO Auto-generated method stub
        return null;
    }

    private Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId)
            .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

}
