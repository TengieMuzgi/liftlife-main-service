package com.liftlife.liftlife.util.exception.handler;

import com.liftlife.liftlife.util.exception.NotFoundException;

public class UserNotFound extends NotFoundException {

    public UserNotFound(String message) {
        super(message);
    }
}
