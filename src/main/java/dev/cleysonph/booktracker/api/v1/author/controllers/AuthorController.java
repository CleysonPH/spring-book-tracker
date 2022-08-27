package dev.cleysonph.booktracker.api.v1.author.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorRequest;
import dev.cleysonph.booktracker.api.v1.author.dtos.AuthorResponse;
import dev.cleysonph.booktracker.api.v1.author.routes.AuthorRoutes;
import dev.cleysonph.booktracker.api.v1.author.services.AuthorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping(AuthorRoutes.CREATE_AUTHOR_ROUTE)
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponse create(@RequestBody @Valid AuthorRequest authorRequest) {
        return authorService.create(authorRequest);
    }

    @GetMapping(AuthorRoutes.FIND_ALL_AUTHORS_ROUTE)
    public List<AuthorResponse> findAll() {
        return authorService.findAll();
    }

}
