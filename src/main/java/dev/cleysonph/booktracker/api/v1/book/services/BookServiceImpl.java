package dev.cleysonph.booktracker.api.v1.book.services;

import java.util.List;

import dev.cleysonph.booktracker.api.v1.book.dtos.BookDetailResponse;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookRequest;
import dev.cleysonph.booktracker.api.v1.book.dtos.BookSummaryResponse;
import dev.cleysonph.booktracker.api.v1.book.mappers.BookMapper;
import dev.cleysonph.booktracker.core.repositories.BookRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookMapper bookMapper;
    private final BookRepository bookRepository;
    @Override
    public BookDetailResponse create(BookRequest bookRequest) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<BookSummaryResponse> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public BookDetailResponse findById(Long id) {
        // TODO Auto-generated method stub
        return null;
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
