package com.kz.web.config.secure.context.exceptions;

public class AlreadyExistingUser extends RuntimeException{
    public AlreadyExistingUser() {
        super();
    }

    public AlreadyExistingUser(String message) {
        super(message);
    }

    public AlreadyExistingUser(String message, Throwable cause) {
        super(message, cause);
    }
}
