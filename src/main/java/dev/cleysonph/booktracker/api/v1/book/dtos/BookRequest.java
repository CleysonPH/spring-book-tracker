package dev.cleysonph.booktracker.api.v1.book.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.ISBN;
import org.hibernate.validator.constraints.URL;
import org.hibernate.validator.constraints.ISBN.Type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 255)
    private String title;

    private String summary;

    @NotNull
    @Positive
    private Integer pages;

    @NotNull
    @NotEmpty
    @ISBN(type = Type.ANY)
    @Size(min = 10, max = 13)
    private String isbn;

    @URL
    @NotNull
    @NotEmpty
    @Size(min = 10, max = 255)
    private String coverUrl;

    @NotNull
    @Positive
    private Integer authorId;

}
