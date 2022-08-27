package dev.cleysonph.booktracker.api.v1.author.mappers;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.core.models.Author;

public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorResponse toResponse(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }

        return AuthorResponse.builder()
            .id(author.getId())
            .name(author.getName())
            .birthDate(author.getBirthDate())
            .deathDate(author.getDeathDate())
            .build();
    }

    @Override
    public Author toModel(AuthorRequest authorRequest) {
        if (authorRequest == null) {
            throw new IllegalArgumentException("authorRequest cannot be null");
        }

        return Author.builder()
            .name(authorRequest.getName())
            .birthDate(authorRequest.getBirthDate())
            .deathDate(authorRequest.getDeathDate())
            .build();
    }

}
