package dev.cleysonph.booktracker.api.v1.author.services;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.api.v1.author.mappers.AuthorMapper;
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

}
