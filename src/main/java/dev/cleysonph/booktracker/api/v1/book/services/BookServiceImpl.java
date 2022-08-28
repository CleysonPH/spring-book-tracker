package dev.cleysonph.booktracker.api.v1.book.services;

import java.util.List;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapper;
import dev.cleysonph.booktracker.core.exceptions.BookNotFoundException;
import dev.cleysonph.booktracker.core.repositories.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;

    @Override
    public BookDetailResponse create(BookRequest bookRequest) {
        var bookToCreate = bookMapper.toModel(bookRequest);
        var createdBook = bookRepository.save(bookToCreate);
        return bookMapper.toDetailResponse(createdBook);
    }

    @Override
    public List<BookSummaryResponse> findAll() {
        return bookRepository.findAll()
            .stream()
            .map(bookMapper::toSummaryResponse)
            .toList();
    }

    @Override
    public BookDetailResponse findById(Long id) {
        var book = bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toDetailResponse(book);
    }

    @Override
    public BookDetailResponse updateById(Long id, BookRequest bookRequest) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

}
