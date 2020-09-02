package com.example.demo.controller;

import com.example.demo.domain.exception.NumbersNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * Global error handler in which exception handler for different exception types are defined.
 */
@ControllerAdvice
@Slf4j
public class ErrorHandler {

    /**
     * Exception handler for @see javax.validation.ConstraintViolationException exception. In case of this error
     * return response with status 400 (Bad Request) and propagate exception message.
     *
     * @param e ConstraintViolationException exception to be handled
     * @param response response
     */
    @SneakyThrows
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleConstraintViolationException(ConstraintViolationException e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * Exception handler for @see org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
     * exception. In case of this error return response with status 400 (Bad Request) and custom exception message.
     *
     * @param e MethodArgumentTypeMismatchException exception to be handled
     * @param response response
     */
    @SneakyThrows
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public void handleConstraintViolationException(MethodArgumentTypeMismatchException e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid argument types in the request");
    }

    /**
     * Exception handler for @see com.example.demo.domain.exception.NumbersNotFoundException exception. In case of this
     * error return response with status 404 (Not Found) and propagate exception message.
     *
     * @param e NumbersNotFoundException exception to be handled
     * @param response response
     */
    @SneakyThrows
    @ExceptionHandler(NumbersNotFoundException.class)
    public void handleConstraintViolationException(NumbersNotFoundException e, HttpServletResponse response) {
        log.error(e.getMessage(), e);
        response.sendError(HttpStatus.NOT_FOUND.value(), "Numbers not found");
    }
}
