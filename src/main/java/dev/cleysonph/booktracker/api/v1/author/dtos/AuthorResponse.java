package dev.cleysonph.booktracker.api.v1.author.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {

    private Long id;
    private String name;
    private LocalDate birthDate;
    private LocalDate deathDate;

}
