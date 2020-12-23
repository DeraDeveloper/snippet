package com.storage.snippet.config;

import com.storage.snippet.exceptions.RequestProcessingException;
import com.storage.snippet.exceptions.ResourceNotFoundException;
import com.storage.snippet.exceptions.ValidationException;
import com.storage.snippet.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGeneralException(Exception e, HttpServletRequest httpServletRequest) {
        System.out.println("Unknown server error" + e.getMessage());

        return buildErrorResponse("There was an error processing the request, Please try again later...");
    }

    @ExceptionHandler(RequestProcessingException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleRequestProcessingException(RequestProcessingException e, HttpServletRequest httpServletRequest) {
        System.out.println("Illegal argument exception "+ e.getMessage());
        return buildErrorResponse(e.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e, HttpServletRequest httpServletRequest) {
        System.out.println(e.getMessage());

        return buildErrorResponse("404 Resource not found");
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(ValidationException e, HttpServletRequest httpServletRequest) {
        System.out.println("Bad request "+ e.getMessage());

        return buildErrorResponse(e.getMessage());
    }

    private ErrorResponse buildErrorResponse(String message){
        return new ErrorResponse(message);
    }

}
