package me.cal1br.webserverprogramming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidModelException extends RuntimeException {
    public InvalidModelException(final String message) {
        super(message);
    }
}
