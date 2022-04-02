package com.example.moviecatalogservice.exception;

import com.example.moviecatalogservice.config.Generated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Generated
public class ApiError {
    private HttpStatus status;
    private String message;
}
