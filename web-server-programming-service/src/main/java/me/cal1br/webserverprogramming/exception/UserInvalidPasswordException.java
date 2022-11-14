package me.cal1br.webserverprogramming.exception;

public class UserInvalidPasswordException extends RuntimeException {
    public UserInvalidPasswordException(final String message) {
        super(message);
    }
}
