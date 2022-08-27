package dev.cleysonph.booktracker.api.v1.book.mappers;

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
        if (bookRequest == null) {
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
        if (book == null) {
            throw new IllegalArgumentException("book cannot be null");
        }

        if (book.getAuthor() == null) {
            throw new IllegalArgumentException("book.author cannot be null");
        }

        return BookDetailResponse.builder()
            .id(book.getId())
            .title(book.getTitle())
            .summary(book.getSummary())
            .pages(book.getPages())
            .isbn(book.getIsbn())
            .coverUrl(book.getCoverUrl())
            .authorId(book.getAuthor().getId())
            .build();
    }

    @Override
    public BookSummaryResponse toSummaryResponse(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("book cannot be null");
        }

        if (book.getAuthor() == null) {
            throw new IllegalArgumentException("book.author cannot be null");
        }

        return BookSummaryResponse.builder()
            .id(book.getId())
            .title(book.getTitle())
            .pages(book.getPages())
            .isbn(book.getIsbn())
            .coverUrl(book.getCoverUrl())
            .authorId(book.getAuthor().getId())
            .build();
    }

    private Author getAuthor(Long authorId) {
        return authorRepository.findById(authorId)
            .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

}
