package com.liftlife.liftlife.util.exception.handler;

import com.liftlife.liftlife.util.exception.DbAccessException;
import com.liftlife.liftlife.util.exception.InvalidTokenException;
import com.liftlife.liftlife.util.exception.NotFoundException;
import com.liftlife.liftlife.util.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

//    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<Object> handleCustomException(InvalidTokenException ex, WebRequest request) {
//        // Twój kod obsługi wyjątku
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
//    }

    //invalid authorization token
//    @org.springframework.web.bind.annotation.ExceptionHandler(value = { InvalidTokenException.class})
//    protected ResponseEntity<Object> handleUserNotFound(InvalidTokenException exception, WebRequest request) {
//        String bodyOfResponse = exception.getMessage();
//        logger.info(bodyOfResponse);
//        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
//    }

    //runtime ex
    @org.springframework.web.bind.annotation.ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
        String bodyOfResponse = exception.getMessage();
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(NotFoundException exception, WebRequest request) {
        String bodyOfResponse = exception.getMessage();
        logger.info(bodyOfResponse);
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { DbAccessException.class})
    protected ResponseEntity<Object> handleDatabaseGet(DbAccessException exception, WebRequest request) {
        String bodyOfResponse = "Error while accessing database, E: " + exception.getMessage();
        logger.error(bodyOfResponse);
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { BadCredentialsException.class})
    protected ResponseEntity<Object> handleBadCredentials(BadCredentialsException exception, WebRequest request) {
        String bodyOfResponse = "Bad credentials";
        logger.error(bodyOfResponse);
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.ACCEPTED, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = { UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(UserNotFoundException exception, WebRequest request) {
        String bodyOfResponse = exception.getMessage();
        logger.info(bodyOfResponse);
        return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }





}
