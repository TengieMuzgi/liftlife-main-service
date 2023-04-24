package com.liftlife.liftlife.util.exception;

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
