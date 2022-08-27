package dev.cleysonph.booktracker.api.v1.book.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailResponse {

    private Long id;
    private String title;
    private String summary;
    private Integer pages;
    private String isbn;
    private String coverUrl;

}
