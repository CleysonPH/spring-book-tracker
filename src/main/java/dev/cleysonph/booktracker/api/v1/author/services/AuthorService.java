package dev.cleysonph.booktracker.api.v1.author.services;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;

public interface AuthorService {

    AuthorResponse create(AuthorRequest authorRequest);

}
