package dev.cleysonph.booktracker.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cleysonph.booktracker.core.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
