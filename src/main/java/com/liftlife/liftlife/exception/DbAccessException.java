package com.liftlife.liftlife.exception;

public class DbAccessException extends RuntimeException{
    public DbAccessException() {
    }

    public DbAccessException(String message) {
        super(message);
    }

    public DbAccessException(Throwable cause) {
        super(cause);
    }
}
