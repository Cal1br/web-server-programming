package me.cal1br.webserverprogramming.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UnsupportedImageFormatException extends RuntimeException {
    public UnsupportedImageFormatException(final String message) {
        super(message);
    }
}
