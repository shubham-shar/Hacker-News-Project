package com.java.assignment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Null;
import java.io.IOException;

/**
 * @author Shubham Sharma
 * @date 24/5/20
 */
@Slf4j
@ControllerAdvice
public class ApiErrorHandler{

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
    public String handleBadRequest(Exception ex, Model model) {
        log.error("Bad Request, Something went wrong", ex);
        model.addAttribute("status", HttpStatus.BAD_REQUEST);
        model.addAttribute("error", ex.getCause());
        model.addAttribute("message", "Bad Request, Something went wrong");
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ NullPointerException.class })
    protected String handleNotFound(Exception ex, Model model) {
        log.error("Something went wrong ", ex);
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("error", ex.getCause());
        model.addAttribute("message",
                "Something went wrong at our end, Sometimes we have a bad day too, We will fix this soon");
        return "error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ IOException.class })
    protected String ioException(Exception ex, Model model) {
        log.error("Some IO exception occured ", ex);
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("error", ex.getCause());
        model.addAttribute("message",
                "Well, this is odd, Seems we are facing some network issue");
        return "error";
    }

}
