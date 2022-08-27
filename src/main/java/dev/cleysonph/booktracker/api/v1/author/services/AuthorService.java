package dev.cleysonph.booktracker.api.v1.author.services;

import java.util.List;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;

public interface AuthorService {

    AuthorResponse create(AuthorRequest authorRequest);
    List<AuthorResponse> findAll();
    AuthorResponse findById(Long authorId);

}
