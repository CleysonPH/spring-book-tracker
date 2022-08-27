package dev.cleysonph.booktracker.api.v1.common.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Integer status;
    private String reason;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;

}
