package dev.cleysonph.booktracker.api.v1.author.mappers;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.core.models.Author;

public interface AuthorMapper {

    AuthorResponse toResponse(Author author);
    Author toModel(AuthorRequest authorRequest);

}
