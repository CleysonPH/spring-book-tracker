package dev.cleysonph.booktracker.api.v1.author.services;

import java.util.List;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
import dev.cleysonph.booktracker.core.exceptions.AuthorNotFoundException;
import dev.cleysonph.booktracker.core.repositories.AuthorRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponse create(AuthorRequest authorRequest) {
        var authorToCreate = authorMapper.toModel(authorRequest);
        var createdAuthor = authorRepository.save(authorToCreate);
        return authorMapper.toResponse(createdAuthor);
    }

    @Override
    public List<AuthorResponse> findAll() {
        return authorRepository.findAll()
            .stream()
            .map(authorMapper::toResponse)
            .toList();
    }

    @Override
    public AuthorResponse findById(Long authorId) {
        return authorRepository.findById(authorId)
            .map(authorMapper::toResponse)
            .orElseThrow(() -> new AuthorNotFoundException(authorId));
    }

    @Override
    public void deleteById(Long authorId) {
        // TODO Auto-generated method stub

    }

}
