package com.kz.web.config.secure.context.exceptions;

public class AlreadyExistingUser extends RuntimeException{
    public static final int CODE = 400;
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
