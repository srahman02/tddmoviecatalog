package com.example.moviecatalogservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemsNotFoundException extends RuntimeException {

    public ItemsNotFoundException() {
        super();
    }

    public ItemsNotFoundException(String message) {
        super(message);
    }

}
