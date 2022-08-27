package dev.cleysonph.booktracker.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cleysonph.booktracker.core.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
