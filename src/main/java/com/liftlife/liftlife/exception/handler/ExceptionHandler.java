package com.liftlife.liftlife.exception.handler;

import com.liftlife.liftlife.exception.DbAccessException;
import com.liftlife.liftlife.exception.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
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

}
