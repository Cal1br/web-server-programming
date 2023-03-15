package me.cal1br.webserverprogramming.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User already exists!")
public class UserAlreadyExists extends RuntimeException {
    public UserAlreadyExists() {
    }
}
