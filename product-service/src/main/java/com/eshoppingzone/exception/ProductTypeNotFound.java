package com.eshoppingzone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductTypeNotFound extends RuntimeException {

    private static final long serialVersionUID = 1l;
    public ProductTypeNotFound(String message) {
        super(message);
    }

}
